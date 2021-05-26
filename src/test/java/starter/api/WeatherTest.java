package starter.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.joshka.junit.json.params.JsonFileSource;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import starter.dto.ForecastDTO;
import starter.services.WeatherRestServiceClient;
import starter.steps.WeatherApiSteps;

import javax.json.JsonObject;
import java.util.Arrays;

import static org.apache.http.HttpStatus.SC_OK;

@Log4j2
@RunWith(SerenityRunner.class)
public class WeatherTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Steps
    private WeatherApiSteps weatherApiSteps = new WeatherApiSteps(new WeatherRestServiceClient());

    @Test
    public void verifyUserCanGetWeatherByCityId() {
        weatherApiSteps.verifyUserCanGetWeatherByCityIdTest(2172797);
    }

    @SneakyThrows
    @ParameterizedTest
    @JsonFileSource(resources = "/expected1.json")
    public void verifyUserCanGetWeatherByCityNamesTest(JsonObject jsonObject) {
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