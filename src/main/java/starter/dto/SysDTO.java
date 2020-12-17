package starter.dto;

import lombok.Data;

@Data
public class SysDTO {
    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
