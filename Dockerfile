FROM openjdk:1.8
EXPOSE 8484
ADD target/facturation_test.jar target/facturation_test.jar
ENTRYPOINT ["java","-jar","/target/facturation_test.jar"]



