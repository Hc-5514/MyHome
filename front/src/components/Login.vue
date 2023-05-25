<template>
  <div>
    <div class="container-fluid vh-100" style="margin-top: 150px">
			<div class="" style="margin-top: 100px">
				<div class="rounded d-flex justify-content-center">
					<div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
						<div class="text-center">
							<h3 class="text-dark">로그인</h3>
						</div>

						<div class="p-4">
							
							<div class="input-group mb-3">
								<span class="input-group-text"
									style="background-color: #929292;"><i
									class="bi bi-person-plus-fill text-white"></i></span> <input
									type="text" class="form-control" placeholder="ID" v-model="id"
									>
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text"
									style="background-color: #929292;"><i
									class="bi bi-key-fill text-white"></i></span> <input 
									class="form-control" placeholder="password"
									v-model="password">
							</div>
							<button class="btn btn-primary text-center mt-2"
							@click = "try_login">Login</button>
						
							<p class="text-center mt-5">
								Don't have an account? <a href="">Sign Up</a>
							</p>
							Forgot your password? <a href="">Find Password</a>
						</div>
					</div>
				</div>
			</div>
		</div>

  </div>
</template>

<script>
// import { login, findById, tokenRegeneration, logout } from "@/api/member";
import { login } from "@/api/user";
import { mapActions } from "vuex";

export default {
    name: "loginCard",
    data() {
        return {
            id: "",
            password:"",
        }
	},
	methods: {
		...mapActions("userStore", ["login"]),
		async try_login() {
			console.log("try login")
			login({
				id: this.id,
				password: this.password
			},
				({data}) => {
					if (data.message === "success") {
						this.login(this.id)
						sessionStorage.setItem("access-token", data["access-token"]);
						sessionStorage.setItem("refresh-token", data['refresh-token']);
						this.$router.push({
							name:"boardlist"
						})
					}
				},
				({data}) => {
					console.log(data)
				},
			)
		}
	}
}
</script>

<style>

</style>