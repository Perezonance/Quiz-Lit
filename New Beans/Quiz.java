package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="quiz_id", unique=true, nullable=false)
	private int id;

	@ManyToOne
	@JoinColumn(name="quiz_student_id")
	private User user;

	@Column(name="quiz_right")
	private int rightAns;

	@Column(name="quiz_wrong")
	private int wrongAns;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_structureId")
	private QuizStructure quizStructure;
		
	private List<Question> questions;


	//Getter and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRightAns() {
		return rightAns;
	}

	public void setRightAns(int rightAns) {
		this.rightAns = rightAns;
	}

	public int getWrongAns() {
		return wrongAns;
	}

	public void setWrongAns(int wrongAns) {
		this.wrongAns = wrongAns;
	}

	public QuizStructure getQuizStructure() {
		return quizStructure;
	}

	public void setQuizStructure(QuizStructure quizStructure) {
		this.quizStructure = quizStructure;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	// constructor
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}



}
