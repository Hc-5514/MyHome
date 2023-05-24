package home.news.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "NewsParameterDto: 뉴스 파라미터 정보", description = "뉴스 글을 얻기 위한 부가적인 파라미터 정보")
public class NewsParameterDto {

    @ApiModelProperty(value = "현재 페이지 번호")
    private int pg;
    @ApiModelProperty(value = "검색 조건")
    private String type;
    @ApiModelProperty(value = "검색어")
    private String word;
}
