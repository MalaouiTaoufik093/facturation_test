FROM openjdk:8
EXPOSE 8080
ADD target/matu_jenkins.jar target/matu_jenkins.jar
ENTRYPOINT ["java","-jar","/target/matu_jenkins.jar"]

