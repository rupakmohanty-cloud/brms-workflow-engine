package com.vanderbilt.ruleengine.langparser;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MVELParser {

    public boolean parseMvelExpression( String expression, Map<String, Object> inputObjects){
        try {
           return MVEL.evalToBoolean(expression,inputObjects);
        }catch (Exception e){
           // log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
           // System.out.println("Can not parse Mvel Expression : {} Error: {}"+expression+ e.getMessage());
        }
        return false;
    }
}
