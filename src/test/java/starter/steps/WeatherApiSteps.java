package starter.steps;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assert.assertEquals;
import static starter.enums.Context.RESPONSE;
import static starter.enums.Languages.getCodeByLanguage;
import static starter.services.WeatherRestServiceClient.getWeatherByCityId;
import static starter.services.WeatherRestServiceClient.getWeatherByCityName;
import static starter.services.WeatherRestServiceClient.getWeatherByCityNameInForeignLanguage;

import io.restassured.response.Response;
import java.util.Arrays;
import lombok.SneakyThrows;
import net.thucydides.core.annotations.Step;
import starter.dto.ForecastDTO;
import starter.utils.ResponseUtil;


public class WeatherApiSteps {
    public ResponseUtil responseUtil;


    @Step("verify user can get weather by city id {0}")
    public void verifyUserCanGetWeatherByCityIdTest(int id) {
        Response response = getWeatherByCityId(id);
        assertEquals(response.getStatusCode(), 200);
    }

    @Step("verify weather in {0} is {1}")
    public void verifyWeatherInCityIs(String city, String weather) {
        Response response = getWeatherByCityName(city);
        ForecastDTO actualForecastDTO = responseUtil.parseJson(response);
        assertEquals(weather, Arrays.asList(actualForecastDTO.getWeatherDTO()).get(0).getMain());
    }

    @Step("verify weather in {0} is {1}")
    public void verifyDetailedWeatherInCityIs(String city, String weather) {
        Response response = getWeatherByCityName(city);
        ForecastDTO actualForecastDTO = responseUtil.parseJson(response);
        assertEquals(weather, Arrays.asList(actualForecastDTO.getWeatherDTO()).get(0).getDescription());
    }

    @Step("#User gets forecast by City Name {0}")
    public void userGetsForecastByCityName(String cityName) {
        Response response = getWeatherByCityName(cityName);
        setSessionVariable(RESPONSE).to(response);
    }


    @Step("#{0} user gets forecast by City Name {1}")
    public void foreignUserGetsForecastByCityName(String language, String cityName) {
        Response response = getWeatherByCityNameInForeignLanguage(getCodeByLanguage(language), cityName);
        setSessionVariable(RESPONSE).to(response);
    }

    @Step("#verify status code is {0}")
    public void verifyStatusCodeIs(int statusCode) {
        Response response = sessionVariableCalled(RESPONSE);
          assertEquals(response.getStatusCode(), statusCode);
    }

    @SneakyThrows
    @Step("#verify country of the city is {0}")
    public void verifyCountryOfTheCityIs(String country) {
        Response response = sessionVariableCalled(RESPONSE);
        ForecastDTO actualForecastDTO = responseUtil.parseJson(response);
        assertEquals(country, actualForecastDTO.getSysDTO().getCountry());
    }
}
