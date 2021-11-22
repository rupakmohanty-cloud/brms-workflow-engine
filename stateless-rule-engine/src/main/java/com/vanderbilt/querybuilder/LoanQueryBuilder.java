package com.vanderbilt.querybuilder;

import com.vanderbilt.ruleengine.dto.QueryParameter;

public class LoanQueryBuilder {

  public static String getMvelQueryParam(QueryParameter parameter) {
    //input.monthlySalary < 50000 && input.creditScore <= 300 && input.requestedLoanAmount >= 1000000 || $(bank.target_done) == true
    StringBuilder builder = new StringBuilder();
    builder.append("input.monthlySalary");
    builder.append(" ");
    builder.append(parameter.getSalaryOperator());
    builder.append(parameter.getMonthlySalary());
    builder.append(" ");
    builder.append("&&");
    builder.append(" ");
    builder.append("input.creditScore");
    builder.append(" ");
    builder.append(parameter.getCreditScoreOperator());
    builder.append(parameter.getCreditScore());
    builder.append(" ");
    builder.append("&&");
    builder.append(" ");
    builder.append("input.requestedLoanAmount");
    builder.append(" ");
    builder.append(parameter.getLoanAmountOperator());
    builder.append(parameter.getRequestedLoanAmount());
    builder.append(" ");
    builder.append("||");
    builder.append(" ");
    builder.append("$(bank.target_done) == true");
    return builder.toString();

  }

}
