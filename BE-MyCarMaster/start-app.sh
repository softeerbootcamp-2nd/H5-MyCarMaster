#!/bin/sh

JAVA_OPTS="-Dspring.profiles.active=${PROFILE}"

if [ "${PROFILE}" = "prod" ]; then
  JAVA_OPTS="${JAVA_OPTS}
  -javaagent:/pinpoint-agent/pinpoint-bootstrap-2.5.0.jar
  -Dpinpoint.agentId=${PINPOINT_AGENT_ID}
  -Dpinpoint.applicationName=h5
  -Dpinpoint.config=/pinpoint-agent/pinpoint-root.config"
fi

exec java -jar $JAVA_OPTS /app.jar
