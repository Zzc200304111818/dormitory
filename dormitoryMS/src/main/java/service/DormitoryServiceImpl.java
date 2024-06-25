package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DormitoryDao;
import domain.Dormitory;
@Service
public class DormitoryServiceImpl implements DormitoryService {

	@Autowired
	private DormitoryDao dormitoryDao;
	@Override
	public boolean addDormitory(Dormitory dormitory) {
		String sql = "insert into dormitories(dormitoryName, totalBeds, availableBeds, remark) values(?, ?, ?, ?)";
	    if(dormitoryDao.addDormitory(sql, dormitory) > 0) return true;
	    return false;   
	}

	@Override
	public boolean deleteDormitory(String dormitoryName) {
		String sql = "delete from dormitories where dormitoryName = ?";
		if(dormitoryDao.deleteDormitory(sql, dormitoryName) > 0)	return true;
		return false;
	}

	@Override
	public Dormitory queryDormitory(String dormitoryName) {
		String sql = "select * from dormitories where dormitoryName = ?";
		return dormitoryDao.queryDormitory(sql, dormitoryName);
	}

	@Override
	public ArrayList<Dormitory> queryAllDormitory() {
		String sql = "select * from dormitories";
		return dormitoryDao.queryAllDormitory(sql);
	}

	@Override
	public boolean updateDormitory(Dormitory dormitory) {
		String sql = "update dormitories set totalBeds = ?, availableBeds = ?, remark = ? where dormitoryName = ?";
		if(dormitoryDao.updateDormitory(sql, dormitory) > 0)	return true;
		return false;
	}

}
