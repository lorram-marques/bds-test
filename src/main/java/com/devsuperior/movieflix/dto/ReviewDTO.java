package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Nao pode estar em branco")
	private String text;
	private Long movieId;
	private Long userId;
	private String userName;
	
	public ReviewDTO() {
	}
	
	public ReviewDTO(Long id, String text, Long movieId, Long userId, String userName) {
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.userId = userId;
		this.setUserName(userName);
	}

	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText();
		userId = review.getUser().getId();
		setUserName(review.getUser().getName());
		movieId = review.getMovie().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}
