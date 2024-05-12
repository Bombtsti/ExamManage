package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.SubjectQuestion;
import com.exam.mapper.SubjectQuestionMapper;
import com.exam.service.SubjectQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectQuestionServiceImpl implements SubjectQuestionService {

    @Autowired
    private SubjectQuestionMapper subjectQuestionMapper;

    public List<SubjectQuestion> findByIdAndType(Integer paperId) {
        return subjectQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public IPage<SubjectQuestion> findAll(Page<SubjectQuestion> page) {
        return subjectQuestionMapper.findAll(page);
    }

    @Override
    public SubjectQuestion findOnlyQuestionId() {
        return subjectQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(SubjectQuestion subjectQuestion) {
        return subjectQuestionMapper.add(subjectQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return null;
    }
}
