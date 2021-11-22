package com.vanderbilt.ruleengine.impl.loanruleengine;


import com.vanderbilt.ruleengine.main.InferenceEngine;
import com.vanderbilt.ruleengine.restAPI.RuleNamespace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanInferenceEngine extends InferenceEngine<UserDetails, LoanDetails> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.LOAN;
    }

    @Override
    protected LoanDetails initializeOutputResult() {
        return LoanDetails.builder().build();
    }
}
