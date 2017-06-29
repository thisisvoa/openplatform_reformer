/**
 * IOException.java
 */
package com.openplatform.weasel.sdk.exception;

/**IO通讯异常
 * @author Dylan
 * @time 2013-3-16
 */
public class IOCommunicateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IOCommunicateException(String message) {
		super(message);
	}

}
