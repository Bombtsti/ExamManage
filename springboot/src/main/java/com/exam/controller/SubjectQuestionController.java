package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.entity.SubjectQuestion;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.SubjectQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectQuestionController {

    @Autowired
    private SubjectQuestionServiceImpl subjectQuestionService;

    @PostMapping("/subjectQuestion")
    public ApiResult add(@RequestBody SubjectQuestion subjectQuestion) {
        int res = subjectQuestionService.add(subjectQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/subjectQuestionId")
    public ApiResult findOnlyQuestionId() {
        SubjectQuestion res = subjectQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }
}
