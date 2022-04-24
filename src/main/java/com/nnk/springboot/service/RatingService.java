package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> list(){
        return this.ratingRepository.findAll();
    }

    public Optional<Rating> findById(Integer id){return this.ratingRepository.findById(id);}

    public Rating save(Rating rating){
        return this.ratingRepository.save(rating);
    }

    public Rating update(Rating newRating){
        Optional<Rating> ratingFind = this.ratingRepository.findById(newRating.getId());
        Rating ratingUpdated = null;

        if (ratingFind.isPresent()){
            Rating ratingUpdate = ratingFind.get();

            ratingUpdate.setMoodysRating(newRating.getMoodysRating());
            ratingUpdate.setSandPRating(newRating.getSandPRating());
            ratingUpdate.setFitchRating(newRating.getFitchRating());
            ratingUpdate.setOrderNumber(newRating.getOrderNumber());

            ratingUpdated = this.ratingRepository.save(ratingUpdate);
        }

        return ratingUpdated;
    }

    public void deleteById(Integer id){
        this.ratingRepository.deleteById(id);
    }
}
