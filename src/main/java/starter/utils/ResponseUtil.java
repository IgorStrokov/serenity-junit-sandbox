package starter.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import starter.dto.ForecastDTO;

public class ResponseUtil {

    public ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public ForecastDTO parseJson(Response response) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper.readValue(response.getBody().asString(), ForecastDTO.class);
    }

}
