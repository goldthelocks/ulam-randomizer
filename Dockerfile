FROM amazoncorretto:11-alpine-jdk as LAYERS_BUILD
WORKDIR application
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:11-alpine-jdk
WORKDIR application
EXPOSE 9999
COPY --from=LAYERS_BUILD application/dependencies/ ./
COPY --from=LAYERS_BUILD application/spring-boot-loader ./
COPY --from=LAYERS_BUILD application/snapshot-dependencies/ ./
COPY --from=LAYERS_BUILD application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]