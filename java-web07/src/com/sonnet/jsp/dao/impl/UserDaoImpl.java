package com.sonnet.jsp.dao.impl;

import com.sonnet.jsp.dao.UserDao;
import com.sonnet.jsp.handler.SingleHandler;
import com.sonnet.jsp.jdbc.JdbcUtil;
import com.sonnet.jsp.pojo.User;

public class UserDaoImpl implements UserDao {


    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT username, password, name FROM user WHERE username=?";
        return JdbcUtil.query(sql, new SingleHandler<>(User.class), username);
    }

    @Override
    public Integer getUrlCount(String username, String url) {
        String sql = "SELECT COUNT(*) FROM user_role a INNER JOIN role b ON a.role_id=b.id\n" +
                "INNER JOIN role_permission c ON b.id=c.role_id\n" +
                "INNER JOIN permission d ON c.permission_id=d.id WHERE a.username=? AND d.url=?";

        return JdbcUtil.query(sql, new SingleHandler<>(Integer.class), username, url);
    }

}
