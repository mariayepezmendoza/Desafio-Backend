/**
 * 
 */
package com.maria.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maria.api.exception.ResourceNotFoundException;
import com.maria.api.model.Relation;
import com.maria.api.repository.IRelationRepository;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */

@Service
@Transactional
public class RelationServiceImpl implements RelationService{

	private static final Logger Log = LoggerFactory.getLogger(RelationServiceImpl.class);

	@Autowired
	private IRelationRepository relationRepository;

	@Override
	public List<Relation> createRelation(List<Relation> relations) {
		for(Relation relation: relations) {
			Observable<Relation> source = Observable.just(relationRepository.save(relation));
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
		}
		return relations;
	}

	@Override
	public Observable<Relation> updateRelation(Relation relation) {
		Optional<Relation> relationDb = this.relationRepository.findById(relation.getId());
	
		if(relationDb.isPresent()) {
			Relation relationUpdate = relationDb.get();
			setValues(relation, relationUpdate);
			
			Observable<Relation> source = Observable.just(relationUpdate);
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
			return source;
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + relation.getId());
		}
	}

	@Override
	public List<Relation> getAllRelation() {
		List<Relation> list = new ArrayList<Relation>(relationRepository.findAll());
		Observable<Relation> source = Observable.fromIterable(list);
		source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
		return list;
	}

	@Override	
	public Observable<Relation> getRelationById(long relationId) {
		Optional<Relation> relationDb = this.relationRepository.findById(relationId);

		if(relationDb.isPresent()) {
			Observable<Relation> source = Observable.just(relationDb.get());
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
			return source;
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + relationId);
		}
	}

	@Override
	public void deleteRelation(long relationId) {
		Optional<Relation> relationDb = this.relationRepository.findById(relationId);

		if(relationDb.isPresent()) {
			this.relationRepository.delete(relationDb.get());
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + relationId);
		}
	}

	@Override
	public Observable<Relation> getRelationByCurrency(Relation relation) {
		Optional<Relation> relationDb = this.relationRepository.getRelationByCurrency(
				relation.getDestinationCurrency(), relation.getOriginCurrency());
		
		if(relationDb.isPresent()) {
			Observable<Relation> source = Observable.just(relationDb.get());
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
			return source;
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_CURRENCY + relation.getOriginCurrency());
		}
	}

	private void setValues(Relation relation, Relation relationUpdate) {
		if(relation.getId() != null) {
			relationUpdate.setId(relation.getId());
		}

		if(relation.getOriginCurrency() != null) {
			if(!relation.getOriginCurrency().trim().equals("")) {
				relationUpdate.setOriginCurrency(relation.getOriginCurrency());
			}
		}
			
		if(relation.getDestinationCurrency() != null) {
			if(!relation.getDestinationCurrency().trim().equals("")) {
				relationUpdate.setDestinationCurrency(relation.getDestinationCurrency());
			}
		}
		
		if(relation.getChangeType() != null) {
			relationUpdate.setChangeType(relation.getChangeType());
		}
	}
}
