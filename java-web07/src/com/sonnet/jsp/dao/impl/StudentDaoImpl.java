package com.sonnet.jsp.dao.impl;

import com.sonnet.jsp.dao.StudentDao;
import com.sonnet.jsp.handler.MultiResultHandler;
import com.sonnet.jsp.jdbc.JdbcUtil;
import com.sonnet.jsp.pojo.Student;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> searchStudents() {
        String sql = "SELECT id,name,sex,age FROM student";
        return JdbcUtil.query(sql, new MultiResultHandler<>(Student.class));
    }
}
