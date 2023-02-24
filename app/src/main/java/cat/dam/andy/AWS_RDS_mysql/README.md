IMPORTANT:
This is a work in progress. It is not yet ready for use.
This app is a simple example of how to use AWS RDS with MySQL and Android Studio.
DATABASE SHOULD BE CREATED MANUALLY IN AWS RDS BEFORE RUNNING THE APP
You should give PERMISSIONS to your IP in the AWS RDS DB
Attention: you CANNOT UPGRADE your mysql-connector-java in gradle to newer one because it is not compatible with Android Studio
Use only the version 5.1.46 or any other version that is compatible with Android Studio
You should enter your credentials in database/AWS_KEYS.java
In your AWS RDS DB you should create table with the name "POKEMON" and IMPORT the file "pokemon.csv" you will find in files folder.
Enjoy Pokemon! Hope it helps you!