
## How To Run The Project:

Enter the project's main directory in your terminal and run the following:

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/generate_tables.sql`

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/sample_data.sql`

`psql -U {{postgres username}} -d {{postgres database name}} -a -f db/triggers.sql`

`java --enable-preview -jar CoffeeBoutique.jar`
