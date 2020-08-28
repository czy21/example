package com.team.domain.node;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@NodeEntity(label = "company")
public class CompanyNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "HAS")
    private List<DepartmentNode> departments;

    public CompanyNode(String name) {
        this.name = name;
    }
}
