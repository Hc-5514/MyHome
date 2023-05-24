package home.news.model.service;

import home.news.model.NewsDto;
import home.news.model.NewsParameterDto;

import java.util.List;

public interface NewsService {
    public int countDate() throws Exception;

    public int saveNewsArticle() throws Exception;

    public List<NewsDto> listNewsArticle(NewsParameterDto newsParameterDto) throws Exception;
}
