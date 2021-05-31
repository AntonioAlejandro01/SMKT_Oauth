FROM maven:3-openjdk-11 as Builder

WORKDIR /build

COPY pom.xml .

RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r target/

COPY src ./src

RUN mvn clean package  -Dmaven.test.skip

RUN mv ./target/smkt-oauth.jar /app.jar

FROM openjdk:11-jre-slim

WORKDIR /opt/server

COPY --from=Builder /app.jar  ./app.jar

ENV PORT=9100
ENV EUREKA_URL=http://smkt-eureka:8761/eureka
ENV CONFIG_OAUTH_CLIENT_ID=smartkitchenapp
ENV CONFIG_OAUTH_CLIENT_SECRET=secret
ENV CONFIG_OAUTH_JWT_KEY=U21hcnRLaXRjaGVuLVNNS1RfT2F1dGg
ENV USERS_ID=smkt-users
ENV APP_SECRET=Sm@artKitchen

EXPOSE ${PORT}

CMD java -jar app.jar --server.port="${PORT}" --eureka.client.service-url.defaultZone="${EUREKA_URL}" --config.security.oauth.client.id="${CONFIG_OAUTH_CLIENT_ID}" --config.security.oauth.client.secret="${CONFIG_OAUTH_CLIENT_SECRET}" --config.security.oauth.jwt.key="${CONFIG_OAUTH_JWT_KEY}}" --usersId="${USERS_ID}" --app-secret="${APP_SECRET}"

