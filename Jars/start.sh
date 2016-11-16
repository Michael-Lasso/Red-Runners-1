#!/bin/sh

#######################################################
#######   Simple Script to launch Java Program   ######
#######################################################

# In order for this script to work, 
#1. The first parameter must be starting date

JAR=`find $RED_RUNNER_PATH -name "*scheduling*.jar" -exec printf {} ';'`
JAVABIN=$JAVA_HOME/bin
JAVA_EXE=$JAVABIN/java

$JAVA_EXE -jar $JAR $RED_RUNNER_PATH 
