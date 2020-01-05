package org.model.OptimalPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EdmodKarp {
	int maxdata = Integer.MAX_VALUE;
	int[][] capacity;
	int[] flow;
	int[] pre;
	int n;
	LinkedList<Integer> queue;
	public ArrayList<Integer> flows = new ArrayList<>();
	public LinkedList<String> AllPath = new LinkedList<String>();
	public EdmodKarp(int[][] capacity) {
		this.capacity = capacity;
		this.n = capacity.length;
		this.pre = new int[n];
	}

	// 广度优先遍历的查找一条src到des的路径
	int BFS(int src, int des) {
		int i;
		this.queue = new LinkedList<Integer>();
		this.flow = new int[n];
		for (i = 0; i < n; ++i) {
			pre[i] = -1;
		}
		pre[src] = -2;
		flow[src] = maxdata;
		queue.add(src);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			if (index == des) {//这条路径已经找到终点了
				// 找到了增广路径
				// System.out.println("增广路径：");
				// for (int j = 0; j < queue.size(); j++) {
				// System.out.print(queue.get(j)+"-->");
				// }
				break;
			}
			for (i = 0; i < n; ++i) {
				// 找到非源节点未被访问过的可达结点，计算其流量
				if (i != src && capacity[index][i] > 0 && pre[i] == -1) {
					pre[i] = index; // 记录前驱
					flow[i] = Math.min(capacity[index][i], flow[index]); // 关键：迭代的找到增量
					queue.add(i);
				}
			}
		}
		if (pre[des] == -1)// 残留图中不再存在增广路径
			return -1;
		else
			return flow[des];

	}

	  public int maxFlow(int src, int des) {
		int increasement = 0;
		int sumflow = 0;
		while ((increasement = BFS(src, des)) != -1) {
			flows.add(increasement);
			int k = des; // 利用前驱寻找路径
			LinkedList<Integer> path = new LinkedList<Integer>();
			while (k != src) {
				int last = pre[k];
				capacity[last][k] -= increasement; // 改变正向边的容量
				capacity[k][last] += increasement; // 改变反向边的容量
				path.push(last);
				k = last;
			}
			StringBuilder st = new StringBuilder();
			for (int i = 0; i < path.size()-1; i++) {
				st.append("["+path.get(i)+","+path.get(i+1)+"]"+"-->");
			}
			st.append("["+path.get(path.size()-1)+","+des+"]"+"-->");
			AllPath.add(st.toString());
			// System.out.println("-------改变后---------");
			// for(int j=0;j<n;j++)
			// {
			// for(int x=0;x<n;x++)
			// {
			// System.out.print("---"+capacity[j][x]);
			// }
			// System.out.println();
			// }
			sumflow += increasement;
		}
		return sumflow;
	}

	public List<HashMap<String, Integer>> getPeriod() {
		List<HashMap<String, Integer>> result = new ArrayList<HashMap<String, Integer>>();
		List<String[]> list = new ArrayList<String[]>();
		int maxLength = 0;
		for (int i = 0; i < AllPath.size(); i++) {
			String[] temp = AllPath.get(i).split("-->");
			if (temp.length > maxLength) {
				maxLength = temp.length;
			}
			list.add(temp);
		}
		for (int i = 0; i < maxLength; i++) {
			HashMap<String, Integer> temp = new HashMap<String, Integer>();
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).length >= i + 1) {
					if (temp.containsKey(list.get(j)[i])) {
						temp.put(list.get(j)[i], temp.get(list.get(j)[i]) + flows.get(j));
					} else {
						temp.put(list.get(j)[i], flows.get(j));
					}
				}
			}
			result.add(temp);
		}

		return result;
	}
	
	public static void main(String args[]) {
//		int[][] matrix = new int[4][4];
//		matrix[0][1] = 4;
//		matrix[0][3] = 2;
//		matrix[1][2] = 3;
//		matrix[1][3] = 2;
//		matrix[2][3] = 1;

		int[][] matrix = new int[6][6];
		matrix[0][1] = 16;
		matrix[0][2] = 13;
		matrix[1][2] = 10;
		matrix[2][1] = 4;
		matrix[1][3] = 12;
		matrix[2][4] = 14;
		matrix[3][2] = 9;
		matrix[4][3] = 7;
		matrix[3][5] = 20;
		matrix[4][5] = 4;
		
		EdmodKarp edm = new EdmodKarp(matrix);
		int actual = edm.maxFlow(0, 5);
//		System.out.println("-------最终结果---------");
//		for (int j = 0; j < edm.n; j++) {
//			for (int k = 0; k < edm.n; k++) {
//				System.out.print("---" + edm.capacity[j][k]);
//			}
//			System.out.println();
//		}
		System.out.println("最大流为："+actual);
		
		for (int i = 0; i < edm.flows.size(); i++) {
			System.out.println(edm.AllPath.get(i) + ":" + edm.flows.get(i));
		}
	}

}
