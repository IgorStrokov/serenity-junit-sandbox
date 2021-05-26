package starter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysDTO {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
