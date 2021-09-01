/**
 * 
 */
package com.maria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maria.api.model.Change;

/**
 * @author Maria
 *
 */
@Repository
public interface IChangeRepository extends JpaRepository<Change, Long> {

}
