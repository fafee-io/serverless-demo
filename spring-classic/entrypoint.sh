#!/bin/sh

echo "Calling java -jar with JAVA_OPTS= $JAVA_OPTS"
echo "Calling java -jar with AGENT_OPTS= $AGENT_OPTS"
java $AGENT_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $JAVA_OPTS
