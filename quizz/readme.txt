This is build with spring boot and java 1.8

Use maven to build the application: mvn clean install
Once build, run: mvn spring-boot:run

Once running, you can make GET requests at:  
	localhost:8090/coding/quiz?questions=5&categories=10,11,12,14

The application uses an extra dependency for Lombok which is there to minimize the boiler plate code
like getter, setter, No args constructor and all args constructor.
The application also uses dependency for mockito which is used to write unit test for rest controller methods.

The url take two query parameters. 

	1. questons - This determines how many questions to be returned per category (default is 5)
	2. categories - This is comma-separated values for categories. (default is 11, 12)


