package com.nnk.springboot.api;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.payload.response.MessageResponse;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RuleNameApi {

    private RuleNameService ruleNameService;

    public RuleNameApi(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @GetMapping("/ruleName")
    public ResponseEntity<List<RuleName>> getRuleName() {
        List<RuleName> ruleNames = ruleNameService.list();

        Logger.info("ruleName listé !");

        return ResponseEntity.ok().body(ruleNames);

    }

    @PostMapping("/ruleName")
    public ResponseEntity<MessageResponse> createRuleName(@Valid @RequestBody RuleName ruleName) {
        ruleNameService.save(ruleName);
        return ResponseEntity.ok(new MessageResponse("ruleName saved !"));
    }

    @PutMapping("/ruleName/{id}")
    public ResponseEntity<RuleName> updateRuleName(@PathVariable("id") int id,@RequestBody RuleName ruleName) throws  Exception{

        RuleName result = ruleNameService.update(ruleName,id);

        Logger.info("La ruleName l'id n° "+id +" a été modifié !");

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/ruleName/{id}")
    public ResponseEntity<MessageResponse> deleteRuleName(@PathVariable int id) {
        ruleNameService.deleteById(id);

        Logger.warn("ruleName id "+id+" a été supprimé !");

        return ResponseEntity.ok(new MessageResponse("RuleName id "+id+" a été supprimé !"));
    }
}
