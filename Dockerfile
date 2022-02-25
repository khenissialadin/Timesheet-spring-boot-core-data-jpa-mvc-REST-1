FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.2-SNAPSHOT.war Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.2-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.2-SNAPSHOT.war"]
