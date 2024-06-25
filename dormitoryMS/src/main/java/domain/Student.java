package domain;

public class Student {
	private String stuNo;
	private String name;
	private String sex;
	private Integer age;
	private String dormitoryName;

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDormitoryName() {
		return dormitoryName;
	}

	public void setDormitoryName(String dormitoryName) {
		this.dormitoryName = dormitoryName;
	}

}
