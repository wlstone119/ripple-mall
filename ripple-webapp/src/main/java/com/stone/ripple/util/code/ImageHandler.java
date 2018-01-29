package com.stone.ripple.util.code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;

/**
 * 对两张图片进行合并
 *
 * @author stone 2017年11月24日.
 */
public class ImageHandler {

	/**
	 * 导入本地图片到缓冲区
	 * 
	 * @throws IOException
	 */
	public BufferedImage loadImageLocal(String imgUrl) throws IOException {
		return ImageIO.read(new File(imgUrl));
	}

	/**
	 * 导入网络图片到缓冲区
	 * 
	 * @throws IOException
	 */
	public BufferedImage loadImageUrl(String imgUrl) throws IOException {
		URL url = new URL(imgUrl);
		return ImageIO.read(url);
	}

	/**
	 * 生成新图片到本地
	 * 
	 * @throws IOException
	 */
	public void writeImageLocal(String newImage, BufferedImage img) throws IOException {
		if (newImage != null && img != null) {
			File outputfile = new File(newImage);
			ImageIO.write(img, "jpg", outputfile);
		}
	}

	public BufferedImage megerImage(BufferedImage minImage, BufferedImage maxImage, int leftOffset, int upOffset) {
		int w = minImage.getWidth();
		int h = minImage.getHeight();

		Graphics2D g = maxImage.createGraphics();
		g.drawImage(minImage, leftOffset, upOffset, w, h, null);
		g.dispose();
		return maxImage;
	}

	public static void main(String[] args) throws IOException, WriterException {

		ImageHandler tt = new ImageHandler();

		BufferedImage d = tt.loadImageLocal("/Users/stone/Downloads/12.png");
		BufferedImage b = QRUtil.getBufferImage("http://www.baidu.com", 400, 400);

		tt.writeImageLocal("/Users/stone/Downloads/cc.jpg", tt.megerImage(b, d, 175, 485));

		// 将多张图片合在一起
		System.out.println("success");
	}
}
