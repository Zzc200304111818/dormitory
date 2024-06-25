package dao;

import java.util.ArrayList;

import domain.Dormitory;

public interface DormitoryDao {
	int updateBeds(String sql, String dormitoryName);
	int addDormitory(String sql, Dormitory dormitory);
	int deleteDormitory(String sql, String dormitroyName);
	Dormitory queryDormitory(String sql, String dormitoryName);
	ArrayList<Dormitory> queryAllDormitory(String sql);
	int updateDormitory(String sql, Dormitory dormitory);
}
