package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetStoreApiTest {

    private int petId = 19072026;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void createPet() {
        String body = "{\"id\": " + petId + ", \"name\": \"Zeytin\", \"status\": \"available\"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Zeytin"));
    }

    @Test(priority = 2)
    public void getPet() {
        when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("status", notNullValue());
    }

    @Test(priority = 3)
    public void updatePet() {
        String updatedBody = "{\"id\": " + petId + ", \"name\": \"Zeytin-Updated\", \"status\": \"sold\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Zeytin-Updated"))
                .body("status", equalTo("sold"));
    }

    @Test(priority = 4)
    public void getNonExistingPet() {
        when()
                .get("/pet/99999999912323904")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @Test(priority = 5)
    public void getPetWithInvalidIdFormat() {
        when()
                .get("/pet/abc-invalid-id")
                .then()
                .statusCode(404);
    }

    @Test(priority = 6)
    public void createPetWithEmptyBody() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/pet")
                .then()
                .statusCode(anyOf(is(200), is(405)));
    }

    @Test(priority = 7)
    public void createPetWithWrongContentType() {
        given()
                .contentType(ContentType.XML)
                .body("<pet><name>Test</name></pet>")
                .when()
                .post("/pet")
                .then()
                .statusCode(anyOf(is(415), is(400)));
    }

    @Test(priority = 8)
    public void deletePet() {
        when()
                .delete("/pet/" + petId)
                .then()
                .statusCode(200);
    }

    @Test(priority = 9)
    public void deleteAlreadyDeletedPet() {
        when()
                .delete("/pet/" + petId)
                .then()
                .statusCode(404);
    }
}