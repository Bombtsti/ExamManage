package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.SubjectQuestion;

import java.util.List;

public interface SubjectQuestionService {
    List<SubjectQuestion> findByIdAndType(Integer paperId);

    IPage<SubjectQuestion> findAll(Page<SubjectQuestion> page);

    SubjectQuestion findOnlyQuestionId();

    int add(SubjectQuestion fillQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);
}
