<template>
  <div>
    <div class="container-fluid vh-100" style="margin-top: 100px">
			<div class="" style="margin-top: 100px">
				<div class="rounded d-flex justify-content-center">
					<div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
						<div class="text-center">
							<h3 class="text-dark">회원가입</h3>
						</div>
						<div class="p-4">
							
								<input type="hidden" name="action" value="signup">
								<!-- id -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"><i
										class="bi bi-person-plus-fill text-white"></i></span> <input
										v-model="id" type="text" class="form-control" placeholder="아이디"
										>
								</div>

								<!-- password-->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"><i
										class="bi bi-key-fill text-white"></i></span> <input id="userpwd"
										v-model="password" class="form-control" placeholder="비밀번호"
										>
								</div>
								<!-- 이름 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"><i
										class="bi bi-person-plus-fill text-white"></i></span> <input
										v-model="name" type="text" class="form-control"
										placeholder="이름" >
								</div>

								<!-- 주소 -->
								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"><i
										class="bi bi-person-plus-fill text-white"></i></span> <input
										v-model="address" type="text" class="form-control" placeholder="주소"
										>
								</div>

								<div class="input-group mb-3">
									<span class="input-group-text"
										style="background-color: #929292;"><i
										class="bi bi-person-plus-fill text-white"></i></span> <input
										v-model="email" type="tel" class="form-control"
										placeholder="이메일"
										>
								</div>

								<button class="btn btn-primary text-center mt-2" @click="do_register">Sign
									Up</button>
							
						</div>
					</div>
				</div>
			</div>

		</div>

  </div>
</template>

<script>
import { register } from "@/api/user";

export default {
    name: "registerCard",
    data() {
        return {
            id: "",
            password: "",
            name: "",
            address: "",
            email: "",
        }
	},
	methods: {
		async do_register() {
			register(
				{
					id: this.id,
					password: this.password,
					name : this.name,
					addresss: this.address,
					email: this.email
				},
				({data}) => {
					if (data.message === "success") {
						this.$router.push({
							name:"login"
						})
					} else {
						console.log('X')
					}
				},
				({ error }) => {
					console.log(error)
				}
			)
		}
	},
}
</script>

<style>

</style>