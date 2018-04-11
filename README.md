# RoadStatusChecker

Simple Spring Boot app to integrate with TFL's road status api. 

Styling courtesy of [Google Materialize](http://materializecss.com).

Front end templating engine is Thymeleaf.

The solution offered is a simple web app with a single form to read in user road choice and update users view with the result of the api call.

## Assumptions

When no road is provided, the api's default response is better than no response.

The message attribute of 404 is sufficiently verbose for an informative message to the user. 

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Configuration

Before running the app, you will want to update your app id and api key. These are found within the project under src\main\resources in a file called application.properties.
The two properties that need updating are called tfl.appId and tfl.appKey. Paste the corresponding values in directly after the equals symbol.

## Running the application locally

Opening the project in an IDE, one way to launch the application is run the main method found in com.creavin.roadstatuschecker.RoadStatusChecker.

Alternatively, use the maven goal spring-boot:run

The app will then be accessible via the following url in the web browser of your choice:
http://localhost:8080/tflApi

## Testing

Unit tests can be run using the maven goal test.