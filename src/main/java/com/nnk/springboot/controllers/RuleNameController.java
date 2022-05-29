package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;

    /**
     * Affiche la page contenant tout les règles
     * @param model
     * @return page ruleName/list
     */
    @RequestMapping("/ruleName/list")
    public String homeRuleName(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.list());
        return "ruleName/list";
    }

    /**
     * Affiche la page pour ajouter une règle
     * @param ruleName
     * @return page rule/add
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    /**
     * Enregistre une règle avec la méthode save dans la class ruleNameService si validation est correcte
     * @param ruleName
     * @param result
     * @param model
     * @return page
     */
    @PostMapping("/ruleName/validate")
    public String validateRuleName(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            model.addAttribute("ruleNames", ruleNameService.list());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     * Affiche la page contenant la règle à modifier
     * @param id
     * @param model
     * @return ruleName/update
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateFormRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Modifie une règle avec la méthode update dans la class ruleNameService si validation est correcte
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return une page
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleNameService.update(ruleName,id);
        model.addAttribute("ruleNames", ruleNameService.list());
        return "redirect:/ruleName/list";
    }

    /**
     * Supprime une règle avec la méthode deleteById dans la class ruleNameService
     * @param id
     * @param model
     * @return ruleName/list
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameService.deleteById(ruleName.getId());
        model.addAttribute("ruleNames", ruleNameService.list());
        return "redirect:/ruleName/list";
    }
}
