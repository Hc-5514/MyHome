<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary"><h3>뉴스 목록</h3></div>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">뉴스 번호</th>
              <th scope="col" colspan="2">뉴스 제목</th>
              <th scope="col">뉴스 내용</th>
              <th scope="col">신문사</th>
              <th scope="col">발행일</th>
            </tr>
          </thead>
          <tbody>
            <news-list-item
              v-for="article in articles"
              :key="article.article_no"
              @click="viewArticle(article)"
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
              <a class="dropdown-item" @click="updateButton('신문사')"
                >신문사</a
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
              @click="crawlArticle()"
            >
              스크랩
            </button>
          </div>
        </div>
      </div>
    </div>
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <li class="page-item">
          <a class="page-link" @click="moveToPage(0)" aria-label="처음으로">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <a class="page-link" @click="moveToPage(0)">{{ day[0] }}</a>
        <a class="page-link" @click="moveToPage(1)">{{ day[1] }}</a>
        <a class="page-link" @click="moveToPage(2)">{{ day[2] }}</a>
        <a class="page-link" @click="moveToPage(3)">{{ day[3] }}</a>
        <a class="page-link" @click="moveToPage(4)">{{ day[4] }}</a>
        <a class="page-link" @click="moveToPage(5)">{{ day[5] }}</a>
        <a class="page-link" @click="moveToPage(6)">{{ day[6] }}</a>
        <a class="page-link" @click="moveToPage(7)">{{ day[7] }}</a>
        <a class="page-link" @click="moveToPage(8)">{{ day[8] }}</a>
        <a class="page-link" @click="moveToPage(9)">{{ day[9] }}</a>
        <li class="page-item">
          <a class="page-link" @click="moveToPage(9)" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { listArticle, crawlArticle } from "@/api/news";
import { apiInstance } from "@/api/axios";
import NewsListItem from "@/components/news/item/NewsListItem";

export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      articles: [],
      param: {},
      day: [],
    };
  },
  mounted() {
    this.getDay();
  },
  created() {
    listArticle(
      ({ data }) => {
        this.articles = data.articles;
        this.param = data.param;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    crawlArticle() {
      console.log("crawlArticle 실행");
      crawlArticle(
        ({ data }) => {
          let msg = "최신 상태입니다.";
          if (data === "success") {
            msg = "크롤링이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveSearch() {
      if (this.param.word == "") {
        this.$router.go();
      } else {
        apiInstance()
          .get(`/home/news`, {
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
    moveToPage(pageNumber) {
      apiInstance()
        .get(`/home/news`, {
          params: {
            pg: pageNumber,
          },
        })
        .then(({ data }) => {
          this.articles = data.articles;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateButton(text) {
      if (text === "신문사") {
        this.param.type = "company";
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
    getDay() {
      const today = new Date();
      const options = { month: "long", day: "numeric" };

      for (let i = 1; i <= 10; i++) {
        const date = new Date(today);
        date.setDate(date.getDate() - i);
        this.day.push(date.toLocaleDateString(undefined, options));
      }
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