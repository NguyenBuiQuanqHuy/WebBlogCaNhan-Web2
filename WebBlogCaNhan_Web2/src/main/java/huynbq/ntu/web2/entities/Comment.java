package huynbq.ntu.web2.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	
	private LocalDateTime createdat;
	
	@ManyToOne
	@JoinColumn(name = "user",referencedColumnName = "username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "post",referencedColumnName = "ID")
	private Post post;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public LocalDateTime getCreatedAt() {
		return createdat;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdat = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
