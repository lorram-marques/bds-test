package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired 
	private GenreRepository genreRepository;
	
	public Page<MovieDTO> findAll(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> list = repository.find(genre, pageable);
		return list.map(x -> new MovieDTO(x)); 
	}
	
	public MovieDTO findById(Long id) {
		Optional<Movie> entity = repository.findById(id);
		MovieDTO dto = new MovieDTO(entity.get());
		return dto;
	}
	
	/*@Transactional
	public List<ReviewDTO> findReviews(Long id) {
		Optional<Movie> movie = repository.findById(id);
		List<Review> reviews = movie.get().getReviews();
		List<ReviewDTO> reviewsDto = reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		return reviewsDto;
	}*/	
}
