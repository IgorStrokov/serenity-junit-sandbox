package starter.services;

import static starter.utils.ConfigProvider.getRequestSpec;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class WeatherRestServiceClient {
    private static final String OPENWEATHER_ENDPOINT = "/data/2.5/weather";

    public static Response getWeatherByCityName(String name) {
        return getRequestSpec()
              .param("q", name)
              .when()
              .get(OPENWEATHER_ENDPOINT);
    }

    public static Response getWeatherByCityNameInForeignLanguage(String language, String name) {
        Map<String, String> params = new HashMap<>();
        params.put("lang", language);
        params.put("q", name);
        return getRequestSpec()
              .params(params)
              .when()
              .get(OPENWEATHER_ENDPOINT);
    }

    public static Response getWeatherByCityId(int cityId) {
        return getRequestSpec()
              .param("id", cityId)
              .when()
              .get(OPENWEATHER_ENDPOINT);
    }

}
