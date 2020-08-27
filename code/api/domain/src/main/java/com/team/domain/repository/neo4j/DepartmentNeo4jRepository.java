
package com.team.domain.repository.neo4j;

import com.team.domain.node.DepartmentNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DepartmentNeo4jRepository extends Neo4jRepository<DepartmentNode, Long> {

}