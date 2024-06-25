package service;

import java.util.ArrayList;

import domain.Dormitory;

public interface DormitoryService {
	boolean addDormitory(Dormitory dormitory);
	boolean deleteDormitory(String dormitoryName);
	Dormitory queryDormitory(String dormitoryName);
	ArrayList<Dormitory> queryAllDormitory();
	boolean updateDormitory(Dormitory dormitory);
}
