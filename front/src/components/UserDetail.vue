<template>
  <div class="container-fluid vh-100" style="margin-top: 100px">
            <div class="" style="margin-top: 200px">
                <div class="rounded d-flex justify-content-center">
                    <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light"
                        style="width: 600px">
                        <div class="text-center">
                            <h3 class="text-dark">마이페이지</h3>
                        </div>
                        <div class="p-7">
                            <!-- id -->
                            <div class="input-group mb-3">
                                <span class="input-group-text" style="background-color: #929292;"> 
                                    <i class="bi bi-person-plus-fill text-white"></i>
                                    아이디
                                </span>
                                <input
                                    id="userid" type="text" class="form-control"
                                    v-model="id" disabled>
                            </div>

                            <!-- 비밀번호 -->
                            <div class="input-group mb-3">
                                <span class="input-group-text"
                                    style="background-color: #929292;"> 
                                    <i class="bi bi-person-plus-fill text-white"></i>
                                    비밀번호
                                </span> 
                                    
                                    <input
                                    id="userid" type="text" class="form-control"
                                    v-model="password" :disabled="disabled">
                            </div>

                            <!-- 이름 -->
                            <div class="input-group mb-3">
                                <span class="input-group-text"
                                    style="background-color: #929292;"> 
                                    <i class="bi bi-person-plus-fill text-white"></i>
                                    이름
                                </span>
                                    <input
                                    id="username" type="text" class="form-control"
                                    v-model="name" :disabled="disabled">
                            </div>

                            <!-- 주소 -->
                            <div class="input-group mb-3">
                                <span class="input-group-text"
                                    style="background-color: #929292;"> 
                                    <i class="bi bi-person-plus-fill text-white"></i>
                                이메일
                                </span> <input
                                    id="email" type="text" class="form-control"
                                    v-model="email" :disabled="disabled">
                            </div>


                            <form style='display: inline'>
                                <input value="수정" class="btn btn-primary text-center mt-2">
                            </form>


                            <form style='display: inline'>
                                <input value="회원탈퇴" class="btn btn-danger text-center mt-2">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</template>

<script>
import { findById } from "@/api/user";
// import { mapGetters } from "vuex";

export default {
    name: "userDetail",
    data() {
        return {
            id: "",
            password: "",
            name:"",
            email: "",
            disabled: false,
        }
    },
    computed: {
    //     ...mapGetters([
    //   'get_user_id',
    // ])
    }
    ,
    created() {
        const user_id = this.$store.state.userStore.user_id
        
        findById(user_id, ({ data }) => {
            const user = data.userInfo
            this.id = user.id
            this.password = user.password
            this.name = user.name
            this.email = user.email
        }, () => {
            console.log('X')
        })
    },
}
</script>

<style>

</style>