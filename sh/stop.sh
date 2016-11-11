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
    if [ -d "old" ]
    then echo "old exist"
    else
        mkdir old
    fi
    if [ -f $destFile ]
    then
        echo "move file to old diretory"
        mv $destFile old/attchment_old${now}.jar
    fi
}


function stopServer(){
#first check if is alived
    echo "stop server started, first the check if it's alived now"
    curl localhost:$serverPort
    if [ "$?" = "0" ]
    then echo "the server alived go stop it"
    echo "Stopping process on port:$serverPort"
    mm=$(curl -X POST localhost:$serverPort/shutdown)
    #post ended"
    echo $mm
    fi
    echo "go to check the status of the server"

    curl localhost:$serverPort
#check if 7 is exit status if is 7 means it's still alived.
    while [ "$?" = "0" ]
    do
        echo "the server still lived"
        curl localhost:$serverPort
    done
    echo "the server is stopped"
}

stopServer
moveold