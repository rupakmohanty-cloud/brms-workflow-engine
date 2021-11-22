package com.vanderbilt.ruleengine.knowledgebase;

import com.google.common.base.Enums;


import com.vanderbilt.ruleengine.knowledgebase.db.RuleDbModel;
import com.vanderbilt.ruleengine.knowledgebase.db.RulesRepository;
import com.vanderbilt.ruleengine.knowledgebase.models.Rule;
import com.vanderbilt.ruleengine.restAPI.RuleNamespace;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeBaseService {
    @Autowired
    private RulesRepository rulesRepository;



    public List<Rule> getAllRules(){
        return rulesRepository.findAll().stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    public List<Rule> getAllRuleByNamespace(String ruleNamespace){
        return rulesRepository.findByRuleNamespace(ruleNamespace).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    private Rule mapFromDbModel(RuleDbModel ruleDbModel){
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleDbModel.getRuleNamespace().toUpperCase())
                .or(RuleNamespace.DEFAULT);
        return Rule.builder()
                .ruleNamespace(namespace)
                .ruleId(ruleDbModel.getRuleId())
                .condition(ruleDbModel.getCondition())
                .action(ruleDbModel.getAction())
                .description(ruleDbModel.getDescription())
                .priority(ruleDbModel.getPriority())
                .build();
    }
}
