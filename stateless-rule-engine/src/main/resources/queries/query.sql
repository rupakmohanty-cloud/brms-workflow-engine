CREATE DATABASE rulebase;
CREATE TABLE rules_repo (
                       rule_namespace varchar(256) not null,
                       rule_id integer not null,
                       condition varchar(2000),
                       action varchar(2000),
                       priority integer,
                       description varchar(1000),
                       PRIMARY KEY(rule_id)
);


INSERT INTO rules_repo
(rule_namespace , rule_id, condition,
 action, priority, description)
VALUES (
           'LOAN',
           '1',
           'input.monthlySalary >= 50000 && input.creditScore >= 500 && input.requestedLoanAmount < 1500000 && $(bank.target_done) == false',
           'output.setApprovalStatus(true);output.setSanctionedPercentage(90);output.setProcessingFees(8000);output.setInterestRate(2.8);',
           '1',
           'A person is eligible for Home loan?'
       );

INSERT INTO rules_repo
(rule_namespace , rule_id, condition,
 action, priority, description)
VALUES (
           'LOAN',
           '4',
           'input.monthlySalary < 50000 && input.creditScore <= 300 && input.requestedLoanAmount >= 1000000 || $(bank.target_done) == true',
           'output.setApprovalStatus(true);output.setSanctionedPercentage(90);output.setProcessingFees(8000);output.setInterestRate(3.2);',
           '1',
           'A person is eligible for Home loan?'
       );

