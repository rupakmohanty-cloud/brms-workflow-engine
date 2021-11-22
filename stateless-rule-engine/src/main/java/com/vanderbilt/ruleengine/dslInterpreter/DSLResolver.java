package com.vanderbilt.ruleengine.dslInterpreter;

public interface DSLResolver {
  String getResolverKeyword();
  Object resolveValue(String keyword);

}
