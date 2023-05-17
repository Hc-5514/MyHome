package home.user.model.service;

import home.user.model.UserDto;

public interface UserService {

    UserDto userLogin(UserDto userDto); // 로그인

    int userEnroll(UserDto userDto); // 회원가입

    int userModify(UserDto userDto); // 회원 정보 수정

    int userDelete(String id); // 회원탈퇴
}
