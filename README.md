# SBAB programming assignment

## Requirements
### Functional requirements
1. Implement an API for with minimum 2 endpoints.
2. The [Trafiklab’s open API](https://www.trafiklab.se/api/sl-hallplatser-och-linjer-2) should be used as a data source.
3. The data should be automatically gathered from API for each run of the application.
4. One end-point should return the top 10 bus lines in Stockholm area with most bus stops on
   their route.
5. The other endpoint should return a list of every bus stop of the bus busLine
       provided.
   
### Non-functional requirements
#### Non-functional requirements from assignment
1. The application should be implemented with Java.
2. The delivery of the solution should include the source code, the build script and the
   instructions to run the application in MacOS/Unix or Windows.
#### Non-functional requirements added by me
3. The Trafiklab’s API key should be kept private since it is sensitive information 
    >En API-nyckel ska man inte dela med sig av utan hålla privat, annars så kan någon annan missbruka ett API i ditt namn.
4. The JSON property naming strategy: follow the data source API.
5. The API should be secure and only provide HTTPS endpoints.
6. The API documentation should be created.
7. The Swagger UI should be implemented.
#### Optional
8. HATEOAS
9. Docker

