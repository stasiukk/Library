package com.libraryApp.utilities;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LibraryUtils {
    public static String getToken(String email, String password) {

        JsonPath jp = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC) // Datatype that I am sending to API
                .formParam("email", System.getenv("EMAIL"))
                .formParam("password", System.getenv("PASSWORD"))
                .when().post("/login")
                .then().statusCode(200)
                .extract().jsonPath();

        String token = jp.getString("token");

        return token;
    }


    public static String generateTokenByRole(String role) {

        Map<String, String> roleCredentials = returnCredentials(role);
        String email = roleCredentials.get("email");
        String password = roleCredentials.get("password");

        return getToken(email, password);

    }

    public static Map<String, String> returnCredentials(String role) {
        String email = "";
        String password = "";

        switch (role) {
            case "librarian":
                email = System.getenv("EMAIL");
                password = System.getenv("PASSWORD");
                break;


            default:

                throw new RuntimeException("Invalid Role Entry :\n>> " + role + " <<");
        }
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return credentials;

    }

    public static Map<String, Object> createRandomBook() {

        Faker faker = new Faker();
        Map<String, Object> userMap = new LinkedHashMap<>();
        String name = faker.book().title();
        String isbn = faker.code().isbn13();
        Integer year = faker.number().numberBetween(1800, 2024);
        String author = faker.book().author();
        Integer bookCategoryId = faker.number().numberBetween(1, 10); // Adjust range as needed
        String description = faker.lorem().sentence(); // + " in Ukraine.";

        userMap.put("name", name);
        userMap.put("isbn", isbn);
        userMap.put("year", year);
        userMap.put("author", author);
        userMap.put("book_category_id", bookCategoryId);
        userMap.put("description", description);

        return userMap;
    }


    public static Map<String, Object> createRandomUser() {
        Faker faker = new Faker();
        Map<String, Object> userMap = new LinkedHashMap<>();

        String fullName = faker.name().fullName(); // Random full name
        String email = faker.internet().emailAddress(); // Random email
        String password = faker.internet().password(); // Random password
        Integer userGroupId = faker.number().numberBetween(2, 3); // Random user group ID
        String status = faker.options().option("ACTIVE", "INACTIVE"); // "ACTIVE" or "INACTIVE"

        // Ensure dates are in the correct format for the database (yyyy-MM-dd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormat.format(new Date()); // Current date
        String endDate = dateFormat.format(faker.date().future(365, TimeUnit.DAYS)); // 1 year into the future

        String address = faker.address().fullAddress(); // Random address

        userMap.put("full_name", fullName);
        userMap.put("email", email);
        userMap.put("password", password);
        userMap.put("user_group_id", userGroupId);
        userMap.put("status", status);
        userMap.put("start_date", startDate);
        userMap.put("end_date", endDate);
        userMap.put("address", address);

        return userMap;


    }
}
