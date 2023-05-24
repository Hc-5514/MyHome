package home.board.model.dao;

import home.board.model.BoardDto;
import home.board.model.BoardParameterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardDao {
    public int writeBoardArticle(BoardDto boardDto) throws SQLException;

    public List<BoardDto> listBoardArticle(BoardParameterDto boardParameterDto) throws SQLException; // 목록 - 페이징 처리 구현 예정

    public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;

    public BoardDto getBoardArticle(@Param("category") String category, @Param("category_no") int category_no) throws SQLException;

    public int updateHit(@Param("category") String category, @Param("category_no") int category_no) throws SQLException;

    public int updateBoardArticle(BoardDto boardDto) throws SQLException;

    public int deleteBoardArticle(@Param("category") String category, @Param("category_no") int category_no) throws SQLException;
}
