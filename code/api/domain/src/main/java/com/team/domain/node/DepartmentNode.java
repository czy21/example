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
@NodeEntity(label = "department")
public class DepartmentNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long parentId;
    @Relationship(type = "establish",direction = Relationship.INCOMING)
    private CompanyNode company;

    public DepartmentNode(String name) {
        this.name = name;
    }
}
