#!/usr/bin/env bash
env=$1
serverPort=$2
projectName=$3
configFile=$4

destAbsPath=/home/rcoli/Desktop/$projectName/$env
configFolder=resources

#Constans
logFile=initServer.log
dstLogFile=$logFile

destFile=target/attchment-1.0.jar

whatToFind="Started"
msgLogFileCreated="$logFile created"
msgAppStarted="Application Started... exiting buffer!"

### Functions
#######
function stopServer(){
    echo ""
    echo "Stopping process on port:$serverPort"
    curl -X POST localhost:$serverPort/shutdown
    echo ""
}


function deleteFiles(){
    echo "Deleting files"
    rm -rf *

    echo ""
}

#function copyFiles(){
#    echo "Copying files from $sourFile"
#    cp $sourFile $destFile
#
#    echo "Copying files from $sourConfigFolder"
#    cp -r $sourConfigFolder $destConfigFolder
#
#    echo " "
#}

function run(){
    nohup nice java -jar $destFile --server.port=$serverPort > $dstLogFile 2>&1 &
    echo "COMMAND:nohup nice java -jar $destFile --server.port=$serverPort > $dstLogFile 2>&1 &"
    echo ""
}

function watch(){
    tail -f $dstLogFile |
        while IFS= read Line
            do
                echo "$msgBuffer" "$line"
                if [["$line"==*"$whatToFind"*]];
                    then echo $msgAppstarted
                    pkill tail
                fi
        done
}

stopServer


