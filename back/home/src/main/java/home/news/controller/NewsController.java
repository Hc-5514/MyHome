package home.news.controller;

import home.news.model.NewsDto;
import home.news.model.NewsParameterDto;
import home.news.model.service.NewsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "뉴스 크롤링", notes = "직전 날까지 부동산 관련 뉴스 기사 10개씩 크롤링", response = String.class)
    @PostMapping("/crawl")
    public ResponseEntity<String> saveNewsArticle() throws Exception {
        int n = newsService.saveNewsArticle();
        if (n >= 1) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "뉴스 목록", notes = "모든 뉴스의 정보를 반환")
    @GetMapping
    public ResponseEntity<HashMap<String, Object>> listNewsArticle(@ApiParam(value = "뉴스 글을 얻기 위한 부가 정보", required = true)NewsParameterDto newsParameterDto) throws Exception {
        List<NewsDto> articles = newsService.listNewsArticle(newsParameterDto);
        HashMap<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("param", newsParameterDto);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
