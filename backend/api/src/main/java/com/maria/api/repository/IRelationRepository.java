/**
 * 
 */
package com.maria.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maria.api.model.Relation;

/**
 * @author Maria
 *
 */
@Repository
public interface IRelationRepository extends JpaRepository<Relation, Long> {

	@Query( value = "SELECT r FROM Relation r WHERE r.destinationCurrency= :destination and r.originCurrency= :origin")
	Optional<Relation> getRelationByCurrency(
			@Param("destination") String destination, 
			@Param("origin") String origin);
}
