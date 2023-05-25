<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link :to="{ name: 'board' }" class="nav-link">게시판 </router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'news' }" class="nav-link">뉴스 </router-link>
        </li>
        <li class="nav-item" v-if="is_login == false ">
          <router-link :to="{ name: 'login' }" class="nav-link">로그인 </router-link>
        </li>
        <li class="nav-item" v-if="is_login == true " @click="try_logout">
          <a :to="{ name: 'login' }" class="nav-link">로그아웃 </a>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'register' }" class="nav-link">회원가입 </router-link>
        </li>
      </ul>
      <di>{{ access_token }}</di>
      
    </div>
  </div>
</nav>
</template>

<script>
import { logout } from "@/api/user";
import { mapState } from "vuex";
import { mapActions } from "vuex";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      token: "",
    };
  },
  computed: {
    ...mapState("userStore", ['is_login']),
  },
  methods: {
    ...mapActions("userStore", ["logout"]),
    async try_logout() {
      console.log('logout')
      logout(() => {
        sessionStorage.setItem("access-token", null);
        sessionStorage.setItem("refresh-token", null);
        this.logout()
      },
      () => {
        console.log('logout fail')
        this.logout()
      })
    }
  }
}
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}
</style>
