package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConnectionRequestForm implements Serializable {

	private static final long serialVersionUID = -4415835195125166191L;

	private UserAccountDetails memberUser;

	private UserAccountDetails connectedUser;

	private String source;

	private String status;

	private LocalDateTime createOn;

	public UserAccountDetails getMemberUser() {
		return memberUser;
	}

	public void setMemberUser(UserAccountDetails memberUser) {
		this.memberUser = memberUser;
	}

	public UserAccountDetails getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(UserAccountDetails connectedUser) {
		this.connectedUser = connectedUser;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

}
