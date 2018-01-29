package com.stone.ripple.util.code;

import com.alibaba.fastjson.util.IOUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

@SuppressWarnings("restriction")
public class QRUtil {

    /**
     * 生成二维码
     * @param contents 内容
     * @param width 二维码宽度
     * @param height 二维码高度
     */
    public static String generateQR(String contents, int width, int height) {
        Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(contents.getBytes("UTF-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(byteMatrix, "png", bao);
            return Base64Code(bao.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            IOUtils.close(bao);
        }
    }

    /**
     * 生成base64的二维码
     * @param b
     * @return
     */
	public static String Base64Code(byte[] b) {
        BASE64Encoder encoder = new BASE64Encoder();
        String codeBase64 = "";
        StringBuilder pictureBuffer = new StringBuilder();
        pictureBuffer.append(encoder.encode(b));
        System.out.println(pictureBuffer.toString());
        codeBase64 = pictureBuffer.toString();
        return codeBase64;
    }
    

    /**
     * 链接生成二维码缓冲流
     * 
     * @param contents 内容
     * @param width 二维码宽度
     * @param height 二维码高度
     * @throws WriterException 
     * @throws IOException 
     */
    public static BufferedImage getBufferImage(String url, int width, int height) throws WriterException, IOException {
        Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        
        // 指定编码格式
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ByteArrayInputStream bin = null;
        try {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(url.getBytes("UTF-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(byteMatrix, "png", bao);
            bin = new ByteArrayInputStream(bao.toByteArray());
            
            return ImageIO.read(bin);
        } finally{
            IOUtils.close(bao);
            IOUtils.close(bin);
        }
    }

    public static void main(String[] args) {
        String contents = "http://www.baidu.com";
        QRUtil.generateQR(contents, 300, 300);
        System.out.println("successful");
    }
}
