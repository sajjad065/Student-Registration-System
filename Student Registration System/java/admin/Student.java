package admin;


public class Student {
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private String dob;
	private String gender;
	private String phone;
	private String course;
	
	public String getEmail() {
		return email;
	}

	public String getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getCourse() {
		return course;
	}
	
	public String getfirst_name() {
		return first_name;
	}
	
	public String getlast_name() {
		return last_name;
	}

	public int getid() {
		return id;
	}


	public Student(int id, String first_name, String last_name,String email,String dob,String gender,String phone,String course)
	{
		this.id=id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.dob=dob;
		this.gender=gender;
		this.phone=phone;
		this.course=course;
		
	}
	
}

