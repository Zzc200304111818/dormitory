package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDaoImpl;
import domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDaoImpl userDao;
	@Override  
	public boolean addUser(User user) { 
		String sql = "insert into users(username, password) values(?, ?)";
	    if(userDao.addUser(sql, user) > 0)	return true;
	    return false; // 添加成功，返回true  
	}
	@Override
	public boolean deleteUser(String username) {
		String sql = "delete from users where username = ?";
		if(userDao.deleteUser(sql, username) > 0)	return true;
		return false;
	}
}
