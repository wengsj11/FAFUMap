package com.wes.map;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame {
	static Point sPoint = new Point(); // 起点
	static Point ePoint = new Point(); // 终点
	static Boolean isClicked = false; // 第一点是否已选取

	public static void main(String[] args) {
		JFrame f = new JFrame();// 创建窗口

		f.setTitle("FAFU地图路线查询");
		f.setSize(1180, 771);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(3);

		final JPanel jp = new MyPanel(); // 创建画板

		f.add(jp);
		f.setVisible(true);
		f.addMouseListener(new MouseAdapter() { // 添加点击事件
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (isClicked == false) { // 第一次点击后 isClicked改为true
					isClicked = true;
					// System.out.println("sPoint->"+sPoint);
					// 此处创建ArrayList，第三次点击会重建ArrayList，清除一二次的路线
					((MyPanel) jp).clickPointList = new ArrayList<Point>();
					// 此处创建ArrayList，第三次点击会重建ArrayList2，清除一二次的起点和终点
					((MyPanel) jp).clickPointList2 = new ArrayList<Point>();
					sPoint.setLocation(e.getX(), e.getY());
					// 第一次点击加入pointlist
					((MyPanel) jp).addClickPoint2(sPoint);

					jp.repaint();
				} else if (isClicked == true) { // 如果已点击一次 则isClicked为true，接收第二次点击，并将isClicked改为false
					ePoint.setLocation(e.getX(), e.getY());
					isClicked = false;
					// System.out.println("ePoint-->"+ePoint);
					// 第二次点击加入pointlist
					((MyPanel) jp).addClickPoint2(ePoint);
					new MainAlgorithm(sPoint, ePoint, jp);
					JOptionPane.showMessageDialog(jp, "约" + MainAlgorithm.getDist2() + "米", "  起点至终点的最短距离",
							JOptionPane.INFORMATION_MESSAGE);
					jp.repaint();
				}
			}
		});

	}

}
