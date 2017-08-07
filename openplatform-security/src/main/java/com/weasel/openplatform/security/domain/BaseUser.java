
package com.weasel.openplatform.security.domain;

import com.weasel.openplatform.security.helper.EncryptHelper;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2014年11月24日
 */
public class BaseUser{
	
	protected Long id;
	/**
	 * 用户名
	 */
	protected String username;
	/**
	 * 密码
	 */
	protected String password;
	/**
	 * 加密盐
	 */
	protected byte[] salt;
	/**
	 * email
	 */
	protected String email;
	/**
	 * 手机号
	 */
	protected String mobilePhone;
    
    public BaseUser(){}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Returns the username associated with this user account;
     *
     * @return the username associated with this user account;
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password for this user.
     *
     * @return this user's password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public Object getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
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
    
    public BaseUser encodePassword(byte[] salt){
    	setSalt(salt);
    	setPassword(EncryptHelper.encryptPBKDF2(getPassword(), salt));
    	return this;
    }
    
}


