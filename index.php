<?php
 $connect = mysqli_connect ('localhost', 'root', 'root', 'bigdata');
 $query = "SELECT COUNT(sentiment) as number FROM tweets GROUP BY sentiment;";
 $result = mysqli_query($connect, $query);
 ?>

 <!DOCTYPE html>
 <html>
      <head>
           <title>ClutterSight</title>
	    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/	bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/Footer-with-button-logo.css">
	<style>
	body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
  body{background-image: url(https://i2.wp.com/www.rbipaper.com/wp-content/uploads/vector-grey-abstract-background-for-design_MJmTARLO.jpg);}
  </style>
           <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
           <script type="text/javascript">
           google.charts.load('current', {'packages':['corechart']});
           google.charts.setOnLoadCallback(drawChart);
           function drawChart()
           {
                var data = google.visualization.arrayToDataTable([
                          ['sentiment', 'number'],
                          <?php
                          while($row = mysqli_fetch_array($result))
                          {
                               echo "['".$row["sentiment"]."', ".$row["number"]."],";
                          }
                          ?>
                     ]);
                var options = {
                      //title: 'Positive and Negative Tweets',
                      is3D:true,
                      pieHole: 0.4,
                      chartArea:{left:0,top:0,width:"100%", height:"100%"},
                     };
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
           }
           </script>
      </head>

<body class="w3-light-grey" ><?php
 $connect = mysqli_connect ('localhost', 'root', 'root', 'bigdata');
 $query = "SELECT COUNT(sentiment) as number FROM tweets GROUP BY sentiment;";
 $result = mysqli_query($connect, $query);
 ?>

 <!DOCTYPE html>
 <html>
      <head>
           <title>ClutterSight</title>
	    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="style.css" type="text/css"/>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/	bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/Footer-with-button-logo.css">
	<style>
	body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
  body{background-image: url(https://i2.wp.com/www.rbipaper.com/wp-content/uploads/vector-grey-abstract-background-for-design_MJmTARLO.jpg);}
  </style>
           <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
           <script type="text/javascript">
           google.charts.load('current', {'packages':['corechart']});
           google.charts.setOnLoadCallback(drawChart);
           function drawChart()
           {
                var data = google.visualization.arrayToDataTable([
                          ['sentiment', 'number'],
                          <?php
                          while($row = mysqli_fetch_array($result))
                          {
                               echo "['".$row["sentiment"]."', ".$row["number"]."],";
                          }
                          ?>
                     ]);
                var options = {
                      //title: 'Positive and Negative Tweets',
                      is3D:true,
                      pieHole: 0.4,
                      legend: 'none',
                      labels: 'name',
                      chartArea:{left:0,top:0,width:"100%", height:"100%"},

                     };
                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
           }
           </script>
      </head>

<body class="w3-light-grey" >

<!-- w3-content defines a container for fixed size centered content,
and is wrapped around the whole page content, except for the footer in this example -->
<div class="w3-content" style="max-width:1400px">

<!-- Header -->
<header class="w3-container w3-center w3-padding-32">
  <h1 style="font-size:55px;"><b>ClutterSight</b></h1>
  <h4 style="font-size: 20px;">Your Social Media Data Analyst</h4>
</header>

<!-- Grid -->
<div class="w3-row">

<!-- Blog entries -->
<div class="w3-col l8 s12">
  <!-- Blog entry -->
  <div class="w3-card-4 w3-margin w3-white">


           <div style="width:500px;">
		<h3 style="padding: 20px;"><b>Query Results</b></h3>
                <br />
                <div id="piechart" style="width: 80%; height: 290px; padding-left: 100px; text-align:left;"></div>
                <a style="padding-left: 30px;" href="http://tinypic.com?ref=4rcpqp" target="_blank"><img src="http://i65.tinypic.com/4rcpqp.jpg" border="0" alt="Image"></a></div>


	<div class="w3-container" style="padding: 20px 20px 0px 20px">

      <h5>Analytic description</h5>
    </div>

    <div class="w3-container" style="padding: 10px 20px 20px 20px;">
      <p>The chart above demonstrates the percentage of "positive", "negative", and "neutral" tweets about
      the searched person or company. This is determined by gathering tweets for the mentioned company,
    and cross referencing the contents of the tweets with a database of words that are considered
  positive and negative. No matches are considered neutral tweets. Then the tweets are assigned a score of 1, 0, or -1 based on the contents
of the tweet.</p>

    </div>
  </div>
  <hr>

  <!-- Blog entry -->
  <div class="w3-card-4 w3-margin w3-white">

    <div class="w3-container">
      <h3><b>Location of Tweets</b></h3>
      <h5>Plotted Around the Country</h5>
      <img style="width: 80%;" src="http://cdn.shopify.com/s/files/1/0977/4104/products/USA-XX-113467_ceed41f4-9fd3-43c8-b42e-c33783654dc7_grande.jpeg?v=1460587498">
    </div>

    <div class="w3-container" style="padding: 20px;">
      <p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed
        tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>

    </div>
  </div>
<!-- END BLOG ENTRIES -->
</div>

<!-- Introduction menu -->
<div class="w3-col l4">
  <!-- About Card -->
  <div class="w3-card-2 w3-margin w3-margin-top">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaa4WunlQgON4rc74oHygTzY6-hDinlWucPBt5MUetG2ua3zfC" style="width:100%">
    <div class="w3-container w3-white" style="padding: 20px;">
      <h4><b>About the Project</b></h4>
      <p>Cluttersight was created to provide a way for companies to see how the public views them on Social Media - specifically on Twitter. Cluttersight uses machine
      learning analytics to search tweets about a company and display the percentage of "good", "bad", and "neutral" sounding tweets that contain the company or person's
      name. This gives people a unique way to conceptualize how the public views their company.</p>
    </div>
  </div><hr>

  <!-- Posts -->
  <div class="w3-card-2 w3-margin-top">
<!--
    <div class="w3-container w3-padding">
      <h4 style="color: white;">Our Team</h4>
    </div>
  -->
    <ul class="w3-ul w3-hoverable w3-white">
      <h4 style="padding-top: 20px; padding-left: 20px; font-size: 20px;"><b>Our Team</b></h4>
      <hr>
      <li class="w3-padding-16">
        <img src="https://avatars2.githubusercontent.com/u/15626122?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Hayden Nix</span><br>
        <span><a href="https://github.com/haydennix55">GitHub.com/haydennix55</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars1.githubusercontent.com/u/18429502?s=150&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Ben Wasko</span><br>
        <span><a href="https://github.com/bewa4021">GitHub.com/bewa4021</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://media.licdn.com/dms/image/C4E03AQGCw1wiDOGuSw/profile-displayphoto-shrink_200_200/0?e=1529132400&v=beta&t=qiyqi8h5Z3wByJ1DIH1blsNPn_qBic5MVnseEacUW8Q" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Allison Rodenbaugh</span><br>
        <span><a href="https://github.com/ARodenbaugh">GitHub.com/ARodenbaugh</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars2.githubusercontent.com/u/32139381?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Marissa Tracy</span><br>
        <span><a href=">https://github.com/MarissaTracy">GitHub.com/MarissaTracy</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars1.githubusercontent.com/u/17892193?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Eric Ha</span><br>
        <span><a href="https://github.com/eric-ha">GitHub.com/eric-ha</a></span>
      </li>
    </ul>
  </div>
  <hr>

<!-- END Introduction Menu -->
</div>

<!-- END GRID -->
</div><br>

<!-- END w3-content -->
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>


 </html>


<!-- w3-content defines a container for fixed size centered content,
and is wrapped around the whole page content, except for the footer in this example -->
<div class="w3-content" style="max-width:1400px">

<!-- Header -->
<header class="w3-container w3-center w3-padding-32">
  <h1 style="font-size:55px;"><b>ClutterSight</b></h1>
  <h4>Your Social Media Data Analyst</h4>
</header>

<!-- Grid -->
<div class="w3-row">

<!-- Blog entries -->
<div class="w3-col l8 s12">
  <!-- Blog entry -->
  <div class="w3-card-4 w3-margin w3-white">


           <div style="width:500px;">
		<h3 style="padding: 20px;"><b>Query Results</b></h3>
                <br />
                <div id="piechart" style="width: 100%; height: 290px; padding-left: 100px;"></div>
           </div>
	<div class="w3-container" style="padding: 20px 20px 0px 20px">
      <h5>Analytic description</h5>
    </div>

    <div class="w3-container" style="padding: 10px 20px 20px 20px;">
      <p>The chart above demonstrates the percentage of "positive", "negative", and "neutral" tweets about
      the searched person or company. This is determined by searching tweets for the mentioned company,
    and cross referencing the contents of the tweets with a database of words that are considered
  positive, negative, and neutral. Then the tweets are assigned a score of 1, 0, or -1 based on the contents
of the tweet.</p>

    </div>
  </div>
  <hr>

  <!-- Blog entry -->
  <div class="w3-card-4 w3-margin w3-white">

    <div class="w3-container">
      <h3><b>Location of Tweets</b></h3>
      <h5>Plotted Around the Country</h5>
      <img style="width: 80%;" src="http://cdn.shopify.com/s/files/1/0977/4104/products/USA-XX-113467_ceed41f4-9fd3-43c8-b42e-c33783654dc7_grande.jpeg?v=1460587498">
    </div>

    <div class="w3-container" style="padding: 20px;">
      <p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt sed
        tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>

    </div>
  </div>
<!-- END BLOG ENTRIES -->
</div>

<!-- Introduction menu -->
<div class="w3-col l4">
  <!-- About Card -->
  <div class="w3-card-2 w3-margin w3-margin-top">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaa4WunlQgON4rc74oHygTzY6-hDinlWucPBt5MUetG2ua3zfC" style="width:100%">
    <div class="w3-container w3-white" style="padding: 20px;">
      <h4><b>About the Project</b></h4>
      <p>Cluttersight was created to provide a way for companies to see how the public views them on Social Media - specifically on Twitter. Cluttersight uses machine
      learning analytics to search tweets about a company and display the percentage of "good", "bad", and "neutral" sounding tweets that contain the company or person's
      name. This gives people a unique way to conceptualize how the public views their company.</p>
    </div>
  </div><hr>

  <!-- Posts -->
  <div class="w3-card-2 w3-margin-top">
<!--
    <div class="w3-container w3-padding">
      <h4 style="color: white;">Our Team</h4>
    </div>
  -->
    <ul class="w3-ul w3-hoverable w3-white">
      <h4 style="padding-top: 20px; padding-left: 20px; font-size: 20px;"><b>Our Team</b></h4>
      <hr>
      <li class="w3-padding-16">
        <img src="https://avatars2.githubusercontent.com/u/15626122?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Hayden Nix</span><br>
        <span><a href="https://github.com/haydennix55">GitHub.com/haydennix55</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars1.githubusercontent.com/u/18429502?s=150&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Ben Wasko</span><br>
        <span><a href="https://github.com/bewa4021">GitHub.com/bewa4021</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://media.licdn.com/dms/image/C4E03AQGCw1wiDOGuSw/profile-displayphoto-shrink_200_200/0?e=1529132400&v=beta&t=qiyqi8h5Z3wByJ1DIH1blsNPn_qBic5MVnseEacUW8Q" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Allison Rodenbaugh</span><br>
        <span><a href="https://github.com/ARodenbaugh">GitHub.com/ARodenbaugh</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars2.githubusercontent.com/u/32139381?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Marissa Tracy</span><br>
        <span><a href=">https://github.com/MarissaTracy">GitHub.com/MarissaTracy</a></span>
      </li>
      <li class="w3-padding-16">
        <img src="https://avatars1.githubusercontent.com/u/17892193?s=460&v=4" alt="Image" class="w3-left w3-margin-right" style="width:50px">
        <span class="w3-large">Eric Ha</span><br>
        <span><a href="https://github.com/eric-ha">GitHub.com/eric-ha</a></span>
      </li>
    </ul>
  </div>
  <hr>

<!-- END Introduction Menu -->
</div>

<!-- END GRID -->
</div><br>

<!-- END w3-content -->
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
 </html>
