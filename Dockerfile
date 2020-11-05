FROM openjdk:11

WORKDIR /opt/server

COPY ./target/smkt-oauth-1.0.0.jar ./app.jar

EXPOSE 4050

CMD [ "java", "-jar", "./app.jar" ]
