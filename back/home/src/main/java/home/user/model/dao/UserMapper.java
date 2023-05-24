package home.user.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import home.user.model.UserDTO;

@Mapper
public interface UserMapper {
	public UserDTO read(UserDTO user);
	public UserDTO readById(String id);
	public List<UserDTO> readAll();
	public int create(UserDTO user);
	public int update(UserDTO user);
	public void delete(UserDTO user);
}
