package dao;

import domain.User;

public interface UserDao {
	int addUser(String sql, User user);
	int deleteUser(String sql, String username);
}
