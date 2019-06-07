# install postgres if not installed already, skip if installed
sudo apt install postgresql

# create user user; set password as password on prompt(or use any password but remember to change in the application properties)
# (select y for the second option that is allow the user to create database)
sudo -u postgres createuser --interactive --password user

# create database with user as the owner
sudo -u postgres createdb testdb -O user

# to login to the newly create db
psql --user user -d testdb -W

# schema creation is handled by spring-boot

#################################################################################
# For running the application:
# make sure java 8 is installed.
# Install postman, required to test the api for upload.
java -jar <jar_name>.jar

# make sure that port 8080 is free, or the application won't start.
# now once the application is up and running, open postman
# In postman
  1.set url to : localhost:8080/file-upload/csv

	set request type to: POST (located just at the left of the url)

	select the "body" and select "form-data"

		in the KEY field type "file", change value type to file and select "choose file",

		now select the csv file and hit "Send"

	You should get success and failure count as the response, and the data should be saved to the database.

  2.get request, set url to : localhost:8080/query/search?id=1

    and hit send to get the list of failed rows data.