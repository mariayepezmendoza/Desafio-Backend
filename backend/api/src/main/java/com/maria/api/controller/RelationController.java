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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maria.api.model.Relation;
import com.maria.api.service.RelationService;

import io.reactivex.Observable;

/**
 * @author Maria
 *
 */

@RestController
public class RelationController {
	
	@Autowired
	private RelationService relationService;

	@GetMapping("/relations")
	public ResponseEntity<List<Relation>> getAllRelation(){
		return ResponseEntity.ok().body(relationService.getAllRelation());
	}

	@GetMapping("/relations/{id}")
	public ResponseEntity<Observable<Relation>> getAllRelationById(@PathVariable long id){
		return ResponseEntity.ok().body(relationService.getRelationById(id));
	}

	@PostMapping("/relations")
	public ResponseEntity<List<Relation>> createRelation(@RequestBody List<Relation> relations){
		return ResponseEntity.ok().body(this.relationService.createRelation(relations));
	}

	@PatchMapping("/relations/{id}")
	public ResponseEntity<Observable<Relation>> updateRelation(@PathVariable long id, @RequestBody Relation relation){
		relation.setId(id);
		return ResponseEntity.ok().body(this.relationService.updateRelation(relation));
	}

	@DeleteMapping("/relations/{id}")
	public HttpStatus deleteRelation(@PathVariable long id){
		this.relationService.deleteRelation(id);
		return HttpStatus.OK;
	}

	@GetMapping("/relations/test")
	public ResponseEntity<Observable<Relation>> getAllRelationById(@RequestBody Relation relation){
		return ResponseEntity.ok().body(relationService.getRelationByCurrency(relation));
	}
}
