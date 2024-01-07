# Volunteer Management System

This is the backend for the VMS utilising Spring's various technologies to create a simple factory of beans that support a Postgres RDBMS.

![image](https://github.com/neozhixuan/vms-backend/assets/79783660/1ee91eae-ceea-40fb-ac99-84ad3046bb30)

## Tech Stack

Spring Boot Backend:
- Spring JPA for repository management and persistence
- Spring Web to support RESTful API endpoint architecture
- Utilised Inheritance and Polymorphism in entity design, and Encapsulation in Java syntax
- Minimised code bulk by Abstracting code to Repositories, Services, and POJO Requests
- Deployed onto Amazon S3 and EC2 for cloud access

PostgreSQL
- Supported by JPA for pre-made queries
- Utilised SQL queries like JOINs to gather data for endpoints
- Bridged the One-to-Many and Many-to-One associations in the class diagram
- Deployed onto Amazon EC2 for persistent access

Amazon Web Services
- Docker for containerising of application into an image
- Route 53 for DNS resolution of domain names
- Relational Database Services to host PostgreSQL server
- Elastic Kubernetes Services to host docker images as Kubernetes pods
- EC2 to manage node groups within EKS
- Elastic Load Balancer to allow for exposure to internet gateway
- Terraform to manage and provision AWS Resources simultaneously
- Jenkins to tie it all together in a CI/CD pipeline to deploy everything effortlessly
