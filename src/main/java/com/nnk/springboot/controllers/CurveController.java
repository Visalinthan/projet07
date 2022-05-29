package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Role;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class CurveController {

    @Autowired
    CurvePointService curvePointService;

    /**
     * Affiche la page contenant tout les points de courbes
     * @param model
     * @return page curvePoint/list
     */
    @RequestMapping("/curvePoint/list")
    public String homeCurve(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.list());
        return "curvePoint/list";
    }

    /**
     * Affiche la page pour ajouter un point de courbe
     * @param bid
     * @return page curvePoint/add
     */
    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Enregistre un point de courbe avec la méthode save dans la class curvePointService si validation est correcte
     * @param curvePoint
     * @param result
     * @param model
     * @return page
     */
    @PostMapping("/curvePoint/validate")
    public String validateCurve(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.save(curvePoint);
            model.addAttribute("curvePoints", curvePointService.list());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    /**
     * Affiche la page contenant le point de courbe à modifier
     * @param id
     * @param model
     * @return curvePoint/update
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateFormCurve(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Modifie un point de courbe avec la méthode update dans la class curvePointService si validation est correcte
     * @param id
     * @param curvePoint
     * @param result
     * @param model
     * @return une page
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.update(curvePoint,id);
        model.addAttribute("curvePoints", curvePointService.list());
        return "redirect:/curvePoint/list";
    }

    /**
     * Supprime un point de courbe avec la méthode deleteById dans la class curvePointService
     * @param id
     * @param model
     * @return curvePoint/list
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointService.deleteById(curvePoint.getId());
        model.addAttribute("curvePoints", curvePointService.list());
        return "redirect:/curvePoint/list";
    }
}
