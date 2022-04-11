package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
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

    public List<RuleName> list(){
        return this.ruleNameRepository.findAll();
    }

    public Optional<RuleName> findById(Integer id){return this.ruleNameRepository.findById(id);}

    public RuleName save(RuleName ruleName){
        return this.save(ruleName);
    }

    public RuleName update(RuleName newRuleName){
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

    public void deleteById(Integer id){
        this.ruleNameRepository.deleteById(id);
    }
}
