package beans;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Categories {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id", unique=true, nullable=false)
	private int id;
	
	@Column(name="category_name", nullable=false)
	private String name;
	
	@Column(name="category_description", nullable=false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="structureCategory")
	private List<QuizStructure> quizStructures;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="questionCategory")
	private List<Question> questions;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", description=" + description + ", questions=" + questions
				+ "]";
	}
	
	
	
	
}
