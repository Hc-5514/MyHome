package home.user.model.service;

import home.user.model.UserDto;
import home.user.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto userLogin(UserDto userDto) {
        return userDao.userLogin(userDto);
    }

    @Override
    public int userEnroll(UserDto userDto) {
        return userDao.userEnroll(userDto);
    }

    @Override
    public int userModify(UserDto userDto) {
        return userDao.userModify(userDto);
    }

    @Override
    public int userDelete(String id) {
        return userDao.userDelete(id);
    }
}
