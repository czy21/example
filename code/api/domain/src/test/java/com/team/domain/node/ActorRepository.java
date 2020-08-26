
package com.team.domain.node;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;


public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    List<Actor> findAllByRolesMovieTitle(String title);
}
