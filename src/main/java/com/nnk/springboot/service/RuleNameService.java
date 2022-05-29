package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RuleNameService {

    private RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    /**
     * Recherche une liste de règle avec la méthode findAll de l'interface ruleNameRepository
     * @return une liste ruleName
     */
    public List<RuleName> list(){
        return this.ruleNameRepository.findAll();
    }

    /**
     * Recherche une règle en fonction de l'id dans le paramètre avec la méthode findAll de ruleNameRepository
     * @param id
     * @return ruleName
     */
    public Optional<RuleName> findById(Integer id){return this.ruleNameRepository.findById(id);}

    /**
     * Sauvergarde d'une règle avec la méthode save de ruleNameRepository
     * @param ruleName
     * @return sauvegarde de ruleName
     */
    public RuleName save(RuleName ruleName){
        return this.ruleNameRepository.save(ruleName);
    }

    /**
     * Modification d'une règle en récupérant les éléménts dans les paramètres avec la méthode save de ruleNameRepository
     * @param newRuleName
     * @param id
     * @return une règle modifié
     */
    public RuleName update(RuleName newRuleName, int id){
        Optional<RuleName> ruleNameFind = this.ruleNameRepository.findById(newRuleName.getId());
        RuleName ruleNameUpdated = null;

        if (ruleNameFind.isPresent()){
            RuleName ruleNametUpdate = ruleNameFind.get();
            ruleNametUpdate.setName(newRuleName.getName());
            ruleNametUpdate.setDescription(newRuleName.getDescription());
            ruleNametUpdate.setJson(newRuleName.getJson());
            ruleNametUpdate.setTemplate(newRuleName.getTemplate());
            ruleNametUpdate.setSqlStr(newRuleName.getSqlStr());
            ruleNametUpdate.setSqlPart(newRuleName.getSqlPart());

            ruleNameUpdated = this.ruleNameRepository.save(ruleNametUpdate);
        }

        return ruleNameUpdated;
    }

    /**
     * Suppression d'une règle en récupérant l'id dans le paramètre avec la méthode deleteById de ruleNameRepository
     * @param id
     */
    public void deleteById(Integer id){
        this.ruleNameRepository.deleteById(id);
    }
}
