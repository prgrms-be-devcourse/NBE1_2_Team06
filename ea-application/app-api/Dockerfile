FROM openjdk:17.0.1-jdk-slim as build
WORKDIR /workspace/app

# 환경변수 추가
ARG MODULE_PATH=ea-application/app-api
ARG MODULE_NAME=app-api
ARG VERSION=0.0.1

COPY ../.. .

RUN ./gradlew :ea-application:${MODULE_NAME}:build --parallel -x test
RUN mkdir -p ${MODULE_PATH}/build/extracted && (java -Djarmode=layertools -jar ${MODULE_PATH}/build/libs/${MODULE_NAME}-${VERSION}.jar extract --destination ${MODULE_PATH}/build/extracted)

FROM openjdk:17.0.1-jdk-slim
VOLUME /tmp

RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

ARG MODULE_PATH=ea-application/app-api
ARG EXTRACTED=/workspace/app/${MODULE_PATH}/build/extracted

COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]
