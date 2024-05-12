package com.exam.entity;

import lombok.Data;
//主观题
@Data
public class SubjectQuestion {
    private Integer questionId;

    private String subject;

    private String question;

    private String answer;

    private Integer score;

    private String level;

    private String section;

    private String analysis; //题目解析

    private String inputfile;

    private String outputfile;
}
