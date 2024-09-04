#!/bin/bash

# Define paths
WAR_FILE="/home/ed/NetBeansProjects/GhostNetFISH/target/GhostNetFISH-1.0-SNAPSHOT.war"
TOMCAT_WEBAPPS_DIR="/opt/tomcat11/webapps"
TOMCAT_BIN_DIR="/opt/tomcat11/bin"

# Stop Tomcat if running
echo "Stopping Tomcat..."
$TOMCAT_BIN_DIR/shutdown.sh

# Wait a few seconds to ensure Tomcat stops
sleep 5

# Copy WAR file to the Tomcat webapps directory
echo "Copying WAR file to Tomcat webapps folder..."
cp $WAR_FILE $TOMCAT_WEBAPPS_DIR

# Start Tomcat server
echo "Starting Tomcat..."
$TOMCAT_BIN_DIR/startup.sh

echo "Deployment complete. Tomcat is starting up."
