package com.sonnet.jsp.service;

public interface UserService {

    int login(String username, String password);

    boolean hasPermission(String uri, String username);
}
