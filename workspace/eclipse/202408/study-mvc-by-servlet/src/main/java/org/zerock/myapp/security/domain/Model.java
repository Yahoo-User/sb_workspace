package org.zerock.myapp.security.domain;

import java.io.Serial;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.zerock.myapp.security.util.SharedAttributes;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class Model<V> extends HashMap<String, V> {
	@Serial private static final long serialVersionUID = 1L;
	
	
	public Model(HttpServletRequest req) {
		log.trace("<init> (req) invoked.");
		req.setAttribute(SharedAttributes.MODEL, this);
	} // Constructor
	
	
	public V add(String key, V value) {
		log.trace("add({}, {}) invoked.", key, value);
		return this.put(key, value);
	} // add

} // end class
