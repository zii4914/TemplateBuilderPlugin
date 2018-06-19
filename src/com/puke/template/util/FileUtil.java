//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    private static final int BUFFER_SIZE = 8192;

    private FileUtil() {
    }

    public static void createDir(String dirName) {
        checkNotNull(dirName);
        File dir = new File(dirName);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }

    }

    public static void createFile(String filePath) {
        checkNotNull(filePath);
        createFile(new File(filePath));
    }

    public static void createFile(File file) {
        if (!file.exists() || !file.isFile()) {
            File parent = file.getParentFile();
            parent.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static String getContent(String filePath) {
        checkNotNull(filePath);
        return getContent(new File(filePath));
    }

    public static String getContent(File file) {
        checkNotNull(file);
        if (file.exists() && file.isFile()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            try {
                InputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[8192];

                int index;
                while((index = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, index);
                }

                return new String(byteArrayOutputStream.toByteArray());
            } catch (IOException var5) {
                var5.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException("The file is not exists or not a file.");
        }
    }

    public static void writeFile(String filePath, String content) {
        checkNotNull(filePath);
        writeFile(new File(filePath), content);
    }

    public static void writeFile(File file, String content) {
        checkNotNull(file);
        checkNotNull(content);
        if (!file.exists() || !file.isFile()) {
            createFile(file);
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
        } catch (IOException var12) {
            var12.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

    }

    public static void appendContent(String filePath, String content) {
        checkNotNull(filePath);
        appendContent(new File(filePath), content);
    }

    public static void appendContent(File file, String content) {
        checkNotNull(file);
        checkNotNull(content);
        if (!"".equals(content)) {
            String originContent;
            if (file.exists() && file.isFile()) {
                originContent = getContent(file);
            } else {
                createFile(file);
                originContent = "";
            }

            writeFile(file, originContent + content);
        }
    }

    public static void replaceContent(String filePath, String before, String after) {
        checkNotNull(filePath);
        replaceContent(new File(filePath), before, after);
    }

    public static void replaceContent(File file, String before, String after) {
        checkNotNull(file);
        checkNotNull(file);
        checkNotNull(file);
        String originContent = getContent(file);
        if (originContent == null) {
            throw new RuntimeException("Access file content failured.");
        } else {
            String finalContent = originContent.replaceAll(before, after);
            writeFile(file, finalContent);
        }
    }

    public static void deleteFile(String filePath) {
        checkNotNull(filePath);
        deleteFile(new File(filePath));
    }

    public static void deleteFile(File file) {
        checkNotNull(file);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    File[] var2 = subFiles;
                    int var3 = subFiles.length;

                    for(int var4 = 0; var4 < var3; ++var4) {
                        File subFile = var2[var4];
                        deleteFile(subFile);
                    }
                }
            }

            file.delete();
        }
    }

    public static void copy2Dir(String fromPath, String toPath) {
        checkNotNull(fromPath);
        checkNotNull(toPath);
        copy2Dir(new File(fromPath), new File(toPath));
    }

    public static void copy2Dir(File from, File to) {
        checkNotNull(from);
        checkNotNull(to);
        if (!to.exists()) {
            to.mkdirs();
        }

        if (from.exists() && !to.isFile()) {
            String fileName = from.getName();
            File targetFile = new File(to, fileName);
            if (from.isFile()) {
                copy2File(from, targetFile);
            } else if (from.isDirectory()) {
                targetFile.mkdirs();
                File[] subFiles = from.listFiles();
                if (subFiles != null) {
                    File[] var5 = subFiles;
                    int var6 = subFiles.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        File subFile = var5[var7];
                        copy2Dir(subFile, targetFile);
                    }
                }
            }

        } else {
            throw new IllegalArgumentException("The param is invalid.");
        }
    }

    public static void copy2File(String fromPath, String toPath) {
        checkNotNull(fromPath);
        checkNotNull(toPath);
        copy2File(new File(fromPath), new File(toPath));
    }

    public static void copy2File(File from, File to) {
        checkNotNull(from);
        checkNotNull(to);
        if (from.exists() && !from.isDirectory()) {
            try {
                save2File((InputStream)(new FileInputStream(from)), to);
            } catch (FileNotFoundException var3) {
                var3.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("The param is invalid.");
        }
    }

    public static void save2File(byte[] bytes, File to) {
        save2File((InputStream)(new ByteArrayInputStream(bytes)), to);
    }

    public static void save2File(InputStream inputStream, File to) {
        checkNotNull(inputStream);
        checkNotNull(to);
        File parentFile = to.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(to);
            byte[] buffer = new byte[8192];

            int index;
            while((index = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, index);
            }
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException var16) {
                    var16.printStackTrace();
                }
            }

        }

    }

    public static String is2Str(InputStream inputStream) {
        checkNotNull(inputStream);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        try {
            int index;
            try {
                while((index = inputStream.read(buffer)) != -1) {
                    os.write(buffer, 0, index);
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return new String(os.toByteArray());
    }

    public static byte[] is2Bytes(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        } else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            try {
                byte[] buffer = new byte[4096];

                int index;
                while((index = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, index);
                }
            } catch (IOException var12) {
                var12.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }

            }

            return outputStream.toByteArray();
        }
    }

    private static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}
