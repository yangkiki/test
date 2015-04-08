package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FansForm implements Serializable {

	private static final long serialVersionUID = -9069730988536092598L;

	private UserAccountDetails send;

	private UserAccountDetails recept;
	
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserAccountDetails getSend() {
		return send;
	}

	public void setSend(UserAccountDetails send) {
		this.send = send;
	}

	public UserAccountDetails getRecept() {
		return recept;
	}

	public void setRecept(UserAccountDetails recept) {
		this.recept = recept;
	}

	private LocalDateTime createOn;

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

}
