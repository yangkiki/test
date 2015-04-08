package com.moxian.ng.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ConnectionRequestsDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String source;

	private String status;

	private LocalDateTime createOn;

	private UserAccountDetails memberUser;

	private UserAccountDetails connectedUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
