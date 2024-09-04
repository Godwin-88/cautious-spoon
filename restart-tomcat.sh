#!/bin/bash
sudo /opt/tomcat11/bin/shutdown.sh
sleep 5  # Give it a few seconds to shut down completely
sudo /opt/tomcat11/bin/startup.sh
