package com.sonnet.jsp.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 对查询的结果集进行处理，具体怎么处理需要用户实现
 * @param <T>
 */
public interface ResultHandler<T> {

    T handle(ResultSet rs) throws SQLException;
}
