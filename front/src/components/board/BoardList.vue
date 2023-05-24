<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary"><h3>글목록</h3></div>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">카테고리</th>
              <th scope="col">글 번호</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">작성일</th>
              <th scope="col">조회수</th>
            </tr>
          </thead>
          <tbody>
            <!-- 하위 component인 BoardListItem에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.article_no"
              @click="viewArticle(article)"
              :class="{ 'table-active': article.category === 'notice' }"
              v-bind="article"
            />
          </tbody>
        </table>
      </div>
    </div>
    <div class="row mb-1">
      <div class="col">
        <div class="input-group mb-3">
          <button
            type="button"
            class="btn btn-outline-secondary"
            id="categoryButton"
          >
            분류 선택
          </button>
          <button
            type="button"
            class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <span class="visually-hidden">Toggle Dropdown</span>
          </button>
          <ul class="dropdown-menu">
            <li>
              <a class="dropdown-item" @click="updateButton('작성자')"
                >작성자</a
              >
            </li>
            <li><hr class="dropdown-divider" /></li>
            <li>
              <a class="dropdown-item" @click="updateButton('제목')">제목</a>
            </li>

            <li><hr class="dropdown-divider" /></li>
            <li>
              <a class="dropdown-item" @click="updateButton('내용')">내용</a>
            </li>
            <li><hr class="dropdown-divider" /></li>
            <li>
              <a class="dropdown-item" @click="updateButton('제목+내용')"
                >제목+내용</a
              >
            </li>
          </ul>
          <input
            type="text"
            v-model="param.word"
            class="form-control"
            aria-label="Text input with segmented dropdown button"
          />
          <div class="input-group-append">
            <button class="btn btn-outline-primary" @click="moveSearch()">
              검색
            </button>

            <button
              class="btn btn-outline-primary"
              style="margin-left: 5px"
              @click="moveWrite()"
            >
              글쓰기
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-html="navigation.navigator" @click="movePage()"></div>
  </div>
</template>

<script>
import { listArticle } from "@/api/board";
import { apiInstance } from "@/api/axios";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      navigation: {},
      param: {},
    };
  },
  created() {
    listArticle(
      ({ data }) => {
        this.articles = data.articles;
        this.navigation = data.navigation;
        this.param = data.param;
        console.log("listArticle created()");
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({
        name: "boardwrite",
      });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: {
          category: article.category,
          category_no: article.category_no,
        },
      });
    },
    moveSearch() {
      console.log("move search");
      if (this.param.word == "") {
        this.$router.go();
      } else {
        apiInstance()
          .get(`/home/board`, {
            params: this.param,
          })
          .then(({ data }) => {
            this.articles = data.articles;
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    movePage() {
      let pages = document.querySelectorAll(
        ".pagination .page-item .page-link[move-pg]"
      );
      pages.forEach((page) => {
        page.addEventListener("click", () => {
          let nextPage = page.getAttribute("move-pg");
          this.$router.push({
            path: 'name: "boardlist"',
            query: { page: nextPage },
          });
        });
      });
    },
    updateButton(text) {
      if (text === "작성자") {
        this.param.type = "user_id";
      }
      if (text === "제목") {
        this.param.type = "title";
      }
      if (text === "내용") {
        this.param.type = "content";
      }
      if (text === "제목+내용") {
        this.param.type = "title_content";
      }
      document.getElementById("categoryButton").textContent = text;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdTitle {
  width: 300px;
  text-align: left;
}
</style>
