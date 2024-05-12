package com.exam.util;

import com.exam.util.CodeRunner;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Objects;

public class JudgeMachine {
    private CodeRunner mCodeRunner;

    public File getFile(String filename) {
        if (filename == null || filename.length() == 0) return null;
        return new File("./src/main/resources/", filename);
    }

    public JudgeMachine() {
        mCodeRunner = new CodeRunner(CodeRunner.Language.C);
    }

    public int judge(String code, @Nullable File inFile, @NotNull File outFile) {
        File retFile = mCodeRunner.run(code, inFile);
        if (!retFile.exists() || !outFile.exists()) return 0;
        if (Objects.equals(getFileMD5(retFile), getFileMD5(outFile))) {
            return 20;
        }
        return 0;
    }

    /**
     * 计算文件的 MD5 值
     */
    private String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte[] buffer = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
