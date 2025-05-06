# Volunteer Management System

This is the backend for the VMS utilising Spring's various technologies to create a simple factory of beans that support a Postgres RDBMS.

![image](https://github.com/neozhixuan/vms-backend/assets/79783660/1ee91eae-ceea-40fb-ac99-84ad3046bb30)

## Set Up

1. Start the postgres database in your pgAdmin.
2. Update the spring.datasource.url, as well as username and password to match the database.

```sh
    spring.datasource.url= jdbc:postgresql://<host-name>:<port>/<table-name>
```

3. Remove the previous builds and create a proper build using Maven

```sh
mvn clean package
```

4. Run the spring boot application using Maven

```sh
mvn spring-boot:run
```

## Tech Stack and Project Scope

**Spring Boot Backend**

- Spring JPA for repository management and persistence
  - Hibernate is the engine at the backend that manages the annotations (e.g. converts your @Entity class (Event) into SQL tables and rows)
- Spring Web to support RESTful API endpoint architecture
- Minimised code bulk by abstracting code to Repositories, Services, and POJO Requests
- Deployed onto Amazon S3 and EC2 for cloud access

**PostgreSQL**

- Supported by JPA for pre-made queries
- Utilised SQL queries like JOINs to gather data for endpoints
- Bridged the One-to-Many and Many-to-One associations in the class diagram
- Deployed onto Amazon EC2 for persistent access

**CI/CD**
- Jenkins to configure the Continuous Integration (CI) pipeline to run builds and tests, then Continuous Deployment (CD) into EKS using `kubectl` scripts
- Docker as part of CI - for containerising of application into an image
- Terraform as part of CD - streamlining the consistent provisioning of various AWS resources below

**Amazon Web Services**

- Route 53 for DNS resolution of domain names
- Relational Database Services to host PostgreSQL server
- Elastic Kubernetes Services to host docker images as Kubernetes pods
- EC2 to manage node groups within EKS
- Elastic Load Balancer to allow for exposure to internet gateway

## OOP Design (to be improved)

- **Inheritance**: Participant and Boss entities inherit the properties of the Person entity
- **Encapsulation**: Private properties within each entity class
- **Abstraction**: Not explicit, but Person is an abstraction of the classes that inherit it
- **Polymorphism**: Not explicit, but can be handled within JPA's inheritance support. `Person p = new Participant();`
- **Composition**: Participant entity has multiple ParticipantAvailabilities

```java
@OneToMany
private List<ParticipantAvailability> availabilities;
```
