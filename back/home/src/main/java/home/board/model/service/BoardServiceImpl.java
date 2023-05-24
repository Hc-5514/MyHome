package home.board.model.service;

import home.board.model.BoardDto;
import home.board.model.BoardParameterDto;
import home.board.model.dao.BoardDao;
import home.board.util.BoardPageNavigation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean writeBoardArticle(BoardDto boardDto) throws Exception {
        if (boardDto.getTitle() == null || boardDto.getContent() == null) {
            throw new Exception();
        }
        return sqlSession.getMapper(BoardDao.class).writeBoardArticle(boardDto) == 1;
    }

    @Override
    public List<BoardDto> listBoardArticle(BoardParameterDto boardParameterDto) throws Exception {
        int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
        boardParameterDto.setStart(start);
        return sqlSession.getMapper(BoardDao.class).listBoardArticle(boardParameterDto);
    }

    @Override
    public BoardPageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
        int naviSize = 5;
        BoardPageNavigation pageNavigation = new BoardPageNavigation();
        pageNavigation.setCurrentPage(boardParameterDto.getPg());
        pageNavigation.setNaviSize(naviSize);
        int totalCount = sqlSession.getMapper(BoardDao.class).getTotalCount(boardParameterDto);//총글갯수  269
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = boardParameterDto.getPg() <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();
        return pageNavigation;
    }

    @Override
    public BoardDto getBoardArticle(String category, int category_no) throws Exception {
        return sqlSession.getMapper(BoardDao.class).getBoardArticle(category, category_no);
    }

    @Override
    public boolean updateHit(String category, int category_no) throws Exception {
        return sqlSession.getMapper(BoardDao.class).updateHit(category, category_no) == 1;
    }

    @Override
    public boolean updateBoardArticle(BoardDto boardDto) throws Exception {
        return sqlSession.getMapper(BoardDao.class).updateBoardArticle(boardDto) == 1;
    }

    @Override
    public boolean deleteBoardArticle(String category, int category_no) throws Exception {
        return sqlSession.getMapper(BoardDao.class).deleteBoardArticle(category, category_no) == 1;
    }
}
