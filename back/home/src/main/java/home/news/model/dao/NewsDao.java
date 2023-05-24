package home.news.model.dao;

import home.news.model.NewsDto;
import home.news.model.NewsParameterDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface NewsDao {

    public int countDate() throws SQLException;

    public int saveNewsArticle(NewsDto newsDto) throws SQLException;

    public List<NewsDto> listNewsArticle(NewsParameterDto newsParameterDto) throws SQLException;
}
