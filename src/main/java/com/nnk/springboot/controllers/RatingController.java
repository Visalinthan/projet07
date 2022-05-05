package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String homeRating(Model model)
    {
        model.addAttribute("ratings",ratingService.list());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validateRating(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.save(rating);
            model.addAttribute("ratings", ratingService.list());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateFormRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingService.update(rating,id);
        model.addAttribute("rating", ratingService.list());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingService.deleteById(rating.getId());
        model.addAttribute("ratings", ratingService.list());
        return "redirect:/rating/list";
    }
}
