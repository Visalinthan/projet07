package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {
    @Autowired
    TradeService tradeService;

    /**
     * Affiche la page contenant tout les échanges
     * @param model
     * @return page bidList/list
     */
    @RequestMapping("/trade/list")
    public String homeTrade(Model model)
    {
        model.addAttribute("trades", tradeService.list());
        return "trade/list";
    }

    /**
     * Affiche la page pour ajouter une échange
     * @param trade
     * @return page trade/add
     */
    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        return "trade/add";
    }

    /**
     * Enregistre une échange avec la méthode save dans la class tradeService si validation est correcte
     * @param trade
     * @param result
     * @param model
     * @return page
     */
    @PostMapping("/trade/validate")
    public String validateTrade(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.save(trade);
            model.addAttribute("trades", tradeService.list());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     * Affiche la page contenant l' échange à modifier
     * @param id
     * @param model
     * @return trade/update
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateFormTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Modifie une échange avec la méthode update dans la class tradeService si validation est correcte
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return une page
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeService.update(trade,id);
        model.addAttribute("trades", tradeService.list());
        return "redirect:/trade/list";
    }

    /**
     * Supprime une échange avec la méthode deleteById dans la class tradeService
     * @param id
     * @param model
     * @return trade/list
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.deleteById(trade.getId());
        model.addAttribute("trades", tradeService.list());
        return "redirect:/trade/list";
    }
}
