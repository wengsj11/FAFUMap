package com.wes.map;

import java.awt.Point;

import javax.swing.JPanel;

/**
 * 使用Dijkstra算法计算出最短路径各点， 并添加point到在MainFrame创建的JPanel里
 * 
 * @author wes
 *
 */

public class MainAlgorithm {

	static MyPanel jp; // 此为传入的Jpanel

	public MyPanel getJp() {
		return jp;
	}

	public void setJp(MyPanel jp) {
		MainAlgorithm.jp = jp;
	}

	static Point sPoint; // 起点

	static Point ePoint; // 终点

	int vex_num; // 表示站点个数

	static int start_point; // 表示出发站点的序号

	static char[] station = new char[23]; // 站点名（大写字母）字符数组
	// 地图各点的X,Y坐标数组
	static int[] X = { 112, 126, 269, 445, 269, 686, 684, 444, 799, 800, 687, 891, 855, 687, 920, 560, 443, 270, 269,
			388, 1040, 838, 562 };

	static int[] Y = { 128, 216, 218, 220, 352, 220, 126, 352, 357, 222, 352, 223, 99, 470, 364, 467, 467, 468, 607,
			606, 373, 475, 351 };

	// 地图邻接矩阵的二维数组
	static int[][] adjmat = {
			{ 9999, 326, 9999, 9999, 9999, 9999, 1000, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 326, 9999, 161, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 161, 9999, 246, 231, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 246, 9999, 9999, 360, 9999, 178, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 231, 9999, 9999, 9999, 9999, 235, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 120,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 360, 9999, 9999, 100, 9999, 9999, 204, 237, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 1000, 9999, 9999, 9999, 9999, 100, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 178, 235, 9999, 9999, 9999, 9999, 9999, 373, 9999, 9999, 9999, 9999, 9999, 325, 9999,
					9999, 9999, 9999, 9999, 186 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 190, 9999, 9999, 9999, 312, 9999, 9999, 9999,
					9999, 9999, 9999, 329, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 204, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 237, 9999, 9999, 190, 9999, 9999, 9999, 9999, 188, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 186 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 176, 9999, 233, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 176, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 188, 9999, 9999, 9999, 9999, 111, 9999, 9999,
					9999, 9999, 9999, 173, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 312, 9999, 9999, 233, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 9999, 276, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 111, 9999, 9999, 283, 9999,
					9999, 9999, 9999, 9999, 225 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 325, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 283, 9999, 180,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 120, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 180, 9999,
					233, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 233,
					9999, 225, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999,
					9999, 225, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 276, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 9999, 329, 9999, 9999, 9999, 9999, 173, 9999, 9999, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 },
			{ 9999, 9999, 9999, 9999, 9999, 9999, 9999, 186, 9999, 9999, 186, 9999, 9999, 9999, 9999, 225, 9999, 9999,
					9999, 9999, 9999, 9999, 9999 } };

	static int[] prev = new int[23]; // 表示最短路径上前驱站点位置数组

	static int[] dist = new int[23]; // 最短距离数组

	static int dist2; // 最短距离

	/**
	 * Dijkstra算法
	 * 
	 * @param u 起始点
	 */

	static void Path_Dijkstra(int u) // 求解最短路径的Dijkstra算法
	{
		int INF = 9999;
		// 测试数据中的不连通是9999
		// 所以假定无穷大为9999
		int i, j;
		int k = 0;
		int[] s = new int[23];// 用来记录点是否被取出
		int min = INF;
		for (i = 0; i < 23; ++i) {
			prev[i] = u;
			// 将所有的节点的前驱节点改为起始点
			dist[i] = adjmat[u][i];
			// 距离为起始点到其的距离
			s[i] = 0;
			// 被取出为否
		}
		s[u] = 1;// 起始点被取出
		prev[u] = -1;// 起始点的前驱节点改为-1
		for (i = 1; i < 23; ++i) {
			min = INF;// 先将最小距离改为无穷大，方便进行比较找最小
			for (j = 0; j < 23; ++j)
				if (s[j] == 0 && dist[j] < min) {
					// 如果存在较小的距离
					min = dist[j];
					k = j;
				}
			if (min == INF)
				continue; // 如果min还是无穷大，那就是找不到。从i+1继续循环，跳过后面的步骤
			s[k] = 1;// 标记改点已经取出
			for (j = 0; j < 23; ++j) {
				// 对点的距离进行调整
				if (s[j] == 0 && dist[j] > dist[k] + adjmat[k][j]) {
					// 如果点没被取出，且通过找到的点k从起始点到达j点距离更小
					// 那就改变从起始点到j点的距离
					dist[j] = dist[k] + adjmat[k][j];
					prev[j] = k;
					// 修改前驱节点
				}
			}
		}
	}

	/**
	 * 
	 * @param v 终点
	 * @return 返回已添加最短路径各点的JPanel
	 */

	static void Output_path(int v) // 依据prev[]和dist[]输出某一终点的最短路径和最短距离
	{
		int x = 0, y = 0;
		jp.addClickPoint((int) getsPoint().getX(), (int) getsPoint().getY());// 添加选定起点

		for (int i = 0; i < 23; ++i) {
			station[i] = (char) ('A' + i);
		}
		int[] stack = new int[23];
		int top = 0;
		int t = v;
		stack[++top] = t;
		while (prev[t] != -1) {
			// 开始节点的前驱节点为-1
			// 如果一个节点的前驱节点不是-1
			// 就得将其前驱节点入栈
			stack[++top] = prev[t];
			t = prev[t];
			// 将t的值改为前驱节点
		}
		// System.out.print(station[start_point - 1]);

		for (int i = 0; i < 23; i++) {
			if (i == start_point - 1) {
				x = X[i];
				y = Y[i];
				break;
			}
		}
		jp.addClickPoint(x, y);// 添加起点节点point
		jp.repaint();
		top--;// 栈顶是开始节点，直接去掉
		// 因为格式。。。所以这么做
		while (top != 0) {
			for (int i = 0; i < 23; i++) {
				if (i == stack[top]) {
					x = X[i];
					y = Y[i];
					break;
				}
			}
			jp.addClickPoint(x, y);
			jp.repaint();
			System.out.print(" --> " + station[stack[top--]]);

		}
		System.out.print(": " + dist[v]);

		setDist2(dist[v]);

		jp.addClickPoint((int) getePoint().getX(), (int) getePoint().getY());// 添加终点节点point
		jp.setBounds(0, 0, 1180, 771);// 设置jpanel大小
	}


	public static int getDist2() {
		return dist2;
	}

	public static void setDist2(int dist2) {
		MainAlgorithm.dist2 = dist2;
	}

	public static Point getsPoint() {
		return sPoint;
	}

	public static void setsPoint(Point sPoint) {
		MainAlgorithm.sPoint = sPoint;
	}

	public static Point getePoint() {
		return ePoint;
	}

	public static void setePoint(Point ePoint) {
		MainAlgorithm.ePoint = ePoint;
	}

	/**
	 * 
	 * @param sPoint
	 *            选取的起点
	 * @param ePoint
	 *            选取的终点
	 */
	MainAlgorithm(Point sPoint, Point ePoint, JPanel jp) {
		setJp((MyPanel) jp);
		setePoint(ePoint);
		setsPoint(sPoint);
		int i;
		// Scanner sc = new Scanner(System.in);
		// System.out.print("请输入起始点数字：\n");
		// start_point = sc.nextInt();
		start_point = getPointNum(getClosestPoint(getsPoint())) + 1;
		
		for (i = 0; i < 23; i++)
			station[i] = (char) ('A' + i);
		
		//传入起点通过算法找出最短路径
		Path_Dijkstra(start_point - 1);

		int end = getPointNum(getClosestPoint(getePoint()));// 输出最短路径和距离 //
		//传入终点设置最短路径
		Output_path(end);
	}

	/**
	 * 
	 * @param point
	 * @return 返回point对应地图数据所选取点为第几个
	 */

	int getPointNum(Point point) {
		for (int i = 0; i < 23; i++) {
			if (point.getX() == X[i] && point.getY() == Y[i])
				return i;

		}
		return 0;
	}

	/**
	 * @param point
	 *            选定的点
	 * @param min
	 *            最短距离
	 * @param x1,y1
	 *            选定点的坐标
	 * @param x2,y2
	 *            最近点的坐标
	 * @return cPoint 最近的点
	 */
	Point getClosestPoint(Point point) {
		int min = 9999;
		int s = 0;
		int x1 = (int) point.getX();
		int y1 = (int) point.getY();
		int x2 = 0, y2 = 0;
		for (int i = 0; i < 23; i++) {
			// 使用两点间距离公式√(x1-x2)^2+(y1-y2)^2
			s = (int) Math.sqrt(Math.pow(x1 - X[i], 2) + Math.pow(y1 - Y[i], 2));
			if (s < min) {
				min = s;
				x2 = X[i];
				y2 = Y[i];
			}
		}
		Point cPoint = new Point(x2, y2);
		return cPoint;
	}

};