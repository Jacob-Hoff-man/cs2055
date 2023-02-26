
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
