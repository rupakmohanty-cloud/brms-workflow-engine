package com.vanderbilt.ruleengine.knowledgebase.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rules_repo")

public class RuleDbModel implements Serializable {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
        name = "id_generator",
        sequenceName = "id_sequence",
        initialValue = 1
    )
    @Column(name = "rule_id")
    private Long ruleId;

    @Column(name = "rule_namespace")
    private String ruleNamespace;

    @Column(name = "condition")
    private String condition;

    @Column(name = "action")
    private String action;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "description")
    private String description;


}
