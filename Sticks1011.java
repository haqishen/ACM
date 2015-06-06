// This is a DFS problem
import java.io.*;
import java.util.*;

public class Sticks1011 {
	static int N, goal, sum;
	static int[] sticks;
	static boolean[] done;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			N = new Integer(br.readLine());
			if(N==0) break;
			
			sticks = new int[N];
			done = new boolean[N];
			String input[] = br.readLine().split(" +");
//			long start=System.currentTimeMillis();
			sum = 0;
			for(int i = 0; i < N; i++){
				sticks[i] = new Integer(input[i]);
				sum += sticks[i];
			}				
			Arrays.sort(sticks);
			
			int min = sticks[N-1];
			boolean flag = false;
			for(goal = min; goal < sum; goal++){
				if(sum%goal != 0) continue;
				Arrays.fill(done, false);
				if(dfs(0,N,0,0)){
					System.out.println(goal);
					flag = true;
					break;
				}
			}
			if(!flag) System.out.println(sum);
			
//			long end=System.currentTimeMillis();
//			System.out.println("time:"+(end-start)+"ms");
		}
	}
	
	private static boolean dfs(int now, int index, int cnt, int no) {
		if(cnt*goal == sum) return true;
		for(int i = index-1; i >= 0; i--){
			if(done[i]) continue;
			if(i<N-1)if(!done[i+1] & sticks[i] == sticks[i+1]) continue;
			int tmp = now+sticks[i];
			if(tmp == goal){
				done[i] = true;
				if(dfs(0,N,cnt+1,0)) return true;
				done[i] = false;
			}else if(tmp < goal){
				done[i] = true;
				if(dfs(tmp, i, cnt, no+1)) return true;
				done[i] = false;
				if(now == 0) return false;
			}
			if(no == 0) return false;
		}
		return false;
	}

	static void debug(Object...o){
		System.out.println(Arrays.deepToString(o));
	}
}
