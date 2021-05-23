package starter.steps;

import lombok.SneakyThrows;
import net.thucydides.core.annotations.Step;
import starter.dto.ForecastDTO;
import starter.services.WeatherRestServiceClient;
import starter.utils.RestResponse;

import java.util.Arrays;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assert.assertEquals;
import static starter.enums.Context.RESPONSE;
import static starter.enums.Context.RESPONSE_DTO;
import static starter.enums.Languages.getCodeByLanguage;


public class WeatherApiSteps {
    private WeatherRestServiceClient weatherRestServiceClient;

    @Step("verify user can get weather by city id {0}")
    public void verifyUserCanGetWeatherByCityIdTest(int id) {
        RestResponse response = weatherRestServiceClient.getWeatherByCityId(id);
        assertEquals(response.getStatusCode(), 200);
    }

    @Step("verify weather in {0} is {1}")
    public void verifyWeatherInCityIs(String city, String weather) {
        RestResponse response = weatherRestServiceClient.getWeatherByCityName(city);
        ForecastDTO actualForecastDTO = response.as(ForecastDTO.class);
        setSessionVariable(RESPONSE_DTO).to(actualForecastDTO);
        assertEquals(weather, Arrays.asList(actualForecastDTO.getWeatherDTO()).get(0).getMain());
    }

    @Step("verify weather in {0} is {1}")
    public void verifyDetailedWeatherInCityIs(String city, String weather) {
        RestResponse response = weatherRestServiceClient.getWeatherByCityName(city);
        ForecastDTO actualForecastDTO = response.as(ForecastDTO.class);
        assertEquals(weather, Arrays.asList(actualForecastDTO.getWeatherDTO()).get(0).getDescription());
    }

    @Step("#User gets forecast by City Name {0}")
    public void userGetsForecastByCityName(String cityName) {
        RestResponse response = weatherRestServiceClient.getWeatherByCityName(cityName);
        setSessionVariable(RESPONSE).to(response);
    }


    @Step("#{0} user gets forecast by City Name {1}")
    public void foreignUserGetsForecastByCityName(String language, String cityName) {
        RestResponse response = weatherRestServiceClient.getWeatherByCityNameInForeignLanguage(getCodeByLanguage(language), cityName);
        setSessionVariable(RESPONSE).to(response);
    }

    @Step("#verify status code is {0}")
    public void verifyStatusCodeIs(int statusCode) {
        RestResponse response = sessionVariableCalled(RESPONSE);
          assertEquals(response.getStatusCode(), statusCode);
    }

    @SneakyThrows
    @Step("#verify country of the city is {0}")
    public void verifyCountryOfTheCityIs(String country) {
        RestResponse response = sessionVariableCalled(RESPONSE);
        ForecastDTO actualForecastDTO = response.as(ForecastDTO.class);
        assertEquals(country, actualForecastDTO.getSysDTO().getCountry());
    }
}
