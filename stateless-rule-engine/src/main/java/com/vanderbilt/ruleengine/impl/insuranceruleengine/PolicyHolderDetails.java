package com.vanderbilt.ruleengine.impl.insuranceruleengine;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyHolderDetails {
    String firstName;
    String lastName;
    LocalDate dob;
    String gender;
    Double premiumAmount;
    Integer policyTermInYear;
    Integer premiumTermInYear;
}
