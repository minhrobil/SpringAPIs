FROM --platform=linux/x86-64/v8 openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/part_4-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
