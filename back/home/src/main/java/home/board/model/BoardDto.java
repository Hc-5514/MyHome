package home.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "BoardDto: 게시글 정보", description = "게시글 상세 정보")
public class BoardDto {

    @ApiModelProperty(value = "전체 글 번호")
    private int article_no;
    @ApiModelProperty(value = "작성자 아이디")
    private String user_id;
    @ApiModelProperty(value = "글 제목")
    private String title;
    @ApiModelProperty(value = "글 내용")
    private String content;
    @ApiModelProperty(value = "작성일")
    private String regtime;
    @ApiModelProperty(value = "조회수")
    private int hit;
    @ApiModelProperty(value = "글 분류")
    private String category;
    @ApiModelProperty(value = "카테고리 별 글 번호")
    private int category_no;
}
