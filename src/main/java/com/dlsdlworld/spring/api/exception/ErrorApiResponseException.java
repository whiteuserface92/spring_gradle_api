package com.dlsdlworld.spring.api.exception;

/**
 */
public class ErrorApiResponseException extends Exception {

	/**
	 *
	 * @param status
	 * @param error
	 */
	public ErrorApiResponseException(int status, String error) {
		super(String.format("There is an error(status:%d, message:%s) in the api response", status, error));
	}
}
