FROM --platform=linux/x86-64/v8 openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/part_4-0.0.1-SNAPSHOT.jar part_4-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/part_4-0.0.1-SNAPSHOT.jar"]
