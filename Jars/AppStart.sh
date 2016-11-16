#!/bin/sh

#######################################################
#######   Simple Script to launch Java Program   ######
#######################################################

# In order for this script to work, 
#1. The first parameter must be starting date
    
PROG=`basename $0`
PROG_DIR=`dirname $0`
PROG_DIR=`cd $PROG_DIR/.;pwd`

HOMEDIR=`cd ${PROG_DIR}/..;pwd`
LOGDIR=$HOMEDIR/logs
MODIFIER=$1

JAVAHOME=C:/Program Files/Java/jre1.8.0_60
JAVABIN=$JAVAHOME/bin

JARS=`find $HOME/ -name "*.jar" -exec printf :{} ';'`

LOGDIR=$HOMEDIR/logs

PATH="C:/Workspace/Cygwin/home/mlasso/Miscellaneous/Red-Runners-1/Resources"

CLASSPATH=:$CLASSPATH:$PATH
export CLASSPATH
export CONFIGDIR

echo "CLASSPATH IS: $CLASSPATH"

JVM_OPTS="-Dssui.iniFileName=$CONFIGDIR/ssui.ini -Dapp=projectName -Dlog.dir=$LOGDIR  -Xms512m -Xmx1g"
APP_EXE="file.jar"
JAVA_EXE=$JAVABIN/java

$JAVA_EXE $JVM_OPTS $APP_EXE $MODIFIER

if [ "$MODIFIER" == "" ]; then
	echo "Usage: $PROG <MODIFIER/YYYYMMDD>"
	echo "Example: $PROG 20100611"
	exit 1
fi

java -jar gs-scheduling-tasks-0.1.0.jar
