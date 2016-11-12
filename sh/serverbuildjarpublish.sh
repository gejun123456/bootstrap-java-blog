#!/usr/bin/env bash
serverPort=$1
projectName=$2
configFile=$3

#Constans
logFile=initServer.log
dstLogFile=$logFile

destFile=target/attchment-1.0.jar

whatToFind="Tomcat started"
msgLogFileCreated="$logFile created"
msgAppStarted="Application Started... exiting buffer!"

### Functions
#######

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
    rm -r $dstLogFile
    touch $dstLogFile
    nohup nice java -jar $destFile --server.port=$serverPort >> $dstLogFile 2>&1 &
    echo "COMMAND:nohup nice java -jar $destFile --server.port=$serverPort > $dstLogFile 2>&1 &"
    echo ""
}

function watch(){
    tail -f $dstLogFile |
        while IFS= read line
            do
                echo "$msgBuffer" "$line"

                if [[ "$line" == *"$whatToFind"* ]];
                    then echo $msgAppstarted
                    pkill tail
                fi
        done
}

run
watch