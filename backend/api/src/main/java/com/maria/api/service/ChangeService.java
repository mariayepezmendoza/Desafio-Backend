/**
 * 
 */
package com.maria.api.service;

import java.util.List;

import com.maria.api.model.Change;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */
public interface ChangeService {

	List<Change> createChange(List<Change> change);

	Observable<Change> updateChange(Change change);

	List<Change> getAllChange();

	Observable<Change> getChangeById(long changeId);

	void deleteChange(long changeId);
}
