<template>
  <tr>
    <td>{{ article_no }}</td>
    <img :src="img_url" alt="이미지가 없습니다." />
    <td>
      <a :href="article_url">{{ title }}</a>
    </td>
    <td>{{ content }}</td>
    <td>{{ company }}</td>
    <td>{{ date }}</td>
  </tr>
</template>

<script>
export default {
  name: "NewsListItem",
  props: {
    article_no: Number,
    img_url: String,
    article_url: String,
    title: String,
    content: String,
    company: String,
    date: String,
  },
};
</script>

<style></style>
