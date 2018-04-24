<?php
 $connect = mysqli_connect ('localhost', 'root', 'root', 'bigdata');
 $query = "SELECT COUNT(sentiment) as number FROM tweets GROUP BY sentiment;";
 $result = mysqli_query($connect, $query);
 ?>

 <!DOCTYPE html>
 <html>
      <head>
           <title>ClutterSight</title>
           <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
           <script type="text/javascript">
           google.charts.load('current', {'packages':['corechart']});
           google.charts.setOnLoadCallback(drawChart);
           // Draws Pie Chart
           function drawChart()
           {
                var data = google.visualization.arrayToDataTable([
                          ['sentiment', 'number'],
                          //reads in numbers from database
                          <?php
                          while($row = mysqli_fetch_array($result))
                          {
                               echo "['".$row["sentiment"]."', ".$row["number"]."],";
                          }
                          ?>
                     ]);
                var options = {
                      title: 'Positive and Negative Tweets',
                      //is3D:true,
                      pieHole: 0.4
                     };
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
           }
           </script>
      </head>
      <body>
           <br /><br />
           <div style="width:900px;">
                <h1 align="center">ClutterSight</h1>
                <br />
                <div id="piechart" style="width: 900px; height: 500px;"></div>
           </div>
      </body>
 </html>
