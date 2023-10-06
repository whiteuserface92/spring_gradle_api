package com.dlsdlworld.spring.api.validator;

/**

 */
public class AssertTrueValidator {

	public boolean isValid(Boolean bool) {
		return bool == null || bool;
	}
}
