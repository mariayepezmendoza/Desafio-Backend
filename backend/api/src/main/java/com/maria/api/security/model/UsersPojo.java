package com.maria.api.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersPojo {

	private String username;
	private String password;
	private Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
}
