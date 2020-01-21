To read the JSON, I decided to use the JSON.simple library.
The Customer class stores the user id and name of the customer.
The CloseCustomers class creates a txt file of a JSON array, parses the freshly created JSON
file, and creates an arraylist of customers who are close. Finally, upon instantation of
the CloseCustomers object, it sorts and prints the customers who are within 100km.

To run the tests, make sure to have JUNIT 4 installed. Simply run the CloseCustomersTest.java
file.

To run the program, simply run Main.java with the first argument as the name of the .txt file in quotes.
(Do this in Eclipse)
You can download the JSON Simple library and add it to class path here:
https://github.com/cis350/20sp-hw1/blob/master/lib/json-simple-1.1.1.jar
