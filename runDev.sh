#!/bin/bash

mvn compile exec:java -Dexec.classpathScope=compile -Dexec.mainClass=storm.ClutterSightTopology -Dexec.args="dev" -Dexec.cleanupDaemonThreads=false
python PythonML/db_access.py dev
