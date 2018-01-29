package com.stone.ripple.util.tool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

/**
 * 
 * @author stone
 *
 */
public class PdfUtil {

    /**
     * 重写 字符设置方法，解决中文乱码问题
     * 
     */
    public static class MyFontsProvider extends XMLWorkerFontProvider {

        public MyFontsProvider(){
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
            String fntname = fontname;
            if (fntname == null) {
                fntname = "宋体";
            }
            if (size == 0) {
                size = 4;
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }

    private static Logger      logger            = LoggerFactory.getLogger(PdfUtil.class);

    /**
     * PDF生成路径
     */
    private static final String PDF_DOWNLOAD_PATH = "/trialPdf/";

    /**
     * 导出PDF文件
     * 
     * @param content
     * @param response
     */
    public void exportPdf(String fileName, String content, HttpServletResponse response) {

        FileOutputStream fos = null;
        FileInputStream in = null;
        OutputStream out = null;
        Document document = new Document();
        File newPath = null;
        try {
            if (StringUtils.isBlank(fileName)) {
                fileName = DateUtil.getTodayDateTime().replaceAll(" ", "").replaceAll(":", "").replaceAll("-", "")
                           + ".pdf";
            } else {
                fileName += ".pdf";
            }
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            String dicPath = new File(".").getCanonicalPath();
            String srcPath = dicPath + PDF_DOWNLOAD_PATH + fileName;

            newPath = new File(dicPath + PDF_DOWNLOAD_PATH);
            newPath.mkdirs();
            // 删除临时文件
            boolean success = fileDelete(newPath);

            if (success) {
                newPath.mkdirs();
                File file = new File(srcPath);
                fos = new FileOutputStream(file);

                PdfWriter writer = PdfWriter.getInstance(document, fos);

                document.open();
                InputStream htmlInput = new ByteArrayInputStream(content.getBytes("UTF-8"));
                // 使用我们的字体提供器，并将其设置为unicode字体样式
                MyFontsProvider fontProvider = new MyFontsProvider();
                fontProvider.addFontSubstitute("lowagie", "garamond");
                fontProvider.setUseUnicode(true);
                CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

                XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlInput, null, Charset.forName("UTF-8"),
                                                         fontProvider);

                document.close();
                writer.close();
                // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
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
        } catch (DocumentException e) {
            logger.error("Export PDF error :" + e.getMessage());
            throw new RuntimeException("Export PDF error : ", e);
        } catch (IOException e) {
            logger.error("Export PDF error :" + e.getMessage());
            throw new RuntimeException("Export PDF error : ", e);
        } catch (Exception e) {
            logger.error("Export PDF error :" + e.getMessage());
            throw new RuntimeException("Export PDF error : ", e);
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
