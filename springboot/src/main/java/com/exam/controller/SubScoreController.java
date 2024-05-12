package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.util.ApiResultHandler;
import com.exam.util.JudgeMachine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Map;

//主观题判分
@RestController
public class SubScoreController {

    @PostMapping("/getSubjectScore")
    public ApiResult getSubjectScore(@RequestBody Map<String,String> map) throws IOException {
        String myanswer = map.get("myanswer");
        String inputfile = map.get("inputfile");
        String outputfile = map.get("outputfile");
        System.out.println(inputfile);


//        //读取inputfile
//        StringBuffer sb = new StringBuffer();
//        ClassPathResource classPathResource = new ClassPathResource(inputfile);
//        try (InputStream is = classPathResource.getInputStream()){
//            byte[] flash = new byte[1024];
//            int len=-1;
//            while((len = is.read(flash))!=-1){
//                String str=new String(flash,0,len);
//                sb.append(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(sb);

//        //编译运行，返回运行结果
        JudgeMachine judgeMachine = new JudgeMachine();
        int judge = judgeMachine.judge(myanswer, judgeMachine.getFile(inputfile), judgeMachine.getFile(outputfile));
        System.out.println(judge);
        return ApiResultHandler.buildApiResult(200,"主观题判断",judge);
    }
}
