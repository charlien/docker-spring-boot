#!/bin/sh
DEFAULT_WAIT=10
BUILD_WAIT=${BUILD_WAIT:=$DEFAULT_WAIT}
WAIT=${WAIT:=$DEFAULT_WAIT}
APP=${APP:?"Missing application name"}
APP_PATH=${APP_PATH:="/usr/app/target"}
LOCK=${LOCK:="b_lock"}
JAR_PATH="${APP_PATH}/$APP"

counter=0
# Since docker compose doesn't support dependency chaining, this will wait for the
# new build to finish before executing the jar or else it will pick up the old
# jar and not have any of the new changes in it
while [ $counter -le $BUILD_WAIT ] && { [ -f  "$JAR_PATH" ] || [ -f $LOCK ]; }; do
  echo "Waiting for build completion $counter"
  counter=$(( counter + 1 ))
  sleep 1;
done

counter=0
# Wait until the new jar is created by maven
until { [ ! -f $LOCK ] && [ -f "$JAR_PATH" ]; } || [ $counter -eq $WAIT ]
do
  echo "Waiting for $JAR_PATH $counter"
  counter=$(( counter + 1 ))
  sleep 1
done

# If timeout occurred because jar could not be created, we jsut exit
if [ ! -f "$JAR_PATH" ]; then
  echo "Unable to find $JAR_PATH"
  exit 1
else
  # Springboot needs to modify the jar to update the MANIFEST.MF so on some boot
  # (mostly the first compose up), the MANIFEST is detected as invalid
  # so this makes sure that the MANIFEST.MF is formatted appropriately before working
  counter=0
  until [ 1 -eq "$( unzip -q -p "$JAR_PATH" META-INF/MANIFEST.MF  | grep -ci "main" )" ] || [ $counter -eq $BUILD_WAIT ]
  do
    echo "Waiting for updated MANIFEST.MF $counter"
    counter=$(( counter + 1 ))
    sleep 1
  done
  exec java -jar "$JAR_PATH"
fi