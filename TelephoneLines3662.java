// This is a Dijkstra problem

import java.io.*;
import java.util.*;

public class TelephoneLines3662 {
	static final int INF = 1000000001;
	static int N,P,K;
	static boolean done[];
	static tele ts[];
	static int roads[][];
	static LinkedList<line> ll[];
	static PriorityQueue<tele> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		 
		pq = new PriorityQueue<tele>();
		
		String input[] = br.readLine().split("\\s");
		N = new Integer(input[0]);
		P = new Integer(input[1]);
		K = new Integer(input[2]);
		
		done = new boolean[N+1];
		ts = new tele[N+1];
		roads = new int[3][P];
		ll = new LinkedList[N+1];
		
		for(int i = 0; i <= N; i++){
			ts[i] = new tele(i);
			ll[i] = new LinkedList<line>();
		}
		
		for(int i = 0; i < P; i++){
			input = br.readLine().split("\\s");
			roads[0][i] = new Integer(input[0]);
			roads[1][i] = new Integer(input[1]);
			roads[2][i] = new Integer(input[2]);
		}
		
		int l = -1;
		int r = INF;
		while(r - l > 1){
			int m = (l+r)/2;
			if(check(m)){
				r = m;
			}else{
				l = m;
			}
		}
		System.out.println(r);
	}
	private static boolean check(int m) {
//		System.out.println("m: "+m);

		for(int i = 0; i <= N; i++){
			ts[i] = new tele(i);
			done[i] = false;
			ll[i].clear();
		}


		for(int i = 0; i < P; i++){
			int t = roads[2][i] > m ? 1 : 0;
			ll[roads[0][i]].add(new line(roads[1][i], t));
			ll[roads[1][i]].add(new line(roads[0][i], t));
		}
		
//		dijkstra
		pq.clear();
		ts[1].dist = 0;
		pq.add(ts[1]);
		
		while(!done[N]){
			if(pq.size() < 1) break;
			tele te = pq.poll();
			if(done[te.no]) continue;
			done[te.no] = true;			
			for(line l : ll[te.no]){
				int d = te.dist + l.dist;
				if(d < ts[l.to].dist){
					ts[l.to].dist = d;
					pq.add(ts[l.to]);
				}				
			}
		}
		


		if(ts[N].dist == INF){
			System.out.println(-1);
			System.exit(0);
		}
		

		if(ts[N].dist <= K){
			return true;
		}else{
			return false;
		}		
	}
}

class tele implements Comparable<tele>{
	int no, dist = 1000000001;
	tele(int no) {
		this.no = no;
	}
	public int compareTo(tele o) {
		return this.dist - o.dist;
	}
}

class line{
	int to,dist;
	line(int to, int dist){
		this.to = to;
		this.dist = dist;
	}
}
