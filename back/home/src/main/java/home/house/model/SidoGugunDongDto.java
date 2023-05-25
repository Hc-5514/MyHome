package home.house.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "SidoGugunDongDto: 시도, 구군, 동 정보", description = "시도, 구군, 동 선택할 때 사용")
public class SidoGugunDongDto {
    @ApiModelProperty(value = "동 코드")
    private String dongCode;
    @ApiModelProperty(value = "시도 이름")
    private String sidoName;
    @ApiModelProperty(value = "구군 이름")
    private String gugunName;
    @ApiModelProperty(value = "동 이름")
    private String dongName;
}
