FROM openjdk:3.8.1
EXPOSE 8484
ADD target/facturation_test.jar target/facturation_test.jar
ENTRYPOINT ["java","-jar","/target/facturation_test.jar"]



