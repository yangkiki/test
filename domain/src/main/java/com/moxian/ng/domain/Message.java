package com.moxian.ng.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.moxian.ng.domain.support.AuditableEntity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "messages")
public class Message extends AuditableEntity<UserAccount, Long> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Type {

        NOTIFICATION,
        MESSAGE
    }

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "is_read")
    private boolean read = false;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private UserAccount to;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private UserAccount from;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type = Type.NOTIFICATION;

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Message() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public UserAccount getTo() {
        return to;
    }

    public void setTo(UserAccount to) {
        this.to = to;
    }

    public UserAccount getFrom() {
        return from;
    }

    public void setFrom(UserAccount from) {
        this.from = from;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" + "title=" + title + ", content=" + content + ", read=" + read + ", to=" + to + ", from=" + from + ", type=" + type + '}';
    }

}
