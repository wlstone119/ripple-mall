package com.stone.ripple.util.tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类WordUtil.java的实现描述： 文本以word的方式导出
 * 
 */
public class WordUtil {

    private static Logger      logger             = LoggerFactory.getLogger(WordUtil.class);

    /**
     * Word生成路径
     */
    private static final String WORD_DOWNLOAD_PATH = "trialWord/";

    /**
     * 导出word文件
     * 
     * @param content
     * @param response
     */
    public void exportWord(String fileName, String content, HttpServletResponse response) {

        FileOutputStream fos = null;
        FileInputStream in = null;
        OutputStream out = null;
        File newPath = null;
        try {
            if (StringUtils.isBlank(fileName)) {
                fileName = DateUtil.getTodayDateTime().replaceAll(" ", "").replaceAll(":", "").replaceAll("-", "")
                           + ".doc";

            } else {
                fileName += ".doc";
            }
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            String dicPath = new File(".").getCanonicalPath();
            String srcPath = dicPath + WORD_DOWNLOAD_PATH + fileName;

            newPath = new File(dicPath + WORD_DOWNLOAD_PATH);
            newPath.mkdirs();
            // 删除临时文件
            boolean success = fileDelete(newPath);
            // 给数据库捞出的数据加上html标签，以使word能识别
            content = "<html>" + content + "</html>";

            if (success) {
                byte b[] = content.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                POIFSFileSystem poifs = new POIFSFileSystem();
                DirectoryEntry directory = poifs.getRoot();
                directory.createDocument("WordDocument", bais);
                newPath.mkdirs();
                File file = new File(srcPath);
                fos = new FileOutputStream(file);
                poifs.writeFilesystem(fos);
                // 设置响应头，控制浏览器下载该文件
                response.setHeader("content-disposition", "attachment;filename=" + fileName);
                // 读取要下载的文件，保存到文件输入流
                in = new FileInputStream(srcPath);
                // 创建输出流
                out = response.getOutputStream();
                // 创建缓冲区
                byte buffer[] = new byte[1024];
                int len = 0;
                // 循环将输入流中的内容读取到缓冲区当中
                while ((len = in.read(buffer)) > 0) {
                    // 输出缓冲区的内容到浏览器，实现文件下载
                    out.write(buffer, 0, len);
                }
            }
        } catch (IOException e) {
            logger.error("Export Word error :" + e.getMessage());
            throw new RuntimeException("Export Word error : ", e);
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
            if (newPath != null) {
                fileDelete(newPath);
            }
        }
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    private boolean fileDelete(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = fileDelete(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return file.delete();
    }
}
