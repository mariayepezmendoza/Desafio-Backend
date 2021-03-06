/**
 * 
 */
package com.maria.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Maria
 *
 */
@Table(name = "change")
@Entity
@Getter
@Setter
@ToString
public class Change {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal mount;

	@Column(name = "new_mount")
	private BigDecimal newMount;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Relation relation;
}
