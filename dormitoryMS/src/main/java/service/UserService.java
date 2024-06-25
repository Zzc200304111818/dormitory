package service;

import domain.User;

public interface UserService {
	boolean addUser(User user);
	boolean deleteUser(String username);
}
