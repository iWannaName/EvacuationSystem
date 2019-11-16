package org.model;

import org.model.SchemeMake.Units.*;
import java.util.Stack;
public class settings {
	public final static boolean DEBUG_MODE=false;
	public final static String AK = "HhS1Knir0GdBAnQtqwY0ABpjBV2KbtaW"; //百度地图 AK
	/***************************************************************/
	/*	������getJSON(String s)
	 *  ������String s
	 *  ���ܣ��ڰ���JSON���ַ�����Ѱ�Ҳ�����JSON������������������get��
	 *     	   ���ص���������ȡJSON
	 *  ���أ�����Ҫ����ַ���(String)
	 *  ʵ����
	 *  	String JSON = "header, {JSON}";
	 *  	System.out.println(getJSON(JSON));
	 *  �����{JSON}
	 */
	/***************************************************************/
	public static String getJSON(String s) {
		String JSON = s.substring(s.indexOf('{'),s.length());
		return JSON;
	}
	
	/***************************************************************/
	/*	������getJSONLen(String JSON)
	 *  ������int
	 *  ���ܣ���ȡJSON���鳤��
	 *  ���أ�����Ҫ����ַ���(String)
	 *  ʵ����
	 *  	String JSON = "[{1},{2},3,"4"]";
	 *  	System.out.println(getJSONLen(JSON,1));
	 *  �����
	 *  	4
	 */
	/***************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int getJSONLen(String json) {
		if(json.charAt(0)!='['||json.charAt(json.length()-1)!=']') {
			System.out.println("json form is incorrect");
			return -1;
		}
		String JSON = json.substring(1,json.length()-1);
		int index_tmp=1;
		Stack s = new Stack();
		int beg=0;
		int end=beg+1;
		for(int i=0;i<JSON.length();i++) {
			char c = JSON.charAt(i);
			if(s.isEmpty()&&c==',') {
				index_tmp++;
			}
			if(c=='{'||c=='[') {
				s.push(i);
			}
			if(c=='}'||c==']') {
				s.pop();
			}
		}
		return index_tmp;
	}
	
	/***************************************************************/
	/*	������getByIndex(String JSON,int index)
	 *  ������String JSON,int index
	 *  ���ܣ���ȡJSON�����ж�Ӧindexλ�õ�����
	 *  ���أ�����Ҫ����ַ���(String)
	 *  ʵ����
	 *  	String JSON = "[{1},{2},3,"4"]";
	 *  	System.out.println(getByIndex(JSON,1));
	 *  	System.out.println(getByIndex(JSON,2));
	 *  	System.out.println(getByIndex(JSON,3));
	 *  	System.out.println(getByIndex(JSON,4));
	 *  �����
	 *  	{1}
	 *  	{2}
	 *  	3
	 *  	"4"
	 */
	/***************************************************************/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getByIndex(String json,int index) {
		int beg=0;
		int end=beg+1;
		if(json.charAt(0)!='['||json.charAt(json.length()-1)!=']') {
			System.out.println("json form is incorrect");
			return null;
		}
		String JSON = json.substring(1,json.length()-1);
		int index_tmp=1;
		Stack s = new Stack();
		boolean flg_beg = true;
		boolean flg_end = true;
		
		for(int i=0;i<JSON.length();i++) {
			char c = JSON.charAt(i);
			if(c=='{'||c=='[') {
				s.push(i);
			}
			if(c=='}'||c==']') {
				s.pop();
			}
			if(s.isEmpty() && c==',') {
				index_tmp++;
				if(index==index_tmp) {
					flg_beg=false;
					beg = i+1;
				}
				if(index+1==index_tmp) {
					flg_end=false;
					end = i;
					break;
				}
			}
		}
		if(flg_beg) {
			beg=0;
		}
		if(flg_end) {
			end=JSON.length();
		}
		return JSON.substring(beg,end);
			
	}
	/***************************************************************/
	/*	������findAttribute
	 *  ������String JSON,String Attribute
	 *  ���ܣ���JSON��Ѱ��ĳ�����Զ�Ӧ��ֵ��֧�ַ���"{}"��"[]"����������
	 *  ���أ�����Ҫ����ַ���
	 *  ʵ����
	 *  	String JSON ="{"attr":"12345","attr1":{"a":1,"b":2},attr2:["1":1]}";
	 *  	System.out.println(findAttribute(JSON,"attr"));
	 *  	System.out.println(findAttribute(JSON,"attr1"));
	 *  	System.out.println(findAttribute(JSON,"attr2"));
	 *  �����
	 *  	12345
	 *  	{"a":1,"b":2}
	 *  	["1":1]
	 */
	/***************************************************************/
	public static String findAttribute(String JSON,String Attribute) {
		int pos = JSON.indexOf(Attribute);
		int d = 0;
		while(JSON.charAt(d+pos)!=':') {
			d++;
		}
		while(JSON.charAt(d+pos)==' ' || JSON.charAt(d+pos)=='\"') {
			d++;
		}
		d++;
		pos = d+pos;
		if(JSON.charAt(pos)=='{') {
			Stack s = new Stack();
			int end = 0;
			for(int i = pos; i<JSON.length();i++) {
				char c = JSON.charAt(i);
				if(c=='{') {
					s.push(i);
				}
				else if(c=='}') {
					int k = (int) s.pop();
					if(k==pos) {
						end = i+1;
						break;
					}
				}
			}
			String ret = JSON.substring(pos,end);
			return ret;
		}
		else if(JSON.charAt(pos)=='['){
			Stack s = new Stack();
			int end = 0;
			for(int i = pos; i<JSON.length();i++) {
				char c = JSON.charAt(i);
				if(c=='[') {
					s.push(i);
				}
				else if(c==']') {
					int k = (int) s.pop();
					if(k==pos) {
						end = i+1;
						break;
					}
				}
			}
			String ret = JSON.substring(pos,end);
			return ret;
		}
		else {
			int beg = pos;
			int end = pos+1;
			if(JSON.charAt(beg)=='\"') {
				beg ++ ;
				while(JSON.charAt(end)!='\"') {
					end++;
				}
				String ret = JSON.substring(beg,end);
				return ret;
			}
			else {
				while(JSON.charAt(end)!=','&&JSON.charAt(end)!='}') {
					end++;
				}
				String ret = JSON.substring(beg,end);
				return ret;
			}
		}
	}
	public static vertex[] Link(vertex []a,vertex []b) {
		int index=0;
		vertex[]ret = new vertex[a.length+b.length];
		for(int i=0;i<a.length;i++) {
			ret[index++]=a[i];
		}
		for(int i=0;i<b.length;i++) {
			ret[index++]=b[i];
		}
		return ret;
	}
}
