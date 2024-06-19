# Coffee Boutique - Relational Database System With PostgreSQL, PL/pgSQL, JDBC, Java
## Code:

Main Java class: `./EntryPoint.java`

Psql JDBC driver: `./lib/postgresql-42.2.18.jar`

Database tables using sql: `./db/generate-tables.sql`

Database seed using sql: `./db/sample_data.sql`

Triggers, internal functions, and internal procedures using plpgsql: `./db/triggers.sql`

Establishing a singleton database connection by interfacing JDBC with database driver using Java: `./db/DBConnection.java`

Scripts for performing each project-specific task using Java: `./db/TasksDriver.java`

Simple task unit tests using Java: `./tests/TasksTests.java`

Simple stress tests using Java: `./tests/StressTests.java`

Custom DAL Entity models that map to database schemas using Java: `./models/*`

Custom DAL Entity services that define DAOs for entity models using Java and java.sql API: `./services/*`

## How To Run The Project:

Ensure that postgres has been installed to your local machine and a database has been prepared (locally or remotely).

Currently, you will need to update the `db/DBConnection.java` fields and re-compile/re-export the Java project's JAR file (I did this using VSCode and targeting the EntryPoint.java as the Main class).

Enter the project's main directory in your terminal and run the following:

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/generate_tables.sql`

Enter postgres password. Then, run the following:

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/sample_data.sql`

Enter postgres password. Then, run the following:

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/triggers.sql`

Then, run the JAR distributable file by running the following:

`java --enable-preview -jar CoffeeBoutique.jar`
