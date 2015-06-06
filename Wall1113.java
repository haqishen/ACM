// This is a Geometry problem

import java.io.*;
import java.util.*;
public class Wall1113 {
	static int N,L;
	static int[] x,y;
	static vertice vs[];
	static ArrayList<Integer> al = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input[] = br.readLine().split(" +");
		N = new Integer(input[0]);
		L = new Integer(input[1]);
		
		x = new int[N];
		y = new int[N];
		vs = new vertice[N];
		
		for(int i = 0; i < N; i++){
			input = br.readLine().split(" +");
			x[i] = new Integer(input[0]);
			y[i] = new Integer(input[1]);
			vs[i] = new vertice(i, x[i], y[i]);
		}
		Arrays.sort(vs);
		
		for(int i = 0; i < N; i++){
			int no = vs[i].no;
			while(al.size()>1){
				if(!check(no))
					al.remove(al.size()-1);
				else
					break;
			}
			if(!al.contains(no))
				al.add(no);
		}
//		for(int i : al){
//			System.out.println(i);
//		}System.out.println();
		int sz = al.size();
		for(int i = N-2; i >= 0; i--){
			int no = vs[i].no;
			while(al.size()>sz){
				if(!check(no))
					al.remove(al.size()-1);
				else
					break;
			}
			if(!al.contains(no))
				al.add(no);
		}
//		for(int i : al){
//			System.out.println(i);
//		}
		
		double perimeter = 0;
		for(int i = 0; i < al.size()-1; i++){
			perimeter += dist(i,i+1);
//			System.out.println(perimeter);
		}
		if(al.size()>1){
			perimeter += dist(0, al.size()-1);
		}
		perimeter += 2*L*Math.PI;		
		System.out.println(Math.round(perimeter));
	}

	static double dist(int i, int j){
		int no1 = al.get(i);
		int no2 = al.get(j);
		int x1 = x[no1] - x[no2];
		int y1 = y[no1] - y[no2];
		return Math.sqrt(x1*x1 + y1*y1);
	}

	static boolean check(int i){
//		System.out.println(i);
		int len = al.size();
		int nol = al.get(len-1);
		int noll = al.get(len-2);
		int x1 = x[nol] - x[noll];
		int y1 = y[nol] - y[noll];
		int x2 = x[i] - x[nol];
		int y2 = y[i] - y[nol];
		if(x1*y2 - x2*y1 >=0){
			return false;
		}else{
			return true;
		}
	}
}

class vertice implements Comparable<vertice>{
	int no, x, y;
	vertice(int no, int x, int y){
		this.no = no;
		this.x = x;
		this.y = y;
	}
	public int compareTo(vertice o) {
		if(this.x == o.x)
			return this.y - o.y;
		else
			return this.x - o.x;
	}
}
