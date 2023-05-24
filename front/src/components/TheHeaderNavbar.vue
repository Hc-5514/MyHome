<template>
  <div>
    <router-link :to="{ name: 'board' }" class="m-2 link">게시판 </router-link>
    <router-link :to="{ name: 'news' }" class="m-2 link">뉴스 </router-link>
    <router-link :to="{ name: 'login' }" class="m-2 link">로그인 </router-link>
    <router-link :to="{ name: 'register' }" class="m-2 link">회원가입 </router-link>
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
