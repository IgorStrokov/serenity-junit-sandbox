package starter.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ConfigProvider {
    public final static String WEATHER_API_KEY = "7f828e302c8911ab84ab784ab3c214a0";

    public static RequestSpecification getRequestSpec() {
     return RestAssured.given()
              .baseUri("https://api.openweathermap.org")
              .param("appid", WEATHER_API_KEY)
              .accept(ContentType.JSON);
    }
}
