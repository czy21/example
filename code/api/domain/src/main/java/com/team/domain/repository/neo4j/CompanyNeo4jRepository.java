
package com.team.domain.repository.neo4j;

import com.team.domain.node.CompanyNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyNeo4jRepository extends Neo4jRepository<CompanyNode, Long> {

}
