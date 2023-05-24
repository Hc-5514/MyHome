<template>
  <div class="row mb-1">
    <div class="col" style="text-align: left">
      <form @submit="onSubmit" @reset="onReset">
        <div class="mb-3">
          <label for="title" class="form-label">제목:</label>
          <input
            id="title"
            v-model="article.title"
            type="text"
            class="form-control"
            required
          />
        </div>

        <div class="mb-3">
          <label for="content" class="form-label">내용:</label>
          <textarea
            id="content"
            v-model="article.content"
            class="form-control"
            rows="10"
            max-rows="15"
          ></textarea>
        </div>

        <button
          type="submit"
          class="btn btn-primary m-1"
          v-if="this.type === 'register'"
        >
          글작성
        </button>
        <button type="submit" class="btn btn-primary m-1" v-else>글수정</button>
        <button type="reset" class="btn btn-danger m-1">초기화</button>
      </form>
    </div>
  </div>
</template>


<script>
import { writeArticle, updateArticle, getArticle } from "@/api/board";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        // user_id, title, content, category, category_no
        user_id: "",
        category: "",
        category_no: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "update") {
      getArticle(
        this.$route.params.category,
        this.$route.params.category_no,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.updateArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.title = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      let param = {
        user_id: "tbd05050", // 추후 로그인 유저 값으로 수정해야 함
        title: this.article.title,
        content: this.article.content,
        category: "qna", // 추후 notice, qna 선택으로 수정 예정, 권한 따라 다름
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateArticle() {
      let param = {
        category: this.article.category,
        category_no: this.article.category_no,
        title: this.article.title,
        content: this.article.content,
      };
      console.log("param확인");
      console.log(param);
      updateArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({
            name: "boardview",
            params: {
              category: this.article.category,
              category_no: this.article.category_no,
            },
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
