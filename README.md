### Simple java project with the console interface UNIVERSITY

#### Installation and Launch
##### Pre-Requisites:
Please make sure you have following tools installed before continuing:
- Java 11
- Maven
- PostgresSQL 10+

##### Set Up Database

* Create an empty PostgreSQK database;
* Put following content into `hibernate.cfg.xml` replacing example values with your database credentials:

```
<property name="connection.url">jdbc:postgresql://localhost:5432/univer</property>
<property name="connection.username">plotva</property>
```

Create database tables running following SQL file in your database: `<project_roo>/src/main/resources/db/init-tables.sql.`
Populate database tables running following SQL file in your database: `<project_roo>/src/main/resources/db/populate-db.sql.`

Remove unnecessary logs adding code:
```
java.util.logging.SimpleFormatter.format=%5$s %n
```
to logging.properties file with path:
```
JDK_HOME/jre/lib/logging.properties
```

##### Launch
* Clone project
* Launch project
```
mvn exec:java
```