import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest {

    @Test
    public void testGetUsers() {
        // Use the following API endpoint and print the response:
        // URL to use: https://reqres.in/api/users?page=2
        // Method to use: GET

        Response response = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract().response();

        // Print the response body
        System.out.println("Response body: " + response.asString());

        // Validate the response status code
        response.then().statusCode(200);

        // Validate the response body
        response.then()
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2))
                .body("data.id", equalTo(7))
                .body("data.email", equalTo("michael.lawson@reqres.in"))
                .body("data.first_name", equalTo("Michael"))
                .body("data.last_name", equalTo("Lawson"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/7-image.jpg"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        // Validate the response headers
        response.then()
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .header("Content-Encoding", equalTo("br"));
    }
}
