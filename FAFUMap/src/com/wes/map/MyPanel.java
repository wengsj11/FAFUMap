package com.wes.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	/**
	 * JPanel类
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Point> clickPointList = new ArrayList<Point>();
	ArrayList<Point> clickPointList2 = new ArrayList<Point>();

	/*
	 * public void addClickPoint(int x,int y) 
	 * 
	 * 此方法添加传入路线各点的坐标至clickPointList
	 */
	public void addClickPoint(int x, int y) {
		Point point = new Point();
		point.setLocation(x, y);
		clickPointList.add(point);
	}

	/*
	 * public void addClickPoint2(Point point) 
	 * 
	 * 此方法添加传入的终点和起点至clickPointList2
	 */
	public void addClickPoint2(Point point) {
		clickPointList2.add(point);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon(MainFrame.class.getResource("map.jpg"));
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);

		// 此处循环clickPointList各点，将各点用直线绘制连接
		for (int i = 1; i < clickPointList.size(); i++) {
			Point currPoint = clickPointList.get(i);

			Point prePoint = clickPointList.get(i - 1);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(8.0f));
			g.drawLine((int) prePoint.getX() - 8, (int) prePoint.getY() - 30, (int) currPoint.getX() - 8,
					(int) currPoint.getY() - 30);
		}

		// 此处绘制鼠标点击点效果
		for (int i = 0; i < clickPointList2.size(); i++) {
			String s = "终点";
			g.setColor(Color.BLACK);
			g.setFont(new Font(null, Font.BOLD, 30));
			Point point = clickPointList2.get(i);
			if (i % 2 == 0)
				s = "起点";
			g.drawString(s, (int) point.getX() + 6, (int) point.getY() - 30);

			ImageIcon iconL = new ImageIcon(MyPanel.class.getResource("location3.png"));
			Image imageL = iconL.getImage();
			g.drawImage(imageL, (int) point.getX() - 39, (int) point.getY() - 95, null);

		}

		repaint();

	}
}
