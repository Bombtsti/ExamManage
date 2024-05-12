package com.exam.util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CodeRunner {
    private Language mLanguage;
    private File mWorkDir;

    /**
     * 根据语言类型创建一个代码运行器
     *
     * @param l 要编译判断的语言
     */
    public CodeRunner(Language l) {
        mLanguage = l;
        mWorkDir = new File(".\\ctmp");
    }

    /**
     * 根据代码，与该代码需要的输入文件，执行代码，执行结果输出到相应的结果文件中
     *
     * @param code    要执行的代码
     * @param inFile  样例输入文件
     * @return 返回执行输出的结果文件
     */
    @NotNull
    public File run(String code, @Nullable File inFile) {
        File exeFile = generateExecutableFile(code);
        File retFile = new File(mWorkDir, exeFile.getName() + "_ret.txt");
        try {
            String cmd = exeFile.getCanonicalPath();
            ProcessBuilder processBuilder = new ProcessBuilder(cmd);

            // 如果有输入
            if (inFile != null && inFile.exists() && inFile.canRead()) {
                processBuilder.redirectInput(ProcessBuilder.Redirect.from(inFile));

            }
            processBuilder.directory(mWorkDir)
                    .redirectOutput(ProcessBuilder.Redirect.to(retFile));

            Process p = processBuilder.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return retFile;
    }

    /**
     * 根据输入的代码字符串，编译成可执行文件
     *
     * @param code 代码字符串
     * @return 编译运行成功，则返回可执行文件
     */
    private File generateExecutableFile(String code) {
        if (code == null || code.isEmpty()) return null;

        // 存储代码到随机文件
        File inFile = getRandomFile();
        try {
            OutputStream os = new FileOutputStream(inFile.getCanonicalFile(), false);
            os.write(code.getBytes(StandardCharsets.UTF_8));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // 编译
            File outFile = new File(inFile.getCanonicalPath() + "_o.exe");
            String cplCmd = getCompileCommand(inFile.getCanonicalPath(), outFile.getCanonicalPath());
            Process cplProcess = Runtime.getRuntime().exec(cplCmd);
            while (true) {
                // 等待编译进程结束，避免无法访问文件
                if (cplProcess.isAlive()) continue;
                break;
            }
            return outFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File getRandomFile() {
        try {
            if (!mWorkDir.exists()) {
                Files.createDirectory(Paths.get(mWorkDir.getCanonicalPath()));
            }
            File file = new File(mWorkDir, new Random().nextInt() + getExtension());
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getExtension() {
        switch (mLanguage) {
            case C:
                return ".c";
            case CPP:
                return ".cpp";
            case Java:
                return ".java";
            case CSharp:
                return ".cs";
            default:
                return "";
        }
    }

    private String getCompileCommand(String infile, String outfile) {
        String cmd = "";
        switch (mLanguage) {
            case C:
                String formatStr = "gcc %1$s -o %2$s";
                cmd = String.format(formatStr, infile, outfile);
                break;
            case CPP:
            case Java:
            case CSharp:
            default:
        }
        return cmd;
    }

    public enum Language {
        C, CPP, Java, CSharp
    }
}
