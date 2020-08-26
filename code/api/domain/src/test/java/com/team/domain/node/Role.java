package com.team.domain.node;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "ACTED_IN")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Actor actor;
    private String role;
    @EndNode
    private Movie movie;

    public Role() {
    }

    public Role(Actor actor, String role, Movie movie) {
        this.actor = actor;
        this.role = role;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
