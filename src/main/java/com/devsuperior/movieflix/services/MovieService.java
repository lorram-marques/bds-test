package com.devsuperior.movieflix.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exception.ObjectNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired 
	private GenreRepository genreRepository;
	
	public Page<MovieDTO> findAll(Long genreId, PageRequest pageRequest) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> list = repository.find(genre, pageRequest);
		return list.map(x -> new MovieDTO(x)); 
	}
	
	public MovieDetailsDTO findById(Long id) {
		Optional<Movie> entity = repository.findById(id);
		Movie movie = entity.orElseThrow(() -> new ObjectNotFoundException(id));
		MovieDetailsDTO dto = new MovieDetailsDTO(movie);
		return dto;
	}
	
	public List<ReviewDTO> findReviews(Long id) {
		Optional<Movie> movie = repository.findById(id);
		List<Review> reviews = movie.get().getReviews();
		List<ReviewDTO> reviewsDto = reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		return reviewsDto;
	}	
}
