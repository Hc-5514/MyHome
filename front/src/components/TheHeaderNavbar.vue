<template>
  <div>
    <router-link :to="{ name: 'board' }" class="m-2 link">게시판 </router-link>
    <router-link :to="{ name: 'news' }" class="m-2 link">뉴스 </router-link>
  </div>
</template>

<script>
export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}
</style>
