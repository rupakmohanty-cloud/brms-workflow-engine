package com.vanderbilt.ruleengine.impl.insuranceruleengine;


import com.vanderbilt.ruleengine.main.InferenceEngine;
import com.vanderbilt.ruleengine.restAPI.RuleNamespace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InsuranceInferenceEngine extends
    InferenceEngine<PolicyHolderDetails, InsuranceDetails> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.INSURANCE;
    }

    @Override
    protected InsuranceDetails initializeOutputResult() {
       // return InsuranceDetails.builder().build();
        return new InsuranceDetails();
    }
}
