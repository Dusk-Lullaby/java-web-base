package com.sonnet.jsp.service.impl;

import com.sonnet.jsp.dao.UserDao;
import com.sonnet.jsp.dao.impl.UserDaoImpl;
import com.sonnet.jsp.pojo.User;
import com.sonnet.jsp.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user == null) return -1;

        return user.getPassword().equals(password) ? 1 : 0;
    }

    @Override
    public boolean hasPermission(String uri, String username) {
        return userDao.getUrlCount(username, uri) > 0;
    }
}
