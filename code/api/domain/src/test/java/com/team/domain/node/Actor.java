package com.team.domain.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label = "Actor")
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "ACTED_IN")
    private List<Role> roles;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    //    public void actedIn(Movie movie, String roleName) {
//
//        Role role = new Role(this, roleName, movie);
//        roles.add(role);
//        movie.getRoles().add(role);
//    }
}
