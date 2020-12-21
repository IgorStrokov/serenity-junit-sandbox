package starter.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import starter.dto.ForecastDTO;

@AllArgsConstructor
public class ResponseUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static ForecastDTO parseJson(Response response) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper.readValue(response.getBody().asString(), ForecastDTO.class);
    }

}
