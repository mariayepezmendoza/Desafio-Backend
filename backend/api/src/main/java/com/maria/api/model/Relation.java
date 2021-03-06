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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Maria
 *
 */

@Table(name = "relation")
@Entity
@Getter
@Setter
@ToString
public class Relation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "origin_currency")
	private String originCurrency;

	@Column(name = "destination_currency")
	private String destinationCurrency;

	@Column(name = "change_type")
	private BigDecimal changeType;
}
