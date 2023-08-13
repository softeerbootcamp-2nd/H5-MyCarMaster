FROM openjdk:11-jre-slim

ARG JAR_FILE=./build/libs/*.jar
ARG PROFILE
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

COPY ${JAR_FILE} app.jar
COPY config/pinpoint-agent pinpoint-agent

ENV PROFILE=${PROFILE}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV PINPOINT_AGENT_ID=${PINPOINT_AGENT_ID}
ENV TZ=Asia/Seoul

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080
ENTRYPOINT ["java", "-jar", \
    "-Dspring.profiles.active=${PROFILE}", \
    "-javaagent:/pinpoint-agent/pinpoint-bootstrap-2.2.2.jar", \
    "-Dpinpoint.agentId=${PINPOINT_AGENT_ID}","-Dpinpoint.applicationName=h5", \
    "-Dpinpoint.config=/pinpoint-agent/pinpoint-root.config", \
    "/app.jar"]
