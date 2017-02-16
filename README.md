# liboracle-jbdc-interface

Interface for Java projects to interact with the  selected SQL database under the Oracle architecture.

Taken from an old database application and actually made it to something kinda useful

## Libraries
* [ojbdc](http://www.oracle.com/technetwork/apps-tech/jdbc-112010-090769.html)
* junit 5 (to run tests)
* at least Java 7

## Testing
Testing was conducted with an [Oracle Server setup] (http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index-092322.html).Once installed, created a user with appropriate privileges to alter, create, drop TABLE.  Can be done through accessing the db from [Oracle's SQLDeveloper]

## Usage

### Connection
Provide an already created user for the database with the corresponding privileges.  If the Database SID and port are different than the default values provided, specify them before JBDCSQLConnection.createOracleConnection as createOracleConnection initiates the connection with the specified URL.

```java
oraclecon = new JBDCSQLConnection();
oraclecon.setPort(1521); /*Default values*/
oraclecon.setSid("orcl");
boolean pass = oraclecon.createOracleConnection("hostname", "user", "password");
```
The boolean return value from createOracleConnection indicates  whether the connection passes or fails along with a thrown SQLException.

### Running Reading .sql file
With SqlRunner class pass the Connection class through a class variable from JBDCSQLConnection.

```java
SqlRunner sqlr = SqlRunner.getInstance();
sqlr.setConnection(oraclecon.getConnection());
```

run(String source) - Point to the file source location in String source.

```java
sqlr.run("resources/SQL_Scripts_test/create_table_test.sql");
```

### Running - query

The alternative is running a SQL query from a string.  Set the query as a String and call the same run(String source) function.

```java
String create_query = "CREATE TABLE Persons_Query\n" +
        "(\n" +
        "    PersonID int,\n" +
        "    LastName varchar(255),\n" +
        "    FirstName varchar(255),\n" +
        "    Address varchar(255),\n" +
        "    City varchar(255)\n" +
        ");";

sqlr.run(create_query);
```

## Examples
Found in test/oraclejbdcconnection/ folder
