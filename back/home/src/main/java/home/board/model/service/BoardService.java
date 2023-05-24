package home.board.model.service;

import home.board.model.BoardDto;
import home.board.model.BoardParameterDto;
import home.board.util.BoardPageNavigation;

import java.util.List;

public interface BoardService {
    public boolean writeBoardArticle(BoardDto boardDto) throws Exception;

    public List<BoardDto> listBoardArticle(BoardParameterDto boardParameterDto) throws Exception;

    public BoardPageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;

    public BoardDto getBoardArticle(String category, int category_no) throws Exception;

    public boolean updateHit(String category, int category_no) throws Exception;

    public boolean updateBoardArticle(BoardDto boardDto) throws Exception;

    public boolean deleteBoardArticle(String category, int category_no) throws Exception;
}
