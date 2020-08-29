package com.team.domain.node;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NoArgsConstructor
@Getter
@Setter
@NodeEntity(label = "employee")
public class EmployeeNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "HAS", direction = Relationship.INCOMING)
    private DepartmentNode department;

    public EmployeeNode(String name) {
        this.name = name;
    }
}
