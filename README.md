# Loan management
[![Build Status](https://travis-ci.com/Naturhistoriska/loan-management.svg?branch=master)](https://travis-ci.com/Naturhistoriska/loan-management)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Loan management system is a system to manage online loan request. It consist of two applications: loan-request and loan-admin

Loan request is an online request form for making information, photo or loan requests of specimens from  collections of Swedish Natural History of Museum.

Loan admin is for loan manager to handle the requests.


## Prerequisites

Solr: 4.10.2
Collections data need to be index in solr

Mongodb 4.2.1
loans

mysql 5.7.22
userdb

wildfly 8.2
Config datasource to connect to mysql userdb


## Usage
Download this source code. cd to directory: loan-management

## Run:

### Mongo:

 - cd mongo
 - run: docker-compose up -d
 - run: docker-compose ps  (To make sure mongo express is up)
 - open mongo express: http://localhost:8081/
 - loans database should be imported


### wildfly

 1. cd wildfly
 2. To build wildfly image with preconfiguration, run:
    
    - make build
    - docker-compose up -d

 3. To upload userdb into mysql:
 
    - copy file from local to docker container:
    
    
    
     1. docker cp data/userdb.sql mysql:/userdb.sql
     2. docker exec -it mysql bash
     3. inside mysql docker container:
     4. mysql -u root -p userdb < userdb.sql
     5. password: admin

4. To access wildfly admin console:
  - Run: http://localhost:9990
  - username: admin
  - password: dina


### loan-management applications

  - run: mvn clean package

  - For deployment:
  - cd dina-loan-admin/target
  - deploy loan-admin.war into wildfly
  - cd dina-loan-web/target
  - deploy loan.war into wildfly



### Run the app:

  - Loan request form:
  - http://localhost:8080/loan
  
  - Loan admin:
  - https://localhost:8443/loan-admin
 
  Access loan-admin with:
  username: admin
  password: admin







