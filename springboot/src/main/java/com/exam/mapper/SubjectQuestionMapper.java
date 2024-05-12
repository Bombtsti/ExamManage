package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.SubjectQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//主观题
@Mapper
public interface SubjectQuestionMapper {
    @Select("select * from subject_question where questionId in (select questionId from paper_manage where questionType = 4 and paperId = #{paperId})")
    List<SubjectQuestion> findByIdAndType(Integer paperId);

    @Select("select * from subject_question")
    IPage<SubjectQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     * @return FillQuestion
     */
    @Select("select questionId from subject_question order by questionId desc limit 1")
    SubjectQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into subject_question(subject,question,answer,analysis,score,level,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{score},#{level},#{section})")
    int add(SubjectQuestion fillQuestion);

    @Select("select questionId from subject_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);
}
