package com.vanderbilt.ruleengine.knowledgebase.models;


import com.vanderbilt.ruleengine.restAPI.RuleNamespace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Rule {
    RuleNamespace ruleNamespace;
    Long ruleId;
    String condition;
    String action;
    Integer priority;
    String description;
}
