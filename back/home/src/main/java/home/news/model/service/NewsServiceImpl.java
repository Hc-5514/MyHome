package home.news.model.service;

import home.news.model.NewsDto;
import home.news.model.NewsParameterDto;
import home.news.model.dao.NewsDao;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int countDate() throws Exception {
        return sqlSession.getMapper(NewsDao.class).countDate();
    }

    @Override
    public int saveNewsArticle() throws Exception {
        // 크롤링 최신 날짜 확인 후, 직전 날까지 크롤링 진행
        int today = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int cnt = this.countDate();
        for (int i = cnt; i >= 1; i--) {
            int crawlDate = today - i;
            String url = "https://land.naver.com/news/breaking.naver?bss_ymd=" + crawlDate + "&listType=1";
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "section_headline NEI=a:lst.title");
            Element element = elements.get(0);
            Elements liElements = element.select("li");

            // 매일 기사 10개씩 크롤링
            for (int j = 0; j < 10; j++) {
                NewsDto newsDto = new NewsDto();
                // <dt> 태그로 분리
                Elements articleElements = liElements.get(j).select("dt");

                // 뉴스 원본 링크 주소 저장
                Element aElement = articleElements.select("a").get(0);
                newsDto.setArticle_url("https://land.naver.com" + aElement.attr("href")); // 뉴스 원본 Url

                // class="photo"가 있을 경우, 이미지 저장
                if (articleElements.size() == 2) {
                    Element imgElement = aElement.select("img").get(0);
                    newsDto.setImg_url(imgElement.attr("src")); // 뉴스 이미지
                    newsDto.setTitle(articleElements.select("a").get(1).text()); // 뉴스 제목
                }

                // class="photo"가 없을 경우
                if (articleElements.size() == 1) {
                    newsDto.setTitle(articleElements.select("a").get(0).text()); // 뉴스 제목
                }

                Element ddElement = element.select("dd").get(j);
                newsDto.setContent(ddElement.text()); // 뉴스 내용
                newsDto.setCompany(ddElement.getElementsByAttributeValue("class", "writing").text()); // 신문사
                newsDto.setDate(ddElement.getElementsByAttributeValue("class", "date").text()); // 발행일
                sqlSession.getMapper(NewsDao.class).saveNewsArticle(newsDto);
            }
        }
        return cnt;
    }

    @Override
    public List<NewsDto> listNewsArticle(NewsParameterDto newsParameterDto) throws Exception {
        int pg = newsParameterDto.getPg() == 0 ? 0 : newsParameterDto.getPg() * 10;
        newsParameterDto.setPg(pg);
        return sqlSession.getMapper(NewsDao.class).listNewsArticle(newsParameterDto);
    }
}
