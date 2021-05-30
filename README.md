# SMKT_Oauth

Authentication and Authenticate service for SmartKitchen App

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

[![Build Dev](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/buildDevVersion.yml/badge.svg?branch=develop)](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/buildDevVersion.yml) [![Build Snapshot](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/BuildSnapshot.yml/badge.svg)](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/BuildSnapshot.yml) [![Build Stable Version](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/BuildRelease.yml/badge.svg)](https://github.com/AntonioAlejandro01/SMKT_Oauth/actions/workflows/BuildRelease.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=AntonioAlejandro01_SMKT_Oauth&metric=alert_status)](https://sonarcloud.io/dashboard?id=AntonioAlejandro01_SMKT_Oauth) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AntonioAlejandro01_SMKT_Oauth&metric=coverage)](https://sonarcloud.io/dashboard?id=AntonioAlejandro01_SMKT_Oauth)


## Use With Docker

Use this Service with Docker as Docker container. The Repo have 3 types of images. 

### Types

- **Stable**: this are the images that in her tag is a specific version ex.: ```antonioalejandro01/smkt-oauth:vX.X.X```. the last tag version have latest tag. 
```bash
    docker pull antonioalejandro01/smkt-oauth:v1.0.0
    # The last stable version
    docker pull antonioalejandro01/smkt-oauth:latest
 ```

- **Snapshot**: this are the images that in her tag is snapshot ex.: ```antonioalejandro01/smkt-oauth:snapshot```
```bash 
    docker pull antonioalejandro01/smkt-oauth:snapshot
```

- **Dev**: this image is only for developers and in her tag have dev ```antonioalejandro01/smkt-oauth:dev```
```bash
    docker pull antonioalejandro01/smkt-oauth:dev
 ```

### Environment variables for Docker image

<table align="center" width="100%" style="margin:1em;">
<thead>
    <tr>
        <th>Name</th>
        <th>Default Value</th>
        <th>Description</th>
    </tr>
</thead>
<tbody>
    <tr>
        <td>PORT</td>
        <td>9100</td>
        <td>Micro service port</td>
    </tr>
    <tr>
        <td>EUREKA_URL</td>
        <td>http://127.0.0.1:8761/eureka</td>
        <td>The url where the smkt-eureka be</td>
    </tr>
    <tr>
        <td>CONFIG_OAUTH_CLIENT_ID</td>
        <td>smartkitchenapp</td>
        <td>The HTTP user</td>
    </tr>
    <tr>
        <td>CONFIG_OAUTH_CLIENT_SECRET</td>
        <td>secret</td>
        <td>The HTTP password</td>
    </tr>
    <tr>
        <td>CONFIG_OAUTH_JWT_KEY</td>
        <td>U21hcnRLaXRjaGVuLVNNS1RfT2F1dGg</td>
        <td>The JWT secret</td>
    </tr>
    <tr>
        <td>USERS_ID</td>
        <td>smkt-users</td>
        <td>Id that service <a>smkt-users</a> have it in <a href="http://github.com/antonioAlejandro01/SMKT_Eureka">smkt-eureka</a></td>
    </tr>
    <tr>
        <td>APP_SECRET</td>
        <td>Sm@artKitchen</td>
        <td>The password to do request for get Users</td>
    </tr>
</tbody>
</table>


#### Docker command

```bash
    docker run -d -p9100:9100 -ePORT=9100 -eEUREKA_URL=http://127.0.0.1:8761/eureka -t antonioalejandro01/smkt-oauth:latest
 ```

## Use in Docker Compose

```yaml
    oauth:
        image: antonioalejandro01/smkt-oauth:latest
        container_name: smkt-oauth
        environment:
            PORT: 9100
            EUREKA_URL: http://smkt-eureka:8761/eureka
        expose:
            - "9100"
        ports: 
            - "9100:9100"
```



