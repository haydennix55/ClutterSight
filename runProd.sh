#!/bin/bash

mvn compile exec:java -Dexec.classpathScope=compile -Dexec.mainClass=storm.ClutterSightTopology -Dexec.args="prod" -Dexec.cleanupDaemonThreads=false
python PythonML/db_access.py prod
