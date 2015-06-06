package DP;

import java.util.*;

public class CowBowling3176 {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {

		int N = s.nextInt();
		int num[][] = new int[N][N];
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j <= i; j++){
				int temp = s.nextInt();
				if(i == 0){
					num[i][j] = temp; continue;
				}
				if(j == 0){
					num[i][0] = num[i-1][0] + temp;
				}else if(j == i){
					num[i][j] = num[i-1][j-1] + temp;
				}else{
					num[i][j] = Math.max(num[i-1][j], num[i-1][j-1]) + temp;
				}				
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++){
			max = Math.max(num[N-1][i], max);
		}
		System.out.println(max);
	}

}
