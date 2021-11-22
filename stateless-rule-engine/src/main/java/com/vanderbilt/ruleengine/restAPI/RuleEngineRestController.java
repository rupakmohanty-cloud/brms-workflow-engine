package com.vanderbilt.ruleengine.restAPI;

import com.google.common.base.Enums;

import com.vanderbilt.querybuilder.LoanQueryBuilder;
import com.vanderbilt.ruleengine.dto.QueryParameter;
import com.vanderbilt.ruleengine.impl.insuranceruleengine.InsuranceInferenceEngine;
import com.vanderbilt.ruleengine.impl.loanruleengine.LoanDetails;
import com.vanderbilt.ruleengine.impl.loanruleengine.LoanInferenceEngine;
import com.vanderbilt.ruleengine.impl.loanruleengine.UserDetails;
import com.vanderbilt.ruleengine.knowledgebase.KnowledgeBaseService;
import com.vanderbilt.ruleengine.knowledgebase.db.RuleDbModel;
import com.vanderbilt.ruleengine.knowledgebase.db.RulesRepository;
import com.vanderbilt.ruleengine.knowledgebase.models.Rule;
import com.vanderbilt.ruleengine.main.RuleEngine;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RuleEngineRestController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;
    @Autowired
    private RuleEngine ruleEngine;
    @Autowired
    private LoanInferenceEngine loanInferenceEngine;
    @Autowired
    private InsuranceInferenceEngine insuranceInferenceEngine;
    @Autowired
    private RulesRepository rulesRepository;

    @GetMapping(value = "/get-all-rules/{ruleNamespace}")
    public ResponseEntity<?> getRulesByNamespace(@PathVariable("ruleNamespace") String ruleNamespace) {
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleNamespace.toUpperCase()).or(RuleNamespace.DEFAULT);
        List<Rule> allRules = knowledgeBaseService.getAllRuleByNamespace(namespace.toString());
        return ResponseEntity.ok(allRules);
    }

    @GetMapping(value = "/get-all-rules")
    public ResponseEntity<?> getAllRules() {
        List<Rule> allRules = knowledgeBaseService.getAllRules();
        return ResponseEntity.ok(allRules);
    }

    @GetMapping(value = "/get-rules")
    public ResponseEntity<?> getRules() {
        List<Rule> allRules = knowledgeBaseService.getAllRules();
        return ResponseEntity.ok(allRules);
    }



    @PostMapping(value = "/loan")
    public ResponseEntity<?> postUserLoanDetails(@RequestBody UserDetails userDetails) {
        LoanDetails result = (LoanDetails) ruleEngine.run(loanInferenceEngine, userDetails);
        result.setAccountNumber(userDetails.getAccountNumber());
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleterule/{ruleId}")
    public ResponseEntity<Object> deleteRule(@PathVariable Long ruleId) {
        return rulesRepository.findById(ruleId)
            .map(question -> {
                rulesRepository.delete(question);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + ruleId));
    }


    @PostMapping("/updateRule")
    public RuleDbModel updateRule(
          @RequestBody QueryParameter parameter) {
        String mvelExpression = LoanQueryBuilder.getMvelQueryParam(parameter);
        return rulesRepository.findById(parameter.getRuleId())
            .map(rule -> {
                rule.setRuleId(parameter.getRuleId());
                rule.setCondition(mvelExpression);
                return rulesRepository.save(rule);
            }).orElseThrow(() -> new ResourceNotFoundException("Rules not found with id " + 1));
    }

   /* @PostMapping(value = "/update")
    public ResponseEntity<?> updateLoanAmount(@RequestBody QueryParameter parameter) {
        String ruleId = parameter.getRuleId();
        String mvelExpression = LoanQueryBuilder.getMvelQueryParam(parameter);
        System.out.println("Rule id is "+ruleId);
        System.out.println("Query Formed is "+mvelExpression);
        List<Rule> allRules = knowledgeBaseService.getAllUpdatedRules(ruleId,mvelExpression);
        return ResponseEntity.ok(allRules);
    }*/




   /* @PutMapping("/update/{ruleId}")
    public RuleDbModel updateRule(@PathVariable Long questionId,
        @Valid @RequestBody QueryParameter parameter) {
        return rulesRepository.findById(parameter.getRuleId())
            .map(question -> {
                question.setTitle(questionRequest.getTitle());
                question.setDescription(questionRequest.getDescription());
                return questionRepository.save(question);
            }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }*/

}
