package starter.api;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import starter.dto.ForecastDTO;
import starter.dto.WeatherDTO;
import starter.steps.WeatherApiSteps;

@RunWith(SerenityRunner.class)
public class WeatherTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Steps
    WeatherApiSteps weatherApiSteps = new WeatherApiSteps();

    @Test
    public void verifyUserCanGetWeatherByCityId() {
        weatherApiSteps.verifyUserCanGetWeatherByCityIdTest(2172797);
    }

    @JsonIgnoreProperties
    @ParameterizedTest
    @JsonFileSource(resources = "/expected1.json")
    public void verifyUserCanGetWeatherByCityNamesTest(JsonObject jsonObject)
          throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ForecastDTO forecastDTO = objectMapper.readValue(jsonObject.toString(), ForecastDTO.class);
        // Given
        weatherApiSteps.userGetsForecastByCityName(forecastDTO.getName());

        // When
        weatherApiSteps.verifyStatusCodeIs(SC_OK);

        // Then
        weatherApiSteps.verifyWeatherInCityIs(forecastDTO.getName(), Arrays.asList(forecastDTO.getWeatherDTO()).get(0).getMain());
        weatherApiSteps.verifyDetailedWeatherInCityIs(forecastDTO.getName(), Arrays.asList(forecastDTO.getWeatherDTO()).get(0).getDescription());
        weatherApiSteps.verifyCountryOfTheCityIs(forecastDTO.getSysDTO().getCountry());
    }

//    static Stream<Arguments> forecastRuData() {
//        ForecastDTO newYorkCityForecast = new ForecastDTO();
//        newYorkCityForecast.setName("Москва");
//        WeatherDTO newYorkCityWeather = new WeatherDTO();
//        newYorkCityWeather.setDescription("небольшой дождь");
//        WeatherDTO[] newYorkCityWeathers = new WeatherDTO[1];
//        newYorkCityWeathers[0] = newYorkCityWeather;
//        newYorkCityForecast.setWeatherDTO(newYorkCityWeathers);
//
//        ForecastDTO newYorkForecast = new ForecastDTO();
//        newYorkForecast.setName("New York");
//        WeatherDTO newYorkWeather = new WeatherDTO();
//        newYorkWeather.setDescription("небольшой дождь");
//        WeatherDTO[] newYorkWeathers = new WeatherDTO[1];
//        newYorkWeathers[0] = newYorkWeather;
//        newYorkForecast.setWeatherDTO(newYorkWeathers);
//
//        return Stream.of(
//              arguments("Russian", newYorkCityForecast),
//              arguments("Russian", newYorkForecast)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("forecastRuData")
//    public void verifyForeignUserCanGetWeatherByCityName(String language, ForecastDTO forecast) {
//        // Given
//        weatherApiSteps.foreignUserGetsForecastByCityName(language, forecast.getName());
//
//        // When
//        weatherApiSteps.verifyStatusCodeIs(SC_OK);
//
//        // Then
//        weatherApiSteps.verifyDetailedWeatherInCityIs(forecast.getName(), Arrays.asList(forecast.getWeatherDTO()).get(0).getDescription());
//    }
}