package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<<<<<<< HEAD
=======
import com.exam.entity.Student;
>>>>>>> e0009d7 (增加主观题类型)
import com.exam.entity.Teacher;

import java.util.List;

public interface TeacherService {

    IPage<Teacher> findAll(Page<Teacher> page);

    public List<Teacher> findAll();

    public Teacher findById(Integer teacherId);

    public int deleteById(Integer teacherId);

    public int update(Teacher teacher);

    public int add(Teacher teacher);
<<<<<<< HEAD
=======

    public int updatePwd(Teacher teacher);
>>>>>>> e0009d7 (增加主观题类型)
}
