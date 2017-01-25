/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.util.*;
 
class Main{
    private final int MAX = 1000000; // the maximum value accepted
    
    static HashMap<Long, Long> counts = new HashMap();
    public static void main(String args[]) throws IOException{
        Main obj = new Main();
	counts.put(1L, 1L);
        obj.beginProcess();
    }

	static long solve(long x) {

		Queue<Long> visited = new LinkedList<>();
		if (counts.containsKey(x)) {
			return counts.get(x);
		}
		long or = x;
		long count = 1;
		Long temp;
		while (x != 1) {
			visited.add(x);
			if (counts.containsKey(x)) {
				count += counts.get(x) - 1;
				break;
			}
			count++;

			if ((x & 1) == 0) {
				x >>= 1;
			} else {
				temp = x << 1;
				x += temp;
				x++;
			}
		}
		while (!visited.isEmpty()) {
			long vis = visited.remove();
			counts.put(vis, count);
			count--;
		}
		return counts.get(or);

	}

	static Long solve(int x, int y) {
		long max = 0;
		for (int i = x; i <= y; i++) {
			long t = solve(i);
			if (t > max) {
				max = t;
			}
		}
		return max;
	}
    
    void beginProcess() throws IOException{
        Scanner in = new Scanner(System.in);
        
        while(in.hasNextInt()){
            int i = in.nextInt();
            int j = in.nextInt();
            
            int from = Math.min(i, j);// getting minimum value of two inputs
            int to = Math.max(i, j);// getting maximum value of two inputs
 
            if(from>0 && from<MAX && to>0 && to<MAX){
                long max = 0;
 
		max = solve(from, to);
                System.out.println(i+" "+j+" "+max);
            }
        }
    }
}