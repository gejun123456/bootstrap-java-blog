#!/usr/bin/env bash
serverPort=$1
projectName=$2
configFile=$3

#Constans
logFile=initServer.log
dstLogFile=$logFile

destFile=target/attchment-1.0.jar

deployfilename=app.jar

deployLoc=/opt/deploy/my-java-blog/

oldLoc=/opt/old/my-java-blog/

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
movefile(){

#need first move old to to the old loc
    echo "move old jar to old directory"
    mkdir -p $oldLoc
    mv $deployLoc$deployfilename $oldLoc

    echo "move new jar to deploy folder"
    mkdir -p $deployLoc
    mv $destFile $deployLoc$deployfilename
    cd $deployLoc
}
function run(){
    rm -r $dstLogFile
    touch $dstLogFile
    nohup nice java -Xms128m -Xmx512m -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar $deployfilename --server.port=$serverPort >> $dstLogFile 2>&1 &
    echo "COMMAND:nohup nice java -Xms128m -Xmx512m -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar $deployfilename --server.port=$serverPort > $dstLogFile 2>&1 &"
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
