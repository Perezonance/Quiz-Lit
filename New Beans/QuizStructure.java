package beans;

import java.sql.Time;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="quiz_structure")
public class QuizStructure {

			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			@Column(name="qs_id", unique=true, nullable=false)
			private int id;
			
			@Column(name="quiz_questioncount", nullable=false)
			private String questionCount;
			
			@Column(name="qs_time", nullable=false)
			private Double time;
			
			@Column(name="qs_accessCode", nullable=false, unique=true)
			private String accessCode;
			
			@OneToMany(fetch = FetchType.LAZY, mappedBy="quizStructure")
			private List<Quiz> quizzes;
			
			@ManyToOne()
			@JoinColumn(name = "category_quizstructureid")
			private Categories structureCategory;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getQuestionCount() {
				return questionCount;
			}

			public void setQuestionCount(String questionCount) {
				this.questionCount = questionCount;
			}

			public Double getTime() {
				return time;
			}

			public void setTime(Double time) {
				this.time = time;
			}

			public String getAccessCode() {
				return accessCode;
			}

			public void setAccessCode(String accessCode) {
				this.accessCode = accessCode;
			}

			public List<Quiz> getQuizzes() {
				return quizzes;
			}

			public void setQuizzes(List<Quiz> quizzes) {
				this.quizzes = quizzes;
			}

			public Categories getStructureCategory() {
				return structureCategory;
			}

			public void setStructureCategory(Categories structureCategory) {
				this.structureCategory = structureCategory;
			}
	
	
}
