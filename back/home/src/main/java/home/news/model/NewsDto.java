package home.news.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "NewsDto: 뉴스 크롤링 정보", description = "네이버 부동산 뉴스 크롤링 데이터")
public class NewsDto {
    @ApiModelProperty(value = "전체 뉴스 번호")
    private int article_no;
    @ApiModelProperty(value = "뉴스 제목")
    private String title;
    @ApiModelProperty(value = "뉴스 내용")
    private String content;
    @ApiModelProperty(value = "뉴스 이미지 Url")
    private String img_url;
    @ApiModelProperty(value = "뉴스 원본 Url")
    private String article_url;
    @ApiModelProperty(value = "신문사")
    private String company;
    @ApiModelProperty(value = "발행일")
    private String date;
}
