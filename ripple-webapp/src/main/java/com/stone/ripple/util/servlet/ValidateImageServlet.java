package com.stone.ripple.util.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ValidateImageServlet extends HttpServlet {

	private static final long serialVersionUID = -4497415180559321040L;

	private static Logger logger = Logger.getLogger(ValidateImageServlet.class);

	public void init(ServletConfig conf) throws ServletException {
		long startTime = System.currentTimeMillis();
		logger.info("Initializing servlet " + conf.getServletName());
		super.init(conf);
		long elapsedTime = System.currentTimeMillis() - startTime;
		logger.info((new StringBuilder()).append(conf.getServletName()).append(getServletName())
				.append("': initialization completed in ").append(elapsedTime).append(" ms").toString());
	}

	public void doGet(HttpServletRequest res, HttpServletResponse req) throws ServletException, IOException {
		req.setContentType("image/jpeg");
		req.setHeader("Pragma", "No-cache");
		req.setHeader("Cache-Control", "no-cache");
		req.setDateHeader("Expires", 0L);
		HttpSession session = res.getSession();

		int width = 60;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.getGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", 0, 18));
		g.setColor(getRandColor(160, 200));

		Random random = new Random();
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}

		session.setAttribute("VerifyCode", sRand);

		g.dispose();
		ImageIO.write(image, "JPEG", req.getOutputStream());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
