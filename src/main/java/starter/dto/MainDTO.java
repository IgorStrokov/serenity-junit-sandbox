package starter.dto;

import lombok.Data;

@Data
public class MainDTO {
    private String temp;
    private String feelsLike;
    private String tempMin;
    private String tempMax;
    private String pressure;
    private String humidity;
}
