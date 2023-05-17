package home.user.model.dao;

import home.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    UserDto userLogin(UserDto userDto);

    int userEnroll(UserDto userDto);

    int userModify(UserDto userDto);

    int userDelete(String id);
}
