package starter.api;

import static org.apache.http.HttpStatus.SC_OK;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import starter.dto.ForecastDTO;
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

}