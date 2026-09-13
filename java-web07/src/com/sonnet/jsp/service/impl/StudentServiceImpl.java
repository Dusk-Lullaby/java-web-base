package com.sonnet.jsp.service.impl;

import com.sonnet.jsp.dao.StudentDao;
import com.sonnet.jsp.dao.impl.StudentDaoImpl;
import com.sonnet.jsp.pojo.Student;
import com.sonnet.jsp.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> searchStudents() {
        return studentDao.searchStudents();
    }
}
