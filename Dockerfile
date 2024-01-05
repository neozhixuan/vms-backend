FROM openjdk:17-oracle
EXPOSE 8080
ADD target/vms-backend.war vms-backend.war
ENTRYPOINT ["java", "-jac", "vms-backend.war"]