<template>
  <div class="container mt-3">
    <div class="row">
      <div class="col">
        <div class="alert alert-primary"><h3>List</h3></div>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <div class="alert alert-danger">Processing deletion...</div>
      </div>
    </div>
  </div>
</template>

<script>
import { deleteArticle } from "@/api/board";

export default {
  name: "BoardDelete",
  created() {
    // let param = this.$route.params.articleno;
    deleteArticle(
      this.$route.params.category,
      this.$route.params.category_no,
      console.log("BoardDelete.vue 실행"),
      console.log(this.$route.params.category),
      console.log(this.$route.params.category_no),
      ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        console.log(data);
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "boardlist" });
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
