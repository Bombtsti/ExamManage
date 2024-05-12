package com.exam.controller;

import jdk.internal.util.xml.impl.Input;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        String inpufile = "";
        StringBuffer sb= new StringBuffer();
        File file = new File("/testfile/test1in.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            int len = -1;
            byte[] bytes = new byte[1024];
            while((len = fis.read(bytes))!=-1){
                String str = new String(bytes,0,len);
                sb.append(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
