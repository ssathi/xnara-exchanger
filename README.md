# xnara-exchanger
A project for xNara


# Instructions

1. Install JDK 17 or later
2. Run './mvnw clean test' to run the test cases
3. Run './mvnw clean package' to run the test cases and build the runnable jar
4. Run 'target/xchange-1.0.jar' to run the application
5. Application is available at 'localhost:9999/xnara/xchanger/rate'
6. Api document is available at 'http://localhost:9999/swagger-ui/index.html'

Sample Request:
{
"curr_code": "USD",
"amount": 23.0,
"language": "en",
"target_code": "SGD"
}

POST:
localhost:9999/xnara/xchanger/rate

Sample Response:
{
"result": "S$ 31.44",
"statement": "You will receive S$ 31.44"
}
