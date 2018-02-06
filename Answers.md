1. The purpose of .gitignore is to ask Github to ignore the dir ".idea", also from the commit, it says it also ask to ignore the file end with .iml.
2. Gradle is used to compile the JavaSpark stuff and Tests. build.gradle is the set up for Gradle.
3. "Travis CI is a Continuous Integration tool that performs builds of your project every time you push to GitHub." Also, it shows the status of yout push, and it tracks the build and pull requestes history.
4. Route consists of a verb (get,post,etc..), a path (/hello) and a callback (request,response). Example: get("/hello", (req, res) -> "Hello World");
5. The sever class is the set up for server, which will return stuff based on the call from the client. The class UserController is the class called in sever which get the correct information from DB.
  * users: The sever will return the users.html
  * api/users: The sever returns the list of users, filters with query parameters
  * api/users?age=25: The sever returns the list of users who are the age of 25
  * api/users/588935f5de613130e931ffd5: The sever returns the information for a specific user, this id gets information for Valerie Erickson
