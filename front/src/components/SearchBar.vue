<template>
    <div>
        <div style = "margin-top:150px">
	<h1 style = "font-size:80px "><b>Where is My Home</b></h1>
    </div>
    <form style = "margin-bottom:50px" action="/house" method="GET">
    <div class="py-lg-5">
        <div class="row col-md-12 justify-content-center mb-2">
        <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" v-model="sido">
            <option :value="null" >시도</option>
            <option
                v-for="(item, index) in sidoList"
                :key="index"
                :value="item.value">{{ item }}
            </option>
            </select>
        </div>
        <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" v-model="gugun">
            <option :value="null" >구근</option>
            <option
                v-for="(item, index) in gugunList"
                :key="index"
                :value="item.value">{{ item }}
            </option>
            </select>
        </div>
        <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" v-model="dong">
            <option :value="null" >동</option>
            <option
                v-for="(item, index) in dongList"
                :key="index"
                :value="item.value">{{ item }}
            </option>
            </select>
        </div>
        <div class="form-group col-md-2">
            <button type="submit" id="list-btn" class="btn btn-dark">
            아파트매매정보
            </button>
        </div>
        </div>
    </div>
    </form>
    </div>
</template>

<script>
export default {
    name: "searchBar",
    data() {
        return {
            sido: null,
            gugun:  null,
            dong:  null,
            sidoList: [1, 2, 3],
            gugunList: [4, 5, 6],
            dongList:[7, 8, 9],
        }
    }

}
</script>

<style>

</style>
