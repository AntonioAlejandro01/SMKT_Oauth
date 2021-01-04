FROM maven:3-openjdk-11 as build

WORKDIR /opt/build

COPY . .

RUN mvn clean compile install

RUN mv ./target/smkt-oauth-1.0.0.jar /app.jar

FROM openjdk:11

WORKDIR /opt/server

COPY --from=build /app.jar  ./app.jar

ARG smkt_users=smkt-users
ARG port=9100
ARG eureka_url=http://smkt-eureka:8761/eureka
ARG app_secret=Sm@artKitchen

ENV SMKT_USERS ${smkt_users}
ENV APP_SECRET ${app_secret} 
ENV PORT ${port}
ENV EUREKA_URL ${eureka_url}
EXPOSE ${PORT}

CMD java -jar app.jar --server.port="${PORT}" --eureka.client.service-url.defaultZone="${EUREKA_URL}" --usersId="${SMKT_USERS}" --app-secret="${APP_SECRET}"

