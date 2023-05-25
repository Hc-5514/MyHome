package home.house.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "HouseDealDto: 아파트 정보", description = "아파트 상세 정보")
public class HouseDealDto {
    @ApiModelProperty(value = "매매 가격")
    private String dealAmount;
    @ApiModelProperty(value = "면적")
    private String area;
    @ApiModelProperty(value = "아파트 일련 번호")
    private int aptCode;
    @ApiModelProperty(value = "시도 이름")
    private String sidoName;
    @ApiModelProperty(value = "구군 이름")
    private String gugunName;
    @ApiModelProperty(value = "동 이름")
    private String dongName;
    @ApiModelProperty(value = "지번")
    private String jibun;
    @ApiModelProperty(value = "아파트 이름")
    private String apartmentName;
    @ApiModelProperty(value = "동 코드")
    private String dongCode;
    @ApiModelProperty(value = "경도")
    private String lng;
    @ApiModelProperty(value = "위도")
    private String lat;
}
