package starter.services;

import org.apache.groovy.util.Maps;
import starter.utils.RestResponse;

public class WeatherRestServiceClient extends RestService{
    private static final String OPENWEATHER_ENDPOINT = "/data/2.5/weather";

    public  RestResponse getWeatherByCityName(String name) {
        return get(OPENWEATHER_ENDPOINT, Maps.of("q", name));
    }

    public  RestResponse getWeatherByCityNameInForeignLanguage(String language, String name) {
        return get(OPENWEATHER_ENDPOINT, Maps.of("lang", language, "q", name));
    }

    public  RestResponse getWeatherByCityId(int cityId) {
        return get(OPENWEATHER_ENDPOINT, Maps.of("id", cityId));
    }

}
