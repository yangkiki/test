package com.moxian.ng.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ConnectionsDetails implements Serializable {

	private static final long serialVersionUID = -3540497659434148507L;

	private String source;

	private LocalDateTime createOn;

	private UserAccountDetails memberUser;

//	private GroupDetails group;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

//	public GroupDetails getGroup() {
//		return group;
//	}
//
//	public void setGroup(GroupDetails group) {
//		this.group = group;
//	}

	public UserAccountDetails getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(UserAccountDetails connectedUser) {
		this.connectedUser = connectedUser;
	}

	private UserAccountDetails connectedUser;

}
