#!/usr/bin/env bash
serverPort=$1
projectName=$2
configFile=$3

configFolder=resources

#Constans
logFile=initServer.log
dstLogFile=$logFile

destFile=attchment-1.0.jar

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
function movefile(){
    echo "move from deploy to the source"
    mv todeploy/$destFile $destFile
    echo "file moved"
}
function run(){
    rm -r $dstLogFile
    touch $dstLogFile
    nohup nice java -jar $destFile --server.port=$serverPort >> $dstLogFile 2>&1 &
    echo "COMMAND:nohup nice java -Djava.security.egd=file:/dev/./urandom -jar $destFile --server.port=$serverPort > $dstLogFile 2>&1 &"
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

movefile
run
watch
