FROM maven:3-openjdk-11 as build

WORKDIR /opt/build

COPY . .

RUN mvn clean compile install

RUN mv ./target/smkt-oauth.jar /app.jar

FROM openjdk:11

WORKDIR /opt/server

COPY --from=build /app.jar  ./app.jar


ARG port=9100
ARG eureka_url=http://smkt-eureka:8761/eureka
ARG config_oauth_client_id=smartkitchenapp
ARG config_oauth_client_secret=secret
ARG config_oauth_jwt_key=U21hcnRLaXRjaGVuLVNNS1RfT2F1dGg
ARG users_id=smkt-users
ARG app_secret=Sm@artKitchen

ENV PORT ${port}
ENV EUREKA_URL ${eureka_url}
ENV CONFIG_OAUTH_CLIENT_ID ${config_oauth_client_id}
ENV CONFIG_OAUTH_CLIENT_SECRET ${config_oauth_client_secret}
ENV CONFIG_OAUTH_JWT_KEY ${config_oauth_jwt_key}
ENV USERS_ID ${users_id}
ENV APP_SECRET ${app_secret}

EXPOSE ${PORT}

CMD java -jar app.jar --server.port="${PORT}" --eureka.client.service-url.defaultZone="${EUREKA_URL}" --config.security.oauth.client.id="${CONFIG_OAUTH_CLIENT_ID}" --config.security.oauth.client.secret="${CONFIG_OAUTH_CLIENT_SECRET}" --config.security.oauth.jwt.key="${CONFIG_OAUTH_JWT_KEY}}" --usersId="${USERS_ID}" --app-secret="${APP_SECRET}"

