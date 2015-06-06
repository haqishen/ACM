// This is a BFS problem
import java.io.*;
import java.util.*;

public class Eight1077 {
	
	static long pow[] = {100000000,10000000,1000000,100000,10000,1000,100,10,1};
	static long goal = 123456780;
	static String res = "";
	static ArrayDeque<Long> pat_que = new ArrayDeque<Long>();
	static ArrayDeque<String> ans_que = new ArrayDeque<String>();
	static HashMap<Long,Integer> history =  new HashMap<Long,Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input[] = br.readLine().trim().split(" +");
		long ini = 0;
		for(int x = 0; x < 9; x++){
			if(!input[x].equals("x")){
				long l = new Long(input[x]);
				ini += l * pow[x];
			}
		}
		
		String A = "unsolvable";
		pat_que.add(ini);
		history.put(ini,0);
		ans_que.add("");
		while(pat_que.size()>0){
			long pat = pat_que.pollFirst();
			String ans = ans_que.pollFirst();
//			debug(pat);
			if(pat == goal){
				A = ans;
				break;
			}
			
//			find out the position of 0
			int s = 0;
			for(int i = 0; i < 9; i++)
				if((pat/pow[i])%10 == 0)
					s = i;
			
			for(int i = 0; i < 4; i++){
				
				long newpat = pat;
				if(i==0){
					if(s==2|s==5|s==8) continue;
					long a = (newpat/pow[s+1])%10;
					newpat += a * (pow[s] - pow[s+1]);
				}else if(i==1){
					if(s==0|s==3|s==6) continue;
					long a = (newpat/pow[s-1])%10;
					newpat += a * (pow[s] - pow[s-1]);
				}else if(i==2){
					if(s==6|s==7|s==8) continue;
					long a = (newpat/pow[s+3])%10;
					newpat += a * (pow[s] - pow[s+3]);
				}else if(i==3){
					if(s==0|s==1|s==2) continue;
					long a = (newpat/pow[s-3])%10;
					newpat += a * (pow[s] - pow[s-3]);
				}

				if(!history.containsKey(newpat)){
					history.put(newpat,0);
//					debug(copy);
					pat_que.addLast(newpat);
					if(i==0)
						ans_que.addLast(ans+"r");
					if(i==1)
						ans_que.addLast(ans+"l");
					if(i==2)
						ans_que.addLast(ans+"d");
					if(i==3)
						ans_que.addLast(ans+"u");
				}
			}
//			debug(pat_que.size());
		}
		System.out.println(A);
	}

	static void debug(Object...o){
		System.err.println(Arrays.deepToString(o));
	}
}
