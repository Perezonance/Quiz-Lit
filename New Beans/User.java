package beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="user_first_name", nullable=false)
	private String fName;
	
	@Column(name="user_last_name", nullable=false)
	private String lName;
	
	@Column(name="user_email", nullable=false, unique=true)
	private String email;
	
	@Column(name="user_role_type", nullable=false)
	private String role;
	
	@Column(name="user_password", nullable=false)
	private String password;
	
	@Column(name="user_password1", nullable=true)
	private String password1;
	
	@Column(name="user_password2", nullable=true)
	private String password2;
	
	@Column(name="user_login_attempt", nullable=false)
	private int loginAttempts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Quiz> quizzes;

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Set<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", role=" + role + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
}
