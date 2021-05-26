package starter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDTO {

    @JsonProperty("coord")
    private CoordDTO coordDTO = new CoordDTO();
    @JsonProperty("weather")
    private WeatherDTO[] weatherDTO = new WeatherDTO[]{};
    private String base;
    @JsonProperty("main")
    private MainDTO mainDTO = new MainDTO();
    private String visibility;
    private WindDTO windDTO = new WindDTO();
    @JsonProperty("clouds")
    private CloudsDTO cloudsDTO = new CloudsDTO();
    private int dt;
    @JsonProperty("sys")
    private SysDTO sysDTO = new SysDTO();
    private int timezone;
    private int id;
    private String name;
    private int cod;

}
