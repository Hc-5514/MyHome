package home.user.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.user.model.UserDTO;
import home.user.model.dao.UserMapper;


@Service
public class UserService {

	@Autowired
	private SqlSession sqlSession;

	
	public UserDTO login(UserDTO dto) throws Exception {
		if (dto.id == null || dto.password == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).read(dto);
	}
	
	public UserDTO userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).readById(userid);
	}
	
	public int addUesr(UserDTO user) throws Exception {
		return sqlSession.getMapper(UserMapper.class).create(user);
	}
	
	public void deleteUser(UserDTO user) throws Exception {
		sqlSession.getMapper(UserMapper.class).delete(user);;
	}
	
	public int updateUser(UserDTO user) throws Exception {
		return sqlSession.getMapper(UserMapper.class).update(user);
	}
}