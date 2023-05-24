import { apiInstance } from "./axios";

const api = apiInstance();

async function login(user, success, fail) {
    await api.post(`/home/users/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/home/users/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
    await api.post(`/home/users/refresh`, user).then(success).catch(fail);
}

async function logout(success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/home/users/logout`).then(success).catch(fail);
}

async function register(user, success, fail) {
    await api.post(`/home/users`, user).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, register };
