<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary"><h3>글보기</h3></div>
      </div>
    </div>
    <div class="row mb-1">
      <div class="col text-start">
        <button class="btn btn-outline-primary" @click="moveList">목록</button>
      </div>
      <!-- <div class="col text-end" v-if="userInfo.userid === article.userid">-->
      <div class="col text-end">
        <button
          class="btn btn-outline-info btn-sm mr-2"
          @click="moveUpdateArticle"
        >
          글수정
        </button>
        <button class="btn btn-outline-danger btn-sm" @click="deleteArticle">
          글삭제
        </button>
      </div>
    </div>
    <div class="row mb-1">
      <div class="col">
        <div class="card border-dark mb-2">
          <div class="card-header">
            <!-- 공지사항, QnA 중 알아서 나오게끔 변경 해야 함 -->
            <h3>
              [공지사항/Q&A] No.{{ article.category_no }} {{ article.title }}
            </h3>
          </div>
          <div class="card-header">
            <h6>
              {{ article.user_id }} {{ article.regtime }} {{ article.hit }}
            </h6>
          </div>
          <div class="card-body text-start">
            <div v-html="message"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/board";
// import { mapState } from "vuex";

// const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    // ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.category,
      this.$route.params.category_no,
      ({ data }) => {
        console.log(data);
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveUpdateArticle() {
      this.$router.replace({
        name: "boardupdate",
        params: {
          category: this.article.category,
          category_no: this.article.category_no,
        },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: {
            category: this.article.category,
            category_no: this.article.category_no,
          },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
