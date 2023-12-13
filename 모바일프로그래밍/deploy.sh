REPO=/home/ubuntu
cd $REPO/hmhBackend/
echo "> Git Pull"
git pull
echo "> Start Build"
./gradlew build
echo "> Copy Build File"
cp ./build/libs/*.jar $REPO/
echo "> Check Currently running application PID"
CURRENT_PID=$(pgrep -f hmhBackend)
echo "$CURRENT_PID"
if [ -z $CURRENT_PID ]; then
    echo "> No applications are currently running and will not be shut down"
else
    echo "> kill $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi
echo "> Start New Deploy"
JAR_NAME=$(ls $REPO/ |grep 'hmhBackend-0.0.1-SNAPSHOT.jar' | tail -n 1)
echo "> JAR Name: $JAR_NAME"
nohup java -jar $REPO/$JAR_NAME &