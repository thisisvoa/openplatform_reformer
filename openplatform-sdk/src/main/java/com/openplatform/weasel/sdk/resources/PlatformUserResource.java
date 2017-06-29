package com.openplatform.weasel.sdk.resources;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月9日
 */
public class PlatformUserResource {

	public Long id;
	/**
	 * 用户名
	 */
	public String username;
	/**
	 * email
	 */
	public String email;
	/**
	 * 手机号
	 */
	public String mobilePhone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}
