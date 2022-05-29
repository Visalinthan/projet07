package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTest {

	@InjectMocks
	private RatingService ratingService;

	@Mock
	private RatingRepository ratingRepository;


	private static Rating getRating(){
		Rating rating = new Rating();
		rating.setId(1);
		rating.setFitchRating("test");
		return rating;
	}

	/**
	 * Test de méthode findAll de l'interface ratingRepository et méthode list de la class ratingService
	 */
	@Test
	public void list(){
		List<Rating> ratings = new ArrayList<>();
		ratings.add(getRating());

		when(ratingRepository.findAll()).thenReturn(ratings);

		assertThat(ratingService.list().get(0).getId()).isEqualTo(getRating().getId());
	}

	/**
	 * Test de méthode save de l'interface ratingRepository et méthode save de la class ratingService
	 */
	@Test
	public void save() {

		Rating rating = getRating();

		when(ratingRepository.save(ArgumentMatchers.any(Rating.class))).thenReturn(rating);

		assertThat(ratingService.save(rating)).isEqualTo(rating);

	}

	/**
	 * Test de méthode save de l'interface ratingRepository et méthode update de la class ratingService
	 */
	@Test
	public void update(){
		Rating rating = getRating();

		when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(getRating()));
		when(ratingRepository.save(ArgumentMatchers.any(Rating.class))).thenReturn(rating);

		assertThat(ratingService.update(rating, rating.getId())).isEqualTo(rating);

	}

	/**
	 * Test de méthode deleteById de l'interface ratingRepository et méthode deleteById de la class ratingService
	 */
	@Test
	public void delete() {
		Rating rating = getRating();

		ratingService.deleteById(rating.getId());

		verify(ratingRepository, times(1)).deleteById(rating.getId());
	}
}
