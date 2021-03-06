/**
 * 
 */
package com.maria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maria.api.model.Change;
import com.maria.api.service.ChangeService;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */

@RestController
public class ChangeController {
	
	@Autowired
	private ChangeService changeService;

	@GetMapping("/changes")
	public ResponseEntity<List<Change>> getAllChange(){
		return ResponseEntity.ok().body(changeService.getAllChange());
	}

	@GetMapping("/changes/{id}")
	public ResponseEntity<Observable<Change>> getAllChangeById(@PathVariable long id){
		return ResponseEntity.ok().body(changeService.getChangeById(id));
	}

	@PostMapping("/changes")
	public ResponseEntity<List<Change>> createChange(@RequestBody List<Change> changes){
		return ResponseEntity.ok().body(this.changeService.createChange(changes));
	}

	@PutMapping("/changes/{id}")
	public ResponseEntity<Observable<Change>> updateChange(@PathVariable long id, @RequestBody Change change){
		change.setId(id);
		return ResponseEntity.ok().body(this.changeService.updateChange(change));
	}

	@DeleteMapping("/changes/{id}")
	public HttpStatus deleteChange(@PathVariable long id){
		this.changeService.deleteChange(id);
		return HttpStatus.OK;
	}
}
