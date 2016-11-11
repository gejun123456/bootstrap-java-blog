#!/usr/bin/env bash
serverPort=$1
projectName=$2
configFile=$3
now=${date+ "%m_%d_%Y"}

configFolder=resources

#Constans
logFile=initServer.log
dstLogFile=$logFile

destFile=attchment-1.0.jar

whatToFind="Tomcat started"
msgLogFileCreated="$logFile created"
msgAppStarted="Application Started... exiting buffer!"

#first move data for archive and dothings.
moveold(){
    mkdir old
    echo "move file to old diretory"
    mv $destFile old/attchment_${now}.jar
}


function stopServer(){
    echo ""
    echo "Stopping process on port:$serverPort"
    mm = $(curl -X POST localhost:$serverPort/shutdown)
    echo "$mm"
}

stopServer
moveold