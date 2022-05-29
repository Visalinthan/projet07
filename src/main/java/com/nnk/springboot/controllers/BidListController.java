package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;


@Controller
public class BidListController {

    private BidListService bidListService;

    private Utils utils;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * Affiche la page contenant tout les offres
     * @param model
     * @return page bidList/list
     */
    @RequestMapping("/bidList/list")
    public String homeBid(Model model)
    {
        List<BidList> bidLists = bidListService.list();
        model.addAttribute("bidLists", bidLists);

        Logger.info("bidList listé !");

        return "bidList/list";
    }

    /**
     * Affiche la page pour ajouter une offre
     * @param bid
     * @return page bidList/add
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Enregistre une offre avec la méthode save dans la class bidListService si validation est correcte
     * @param bid
     * @param result
     * @param model
     * @return page
     */
    @PostMapping("/bidList/validate")
    public String validateBid(@Valid BidList bid, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            bidListService.save(bid);
            model.addAttribute("bidLists", bidListService.list());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /**
     * Affiche la page contenant l' offre à modifier
     * @param id
     * @param model
     * @return bidList/update
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateBidForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Modifie une offre avec la méthode update dans la class bidListService si validation est correcte
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return une page
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.update(bidList,id);
        model.addAttribute("bidLists", bidListService.list());
        return "redirect:/bidList/list";
    }

    /**
     * Supprime une offre avec la méthode deleteById dans la class bidListService
     * @param id
     * @param model
     * @return bidList/list
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListService.deleteById(bidList.getId());
        model.addAttribute("bidLists", bidListService.list());
        return "redirect:/bidList/list";
    }
}
