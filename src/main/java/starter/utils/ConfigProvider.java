package starter.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ConfigProvider {
    public final static String WEATHER_API_KEY = System.getProperty("weather.api.key");
    public final static String BASE_URI = System.getProperty("base.uri");

    public static RequestSpecification getRequestSpec() {
     return RestAssured.given()
              .baseUri(BASE_URI)
              .param("appid", WEATHER_API_KEY)
              .accept(ContentType.JSON);
    }
}
