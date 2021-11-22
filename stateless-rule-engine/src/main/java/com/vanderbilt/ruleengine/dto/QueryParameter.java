package com.vanderbilt.ruleengine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryParameter {
  Long ruleId;
  Double monthlySalary;
  Integer creditScore;
  Double requestedLoanAmount;
  String salaryOperator;
  String creditScoreOperator;
  String loanAmountOperator;

}
