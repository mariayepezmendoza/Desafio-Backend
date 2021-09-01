/**
 * 
 */
package com.maria.api.service;

import java.util.List;

import com.maria.api.model.Relation;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */
public interface RelationService {

	List<Relation> createRelation(List<Relation> Relation);

	Observable<Relation> updateRelation(Relation Relation);

	List<Relation> getAllRelation();

	Observable<Relation> getRelationById(long RelationId);

	void deleteRelation(long RelationId);

	Observable<Relation> getRelationByCurrency(Relation Relation);
}
