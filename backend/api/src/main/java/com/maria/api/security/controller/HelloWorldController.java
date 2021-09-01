/**
 * 
 */
package com.maria.api.security.controller;

/**
 * @author Maria
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/home")
	public String index() {
		return "Bienvenido a BCP!";
	}

}
