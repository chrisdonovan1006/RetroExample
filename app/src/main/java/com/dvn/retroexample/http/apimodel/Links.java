package com.dvn.retroexample.http.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Christopher on 14/05/2017.
 * <p>
 * (Class Info: )
 */

public class Links {
	@SerializedName("self")
	@Expose
	private String self;
	@SerializedName("next")
	@Expose
	private String next;
	
	/**
	 *
	 * @return
	 *     The self
	 */
	public String getSelf() {
		return self;
	}
	
	/**
	 *
	 * @param self
	 *     The self
	 */
	public void setSelf(String self) {
		this.self = self;
	}
	
	/**
	 *
	 * @return
	 *     The next
	 */
	public String getNext() {
		return next;
	}
	
	/**
	 *
	 * @param next
	 *     The next
	 */
	public void setNext(String next) {
		this.next = next;
	}
}
