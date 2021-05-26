package starter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDTO {
    private String temp;
    private String feelsLike;
    private String tempMin;
    private String tempMax;
    private String pressure;
    private String humidity;
}
