# Этап 1: Сборка
FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn compiler:compile
RUN mvn war:war

# Этап 2: запуск образа
FROM tomcat:9-jre11-slim
COPY --from=build /build/artifacts/PropertyTaxWebApp.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
