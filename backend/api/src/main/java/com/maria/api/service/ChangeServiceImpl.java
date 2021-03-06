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
import com.maria.api.model.Change;
import com.maria.api.model.Relation;
import com.maria.api.repository.IChangeRepository;
import com.maria.api.repository.IRelationRepository;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */

@Service
@Transactional
public class ChangeServiceImpl implements ChangeService{

	private static final Logger Log = LoggerFactory.getLogger(ChangeServiceImpl.class);

	@Autowired
	private IChangeRepository changeRepository;

	@Autowired
	private IRelationRepository relationRepository ;

	@Override
	public List<Change> createChange(List<Change> changes) {
		for(Change change : changes) {
			Relation relation = verifyRelation(change);
			change.setRelation(relation);
			change.setNewMount(relation.getChangeType().multiply(
					change.getMount(), Constants.MATH_CONTEXT));

			Observable<Change> source = Observable.just(changeRepository.save(change));
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));

		}
		return changes;
	}

	@Override
	public Observable<Change> updateChange(Change change) {
		Optional<Change> changeDb = this.changeRepository.findById(change.getId());
	
		if(changeDb.isPresent()) {
			Change changeForUpdate = changeDb.get();
			setValues(change, changeForUpdate);
			
			Observable<Change> source = Observable.just(changeForUpdate);
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
			return source;
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + change.getId());
		}
	}

	@Override
	public List<Change> getAllChange() {
		List<Change> list = new ArrayList<Change>(changeRepository.findAll());
		Observable<Change> source = Observable.fromIterable(list);
		source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
		return list;
	}

	@Override
	public Observable<Change> getChangeById(long changeId) {
		Optional<Change> changeDb = this.changeRepository.findById(changeId);

		if(changeDb.isPresent()) {
			Observable<Change> source = Observable.just(changeDb.get());
			source.subscribe(s -> Log.info(Constants.STR_RELATION + s.toString()));
			return source;
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + changeId);
		}
	}

	@Override
	public void deleteChange(long changeId) {
		Optional<Change> changeDb = this.changeRepository.findById(changeId);

		if(changeDb.isPresent()) {
			this.changeRepository.delete(changeDb.get());
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_ID + changeId);
		}
	}

	private Relation verifyRelation(Change change) {
		Optional<Relation> relationDb = this.relationRepository.getRelationByCurrency(
				change.getRelation().getDestinationCurrency(), change.getRelation().getOriginCurrency());

		Relation relation = new Relation();
		if(relationDb.isPresent()) {
			relation.setId(relationDb.get().getId());
			relation.setDestinationCurrency(relationDb.get().getDestinationCurrency());
			relation.setOriginCurrency(relationDb.get().getOriginCurrency());
			relation.setChangeType(relationDb.get().getChangeType());
		}else {
			throw new ResourceNotFoundException(Constants.STR_ERROR_CURRENCY +
					change.getRelation().getOriginCurrency() + " " +
					change.getRelation().getDestinationCurrency());
					
		}

		return relation;
	}

	private void setValues(Change change, Change changeForUpdate) {
		Relation relation = null;
		if(change.getId() != null) {
			changeForUpdate.setId(change.getId());
		}

		if(change.getMount() != null) {
			changeForUpdate.setMount(change.getMount());
		}

		if(change.getRelation() != null &&
			!change.getRelation().getOriginCurrency().equals("") &&
			 !change.getRelation().getDestinationCurrency().equals("")) {
			 relation = verifyRelation(change);
			changeForUpdate.setRelation(relation);
		}
		
		if(change.getMount() == null && relation != null) {
			changeForUpdate.setNewMount(relation.getChangeType().multiply(
					changeForUpdate.getMount(), Constants.MATH_CONTEXT));
		}else if(change.getMount() != null && relation == null){
			changeForUpdate.setNewMount(change.getRelation().getChangeType().multiply(
					change.getMount(), Constants.MATH_CONTEXT));
		}else{
			Log.info(Constants.STR_ANY_ITEMS);
		}
		
	}

}
