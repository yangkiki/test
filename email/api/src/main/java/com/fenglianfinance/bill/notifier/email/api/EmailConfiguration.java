/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.notifier.email.api;

/**
 *
 * @author hansy
 */
public class EmailConfiguration {

    private String host;
    private int port;
    private String username;
    private String password;
    private String from;
    private boolean debug = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String account) {
        this.username = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String apiUrl) {
        this.host = apiUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
