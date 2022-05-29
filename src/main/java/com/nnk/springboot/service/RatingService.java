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

    /**
     * Recherche une liste d'évaluation avec la méthode findAll de l'interface ratingRepository
     * @return une liste rating
     */
    public List<Rating> list(){
        return this.ratingRepository.findAll();
    }

    /**
     * Recherche une évaluation en fonction de l'id dans le paramètre avec la méthode findAll de ratingRepository
     * @param id
     * @return rating
     */
    public Optional<Rating> findById(Integer id){return this.ratingRepository.findById(id);}

    /**
     * Sauvergarde d'une évaluation avec la méthode save de ratingRepository
     * @param rating
     * @return une sauvergade rating
     */
    public Rating save(Rating rating){
        return this.ratingRepository.save(rating);
    }

    /**
     * Modification d'une évaluation en récupérant les éléménts dans les paramètre avec la méthode save de ratingRepository
     * @param newRating
     * @param id
     * @return rating modifié
     */
    public Rating update(Rating newRating, int id){
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

    /**
     * Suppression d'une évaluation en récupérant l'id dans le paramètre avec la méthode deleteById de ratingRepository
     * @param id
     */
    public void deleteById(Integer id){
        this.ratingRepository.deleteById(id);
    }
}
