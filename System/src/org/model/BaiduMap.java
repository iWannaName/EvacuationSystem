package org.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.TreeSet;

import org.model.SchemeMake.PathApi.PathRequest;
import org.model.SchemeMake.PathApi.PathReturn;
import org.model.SchemeMake.PathApi.result.Route;
import org.model.SchemeMake.Units.*;

public class BaiduMap {
	private static final Collection<? extends vertex> PointComparator = null;
	public vertex src, dst;
	public vertex[] tmp;
	public vertex[] ans;
	public vertex[] ansOneTime;
	public static TreeSet<edge> es;

	public BaiduMap(vertex src, vertex dst) {
		this.src = src;
		this.dst = dst;
		tmp = new vertex[3];
		tmp[1] = new vertex(src.x, dst.y);
		tmp[2] = new vertex(dst.x, src.y);
	}

	public vertex[] getJunctions() {

		PathRequest apr = new PathRequest(src, dst);
		String JSON = apr.CulculatePath_Drive();
		if (settings.DEBUG_MODE) {
			System.out.println(JSON);
		}
		PathReturn ret = new PathReturn(JSON);
		Route[] rot = ret.getRoutes();
		TreeSet<vertex> hs = new TreeSet();
		for (int i = 1; i < rot.length; i++) {
			vertex[] p = rot[i].getJunctions(BaiduMap.es);
			for (int j = 1; j < p.length; j++)
				hs.add(p[j]);
		}
		ansOneTime = hs.toArray(new vertex[hs.size()]);
		return ansOneTime;
	}

	public vertex[] getAllJunction() {

		es = new TreeSet<edge>();

		TreeSet<vertex> hs = new TreeSet<vertex>();
		vertex tmp1 = new vertex(src.x, dst.y);
		vertex tmp2 = new vertex(dst.x, src.y);
		hs.add(tmp1);
		hs.add(tmp2);
		hs.add(src);
		hs.add(dst);
		vertex[] p1 = BaiduMap.getJunctions(src, dst);
		vertex[] p2 = BaiduMap.getJunctions(src, tmp1);
		vertex[] p3 = BaiduMap.getJunctions(src, tmp2);
		vertex[] p4 = BaiduMap.getJunctions(tmp1, dst);
		vertex[] p5 = BaiduMap.getJunctions(tmp2, dst);
		vertex[] p6 = BaiduMap.getJunctions(tmp1, tmp2);
		vertex[] u = settings.Link(settings.Link(p1, p2), settings.Link(p3, p4));
		vertex[] u1 = settings.Link(p5, p6);

		vertex[] u2 = settings.Link(u, u1);
		for (int i = 0; i < u2.length; i++) {
			hs.add(u2[i]);
		}
		for (int i = 0; i < p1.length; i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.println(p1.length);
			vertex[] Points = BaiduMap.getJunctions(p1[i], tmp1);
			vertex[] Points2 = BaiduMap.getJunctions(p1[i], tmp2);
			for (int i1 = 0; i1 < Points.length; i1++) {
				hs.add(Points[i1]);
			}

			for (int i1 = 0; i1 < Points2.length; i1++) {
				hs.add(Points2[i1]);
			}
		}
		for (int i = 0; i < p2.length; i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p2.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for (int j = 1; j < p5.length; j++) {
				vertex[] points = BaiduMap.getJunctions(p2[i], p5[j]);
				for (int i1 = 1; i1 < points.length; i1++) {
					hs.add(points[i1]);
				}
			}
		}

		for (int i = 0; i < p3.length; i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p3.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for (int j = 0; j < p4.length; j++) {
				vertex[] points = BaiduMap.getJunctions(p3[i], p4[j]);
				for (int i1 = 1; i1 < points.length; i1++) {
					hs.add(points[i1]);
				}
			}
		}
		System.out.println("[Finnishing]");
		ans = (vertex[]) hs.toArray(new vertex[hs.size()]);
		System.out.println(ans.length);
		for (int i = 0; i < ans.length; i++) {
			System.out.print("new BMap.Point(");
			System.out.print(ans[i].toString2());
			System.out.println("),");
		}
		return ans;

	}

	public static vertex[] getJunctions(vertex src, vertex dst) {
		PathRequest apr = new PathRequest(src, dst);

		String JSON = apr.CulculatePath_Drive();
		if (settings.DEBUG_MODE) {
			System.out.println(JSON);
		}
		PathReturn ret = new PathReturn(JSON);

		Route[] rot = ret.getRoutes();
		TreeSet<vertex> hs = new TreeSet();
		for (int i = 1; i < rot.length; i++) {
			vertex[] p = rot[i].getJunctions(BaiduMap.es);
			for (int j = 1; j < p.length; j++)
				hs.add(p[j]);
		}
		vertex[] p = hs.toArray(new vertex[hs.size()]);
		return p;
	}
	public void ChangePercent(int Event_ID,int percent) {
		String sql = "update project set Percent="+Integer.toString(percent)+" where project_id="+Integer.toString(Event_ID);
	}
	public static vertex[] getAllJunction(vertex src, vertex dst,int Event_ID) {
		BaiduMap.es = new TreeSet<edge>();
		TreeSet<vertex> hs = new TreeSet<vertex>();
		vertex tmp1 = new vertex(src.x, dst.y);
		vertex tmp2 = new vertex(dst.x, src.y);
		hs.add(tmp1);
		hs.add(tmp2);
		
		vertex[] p1 = BaiduMap.getJunctions(src, dst);
		vertex[] p2 = BaiduMap.getJunctions(src, tmp1);
		vertex[] p3 = BaiduMap.getJunctions(src, tmp2);
		vertex[] p4 = BaiduMap.getJunctions(tmp1, dst);
		vertex[] p5 = BaiduMap.getJunctions(tmp2, dst);
		vertex[] p6 = BaiduMap.getJunctions(tmp1, tmp2);
		
		vertex[] u = settings.Link(settings.Link(p1, p2), settings.Link(p3, p4));
		vertex[] u1 = settings.Link(p5, p6);
		vertex[] u2 = settings.Link(u, u1);
		
		Query.addSql("update project set percent = 10 where project_id="+Integer.toString(Event_ID));
		for (int i = 0; i < u2.length; i++) {
			hs.add(u2[i]);
		}
		for(int i=0;i<p1.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.println(p1.length);
			vertex [] Points = BaiduMap.getJunctions(p1[i], tmp1);
			vertex [] Points2 = BaiduMap.getJunctions(p1[i], tmp2);
			for(int i1=0;i1<Points.length;i1++) {
				hs.add(Points[i1]);
			}

			for(int i1=0;i1<Points2.length;i1++) {
				hs.add(Points2[i1]);
			}
		}
		Query.addSql("update project set percent = 20 where project_id="+Integer.toString(Event_ID));
		for(int i=0;i<p2.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p2.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for(int j=1;j<p5.length;j++) {
				vertex[] points = BaiduMap.getJunctions(p2[i], p5[j]);
				for(int i1=1;i1<points.length;i1++) {
					hs.add(points[i1]);
				}
			}
		}
		Query.addSql("update project set percent = 30 where project_id="+Integer.toString(Event_ID));

		for(int i=0;i<p3.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p3.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for(int j=0;j<p4.length;j++) {
				vertex[] points = BaiduMap.getJunctions(p3[i], p4[j]);
				for(int i1=1;i1<points.length;i1++) {
					hs.add(points[i1]);
				}
			}
		}
		Query.addSql("update project set percent = 60 where project_id="+Integer.toString(Event_ID));
		System.out.println("[Finnishing]");
		vertex[] ans = (vertex[]) hs.toArray(new vertex[hs.size()]);
//		System.out.println(ans.length);
//		for (int i = 0; i < ans.length; i++) {
//			System.out.print("new BMap.Point(");
//			System.out.print(ans[i].toString2());
//			System.out.println("),");
//		}
		simplifyEdge();
		Query.addSql("update project set percent = 70 where project_id="+Integer.toString(Event_ID));
		return ans;

	}
	public static vertex[] getAllJunction(vertex src, vertex dst) {
		BaiduMap.es = new TreeSet<edge>();
		TreeSet<vertex> hs = new TreeSet<vertex>();
		vertex tmp1 = new vertex(src.x, dst.y);
		vertex tmp2 = new vertex(dst.x, src.y);
		
		vertex[] p1 = BaiduMap.getJunctions(src, dst);
		vertex[] p2 = BaiduMap.getJunctions(src, tmp1);
		vertex[] p3 = BaiduMap.getJunctions(src, tmp2);
		vertex[] p4 = BaiduMap.getJunctions(tmp1, dst);
		vertex[] p5 = BaiduMap.getJunctions(tmp2, dst);
		vertex[] p6 = BaiduMap.getJunctions(tmp1, tmp2);
		vertex[] u = settings.Link(settings.Link(p1, p2), settings.Link(p3, p4));
		vertex[] u1 = settings.Link(p5, p6);
		vertex[] u2 = settings.Link(u, u1);
		
		
		for (int i = 0; i < u2.length; i++) {
			hs.add(u2[i]);
		}
		for(int i=0;i<p1.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.println(p1.length);
			vertex [] Points = BaiduMap.getJunctions(p1[i], tmp1);
			vertex [] Points2 = BaiduMap.getJunctions(p1[i], tmp2);
			for(int i1=0;i1<Points.length;i1++) {
				hs.add(Points[i1]);
			}

			for(int i1=0;i1<Points2.length;i1++) {
				hs.add(Points2[i1]);
			}
		}
		for(int i=0;i<p2.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p2.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for(int j=1;j<p5.length;j++) {
				vertex[] points = BaiduMap.getJunctions(p2[i], p5[j]);
				for(int i1=1;i1<points.length;i1++) {
					hs.add(points[i1]);
				}
			}
		}

		for(int i=0;i<p3.length;i++) {
			System.out.print("[Generating Pathes]");
			System.out.print(i);
			System.out.print('\\');
			System.out.print(p3.length);
			System.out.print("point num :");
			System.out.println(hs.size());
			for(int j=0;j<p4.length;j++) {
				vertex[] points = BaiduMap.getJunctions(p3[i], p4[j]);
				for(int i1=1;i1<points.length;i1++) {
					hs.add(points[i1]);
				}
			}
		}
		System.out.println("[Finnishing]");
		vertex[] ans = (vertex[]) hs.toArray(new vertex[hs.size()]);
		System.out.println(ans.length);
		for (int i = 0; i < ans.length; i++) {
			System.out.print("new BMap.Point(");
			System.out.print(ans[i].toString2());
			System.out.println("),");
		}
		simplifyEdge();
		return ans;

	}
	
	
	public static void simplifyEdge() {
		
		boolean updated = true;
		boolean[] isVisited = new boolean [es.size()];
		TreeSet<edge>temp = BaiduMap.es;
		while (updated) {
			updated=false;
			edge[] e = temp.toArray(new edge[es.size()]);
			temp = new TreeSet<edge>();
			for(int i=0;i<isVisited.length;i++) {
				isVisited[i]=false;
			}
			for (int i1 = 0; i1 < e.length-1; i1++) {
				if(isVisited[i1]) {
					continue;
				}
				for (int i2 = i1 + 1; i2 < e.length; i2++) {
					if(isVisited[i2]) {
						continue;
					}
					edge e1 = e[i1];
					edge e2 = e[i2];
					//bug: why there would be NullPointer?? line 246 332 
					if(e1==null || e2==null) {
						continue;
					}
					vertex s1 = e1.getSrc();
					vertex s2 = e2.getDst();
					vertex d1 = e1.getDst();
					vertex d2 = e2.getDst();
					String ns1 = s1.getRoad_name();
					String ns2 = s2.getRoad_name();
					String nd1 = d1.getRoad_name();
					String nd2 = d2.getRoad_name();
					if(nd1.equals("无名路")) {
						break;
					}
					if(nd2.equals("无名路")) {
						continue;
					}
					if (s1.compareTo(s2) == 0) {
						
						if (nd1.equals(nd2)) {
							double len1 = e[i1].getLen();
							double len2 = e[i2].getLen();
							if (len1 > len2) {
								vertex p1 =e[i1].getSrc(); 
								e[i1].setSrc(d2);
								vertex p2 =e[i1].getSrc(); 
								if(p1.compareTo(p2)!=0) {
									System.out.println(p1);
									System.out.println(p2);
									temp.add(e[i1]);
									isVisited[i1]=true;
									updated=true;
								}
								break;
							} else {
								vertex p1 =e[i2].getSrc(); 
								e[i2].setSrc(d1);
								vertex p2 =e[i2].getSrc(); 
								if(p1.compareTo(p2)!=0) {
									System.out.println(p1);
									System.out.println(p2);
									temp.add(e[i2]);
									isVisited[i2]=true;
									updated=true;
								}
								break;
							}
						}
					}
					else if (d1.compareTo(d2) == 0) {
						if (ns1.equals(ns2)) {
							double len1 = e1.getLen();
							double len2 = e2.getLen();
							if (len1 > len2) {
								vertex p1 =e[i1].getDst();
								e[i1].setDst(s2);
								vertex p2 =e[i1].getDst();
								if(p1.compareTo(p2)!=0) {
									System.out.println(p1);
									System.out.println(p2);
									temp.add(e[i1]);
									isVisited[i1]=true;
									updated=true;
								}
								break;
							} else {
								vertex p1 =e[i2].getDst();
								System.out.print(p1);
								e[i2].setDst(s1);
								vertex p2 =e[i2].getDst();
								if(p1.compareTo(p2)!=0) {
									System.out.println(p1);
									System.out.println(p2);
									temp.add(e[i2]);
									isVisited[i2]=true;
									updated=true;
								}
								break;
							}
						}
					}

				}
			}
			for(int i=0;i<e.length;i++) {
				if(e[i]==null) {
					continue;
				}
				if(!isVisited[i]) {
					temp.add(e[i]);
				}
			}
		}
	}

//	public static void simplifyEdge() {
//		TreeSet<edge> n = new TreeSet<edge>();
//		edge[] e = BaiduMap.es.toArray(new edge[BaiduMap.es.size()]);
//		TreeSet<edge> ts = new TreeSet<edge>();
//		for (int i = 1; i < e.length; i++) {
//			if (e[i].getRoad_name().equals(e[i - 1].getRoad_name())) {
//				ts.add(e[i]);
//			} else {
//				edge[] sameName = ts.toArray(new edge[ts.size()]);
//				if (ts.size() == 1) {
//					ts.clear();
//					ts.add(e[i]);
//					n.add(sameName[0]);
//					continue;
//				}
//				boolean updated = true;
//
//				while (updated) {
//					updated = false;
//					sameName = ts.toArray(new edge[ts.size()]);
//					boolean[] isVisited = new boolean[sameName.length];
//					for (int i1 = 0; i1 < sameName.length; i1++) {
//						isVisited[i1] = false;
//					}
//					for (int i1 = 0; i1 < sameName.length; i1++) {
//						for (int j = i1 + 1; j < sameName.length; j++) {
//							double len1 = sameName[i1].getLen();
//							double len2 = sameName[j].getLen();
//							if (sameName[i1].getSrc().compareTo(sameName[j].getSrc()) == 0) {
//								updated = true;
//								if (len1 > len2) {
//									ts.remove(sameName[i1]);
//									isVisited[i1] = true;
//									sameName[i1].setSrc(sameName[j].getDst());
//									ts.add(sameName[i1]);
//								} else {
//									ts.remove(sameName[j]);
//									isVisited[j] = true;
//									sameName[j].setSrc(sameName[i1].getDst());
//									ts.add(sameName[j]);
//								}
//							} else if (sameName[i1].getDst().compareTo(sameName[j].getDst()) == 0) {
//								updated = true;
//								if (len1 > len2) {
//									ts.remove(sameName[i1]);
//									sameName[i1].setDst(sameName[j].getSrc());
//									ts.add(sameName[i1]);
//								} else {
//									ts.remove(sameName[j]);
//									sameName[j].setDst(sameName[i1].getSrc());
//									ts.add(sameName[j]);
//								}
//							}
//						}
//					}
//					if (!updated) {
//						for (int i1 = 0; i1 < sameName.length; i1++) {
//							n.add(sameName[i1]);
//						}
//					}
//				}
//				ts.clear();
//				ts.add(e[i]);
//			}
//		}
//		BaiduMap.es = n;
//	}

}
