import { apiInstance } from './axios.js';

const api = apiInstance();

// 게시판 글 작성
function writeArticle(article, success, fail) {
  api.post(`/home/board`, JSON.stringify(article)).then(success).catch(fail);
}

// 게시판 글 목록
function listArticle(success, fail) {
  api.get(`/home/board`).then(success).catch(fail);
}

// 게시판 상세 글
function getArticle(category, category_no, success, fail) {
  api.get(`/home/board/${category}/${category_no}`).then(success).catch(fail);
}

// 게시판 글 수정
function updateArticle(article, success, fail) {
  api.put(`/home/board`, JSON.stringify(article)).then(success).catch(fail);
}

// 게시판 글 삭제
function deleteArticle(category, category_no, success, fail) {
  api.delete(`/home/board/${category}/${category_no}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, updateArticle, deleteArticle };
