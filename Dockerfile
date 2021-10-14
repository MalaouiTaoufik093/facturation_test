FROM openjdk:8
EXPOSE 8080
ADD target/facturation_test.jar target/facturation_test.jar
ENTRYPOINT ["java","-jar","/target/facturation_test.jar"]

