package com.stone.ripple.util.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ImageHandler.class);

	public static void main(String[] args) throws Exception {
		writeWaterImage("D:/wanglei1.png", "D:/wanglei3.jpg", "我是水印");
	}

	/**
	 * 原图加水印
	 * 
	 * @param oldFile
	 * @param outFilePath
	 * @param watermark
	 * @throws Exception
	 */
	public static void writeWaterImage(String oldFile, String outFilePath, String watermark) throws Exception {
		ByteArrayOutputStream outputStream = handleImageStream(oldFile, true, 0, 0, false, 0, watermark);
		writeImage(outputStream, outFilePath);
	}

	/**
	 * 对图片进行等比例处理，不模糊
	 * 
	 * @param oldFile
	 * @param outFilePath
	 * @throws Exception
	 */
	public static void writeRateImage(String oldFile, String outFilePath, String watermark) throws Exception {
		ByteArrayOutputStream outputStream = handleImageStream(oldFile, false, 300, 300, false, 0, watermark);
		writeImage(outputStream, outFilePath);
	}

	/**
	 * 对图片进行模糊处理，大小比例不变
	 * 
	 * @param oldFile
	 * @param outFilePath
	 * @param ambiguity
	 * 
	 * @throws Exception
	 */
	public static void writeBlurImage(String oldFile, String outFilePath, int ambiguity, String watermark)
			throws Exception {
		ByteArrayOutputStream outputStream = handleImageStream(oldFile, true, 0, 0, true, ambiguity, watermark);
		writeImage(outputStream, outFilePath);
	}

	/**
	 * 按指定条件对原图进行处理
	 * 
	 * @param oldFile
	 * @param minFile
	 * @param proportion
	 * @param width
	 * @param height
	 * @param blur
	 */
	public static ByteArrayOutputStream handleImageStream(String oldFile, boolean proportion, int width, int height,
			boolean blur, int ambiguity, String watermark) {

		FileInputStream fileInput = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			fileInput = new FileInputStream(oldFile);
			outputStream = compressPic(fileInput, proportion, width, height, blur, ambiguity, watermark);

		} catch (FileNotFoundException e) {
			logger.error("ImageHandler.writeMinImagefile FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("ImageHandler.writeMinImagefile IOException", e);
		} catch (Exception e) {
			logger.error("ImageHandler.writeMinImagefile Exception", e);
		} finally {
			IOUtils.closeQuietly(fileInput);
			IOUtils.closeQuietly(outputStream);
		}

		return outputStream;
	}

	/**
	 * 图片处理
	 * 
	 * @param input
	 *            文件输入流
	 * @param proportion
	 *            是否指定宽高
	 * @param outputWidth
	 *            指定宽
	 * @param outputHeight
	 *            指定高
	 * @param blur
	 *            是否对图片进行模糊处理
	 * @param ambiguity
	 *            模糊度[0：不模糊，越大越模糊]
	 * @param watermark
	 *            水印文字
	 * @return
	 * @throws Exception
	 */
	private static ByteArrayOutputStream compressPic(InputStream input, boolean proportion, int outputWidth,
			int outputHeight, boolean blur, int ambiguity, String watermark) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Image img = ImageIO.read(input);
		// 判断图片格式是否正确
		if (img.getWidth(null) == -1) {
			throw new RuntimeException("compressPic====图片格式存在问题!不进行压缩!");
		} else {
			int newWidth;
			int newHeight;

			// 判断是否是等比缩放
			if (proportion) {
				newWidth = img.getWidth(null);
				newHeight = img.getHeight(null);
			} else {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
				double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 > rate2 ? rate1 : rate2;
				newWidth = (int) ((img.getWidth(null)) / rate);
				newHeight = (int) ((img.getHeight(null)) / rate);
			}

			BufferedImage src = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

			/* Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢 */
			src.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

			BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
			if (blur) {
				// 压缩后的图片进行模糊处理
				BoxBlurFilter box = new BoxBlurFilter(ambiguity);
				box.filter(src, tag);
			} else {
				tag = src;
			}

			// 添加水印
			if (StringUtils.isNotBlank(watermark)) {
				Graphics2D g = tag.createGraphics();
				g.setColor(Color.WHITE); // 设置字体颜色
				g.setFont(new Font("宋体", Font.BOLD, 50)); // 设置字体和字号
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3F));
				g.rotate(Math.toRadians(300), src.getWidth() / 2, src.getHeight() / 2);
				g.drawString(watermark, src.getWidth() / 2, src.getHeight() / 2); // 把字符串放在对应的坐标处
				g.dispose();
			}

			ImageIO.write(tag, "jpg", outputStream);
		}

		return outputStream;
	}

	private static void writeImage(ByteArrayOutputStream outputStream, String outFilePath) throws Exception {
		FileOutputStream file = new FileOutputStream(outFilePath);
		outputStream.writeTo(file);
	}

}
