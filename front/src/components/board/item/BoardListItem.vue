<template>
  <tr>
    <td>{{ formattedCategory }}</td>
    <td>{{ category_no }}</td>
    <td class="text-left">
      <router-link
        :to="{
          name: 'boardview',
          params: {
            category: category,
            category_no: category_no,
          },
        }"
        class="custom-link"
      >
        {{ title }}
      </router-link>
    </td>
    <td>
      <span v-if="formattedCategory === '공지사항'">
        <i class="bi bi-person-circle"></i>
      </span>
      {{ user_id }}
    </td>
    <td>{{ regtime }}</td>
    <td>{{ hit }}</td>
  </tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListItem",
  computed: {
    formattedCategory() {
      if (this.category === "notice") {
        return "공지사항";
      } else if (this.category === "qna") {
        return "Q&A";
      }
      return this.category;
    },
  },
  props: {
    category: String,
    category_no: Number,
    title: String,
    user_id: String,
    regtime: String,
    hit: Number,
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY년 MM월 DD일");
  //   },
  // },
};
</script>

<style>
.custom-link {
  text-decoration: none;
}
</style>
