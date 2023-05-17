package home.apt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AptDto {
    private String apartmentName;
    private String buildYear;
    private String roadName;
    private String dong;
    private double lat;
    private double lng;
    private String dealAmount;
    private double area;
    private String dealYear;
    private String dealMonth;
    private String dealDay;
}
