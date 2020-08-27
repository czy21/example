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
@NodeEntity(label = "user")
public class UserNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "work", direction = Relationship.INCOMING)
    private DepartmentNode department;

    public UserNode(String name) {
        this.name = name;
    }
}
