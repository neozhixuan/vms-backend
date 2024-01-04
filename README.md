# Volunteer Management System

This is the backend for the VMS utilising Spring's various technologies to create a simple factory of beans that support a Postgres RDBMS.

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
  
