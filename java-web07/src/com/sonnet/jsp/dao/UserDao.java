package com.sonnet.jsp.dao;

import com.sonnet.jsp.pojo.User;

public interface UserDao {

    User getUserByUsername(String username);

    Integer getUrlCount(String username, String url);
}
