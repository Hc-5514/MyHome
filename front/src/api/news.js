import { apiInstance } from './axios.js';

const api = apiInstance();

// 뉴스 글 목록
function listArticle(success, fail) {
  api.get(`/home/news`).then(success).catch(fail);
}

// 뉴스 크롤링 요청
function crawlArticle(success, fail) {
  api.post(`/home/news/crawl`).then(success).catch(fail);
}

export { listArticle, crawlArticle };
