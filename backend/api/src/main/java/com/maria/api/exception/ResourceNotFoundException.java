/**
 * 
 */
package com.maria.api.exception;

/**
 * @author Maria
 *
 */
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * Constructores
	 */
	private static final long serialVersionUID = 6110287434355011646L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
