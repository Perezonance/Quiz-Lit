package beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="question")
public class Question {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="question_id", unique=true, nullable=false)
		private int id;
		
		@Column(name="question_question", nullable=false)
		private String question;
		
		@Column(name="question_correct_answer", nullable=false)
		private String correctAnswer;
		
		@Column(name="question_wrong_answer1", nullable=false, unique=true)
		private String wrongAnswer1;
		
		@Column(name="question_wrong_answer2", nullable=false)
		private String wrongAnswer2;
		
		@Column(name="question_wrong_answer3", nullable=false)
		private String wrongAnswer3;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "question_categoryId")
		private Categories questionCategory;
		
		
		
		

		//Super Constructor
		public Question() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		//Getters and Setters
		
		
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getQuestion() {
			return question;
		}


		public void setQuestion(String question) {
			this.question = question;
		}


		public String getCorrectAnswer() {
			return correctAnswer;
		}


		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}


		public String getWrongAnswer1() {
			return wrongAnswer1;
		}


		public void setWrongAnswer1(String wrongAnswer1) {
			this.wrongAnswer1 = wrongAnswer1;
		}


		public String getWrongAnswer2() {
			return wrongAnswer2;
		}


		public void setWrongAnswer2(String wrongAnswer2) {
			this.wrongAnswer2 = wrongAnswer2;
		}


		public String getWrongAnswer3() {
			return wrongAnswer3;
		}


		public void setWrongAnswer3(String wrongAnswer3) {
			this.wrongAnswer3 = wrongAnswer3;
		}


		public Categories getQuestionCategory() {
			return questionCategory;
		}


		public void setQuestionCategory(Categories questionCategory) {
			this.questionCategory = questionCategory;
		}
		
		
}
