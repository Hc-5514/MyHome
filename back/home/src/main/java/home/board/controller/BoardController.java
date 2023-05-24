package home.board.controller;

import home.board.model.BoardDto;
import home.board.model.BoardParameterDto;
import home.board.model.service.BoardService;
import home.board.util.BoardPageNavigation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "게시판 글 작성", notes = "새로운 게시글 정보 입력, 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
    @PostMapping
    public ResponseEntity<String> writeBoardArticle(@RequestBody @ApiParam(value = "게시글 정보", required = true) BoardDto boardDto) throws Exception {
        logger.info("writeBoardArticle - 호출");
        if (boardService.writeBoardArticle(boardDto)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "게시판 글 목록", notes = "모든 게시글의 정보를 반환", response = List.class)
    @GetMapping
    public ResponseEntity<HashMap<String, Object>> listBoardArticle(@ApiParam(value = "게시글을 얻기 위한 부가 정보", required = true) BoardParameterDto boardParameterDto) throws Exception {
        logger.info("listBoardArticle - 호출");
        List<BoardDto> articles = boardService.listBoardArticle(boardParameterDto);
        BoardPageNavigation boardPageNavigation = boardService.makePageNavigation(boardParameterDto);
        HashMap<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("navigation", boardPageNavigation);
        map.put("param", boardParameterDto);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글 보기", notes = "카테고리와 글 번호가 일치하는 게시글 정보 반환", response = BoardDto.class)
    @GetMapping("/{category}/{category_no}")
    public ResponseEntity<BoardDto> getBoardArticle(@PathVariable @ApiParam(value = "확인할 글의 카테고리", required = true) String category, @PathVariable @ApiParam(value = "확인할 글의 번호", required = true) int category_no) throws Exception {
        logger.info("getBoardArticle - 호출: 카테고리(" + category + "), 글 번호(" + category_no + ")");
        boardService.updateHit(category, category_no);
        return new ResponseEntity<>(boardService.getBoardArticle(category, category_no), HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글 수정", notes = "수정할 게시글 정보 입력, 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
    @PutMapping
    public ResponseEntity<String> updateBoardArticle(@RequestBody @ApiParam(value = "수정할 글 정보", required = true) BoardDto boardDto) throws Exception {
        logger.info("updateArticle - 호출");
        if (boardService.updateBoardArticle(boardDto)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.OK);
    }

    @ApiOperation(value = "게시판 글 삭제", notes = "카테고리와 글 번호가 일치하는 게시글 정보 삭제, 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환", response = BoardDto.class)
    @DeleteMapping("/{category}/{category_no}")
    public ResponseEntity<String> deleteBoardArticle(@PathVariable @ApiParam(value = "삭제할 글의 카테고리", required = true) String category, @PathVariable @ApiParam(value = "삭제할 글의 번호", required = true) int category_no) throws Exception {
        logger.info("deleteArticle - 호출");
        if (boardService.deleteBoardArticle(category, category_no)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }
}
