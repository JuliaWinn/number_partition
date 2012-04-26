import java.util.Random;
import java.io.*;


public class RandomAlgs {
	static final Random gen = new Random();
	
	/*
	 *  nums: how many numbers to partition
	 *  iters: how many iterations
	 *  set_soln: true if using SetSolution, false if PrepartitionSolution
	 */
	public static long rep_random(boolean set_soln, int iters, long[] numbers){
		// Generate a random solution, make it the best
		long best = set_soln ? new SetSolution(numbers).residue() : 
			new PrepartitionSolution(numbers).residue();
		/*
		 *  The rest of the iterations: gen a random, and if it improves, make
		 *  it the best.
		 */
		long test;
		for (int i=1; i<iters; i++){
            test = set_soln ? new SetSolution(numbers).residue() : 
					new PrepartitionSolution(numbers).residue();
			best = Math.min(best, test);
		}
		return best;
	}
	
	public static long hill_climb(boolean set_soln, int iters, long[] numbers){
		// Remember both the best residue, and the soln which got it
        Solution best = set_soln ? new SetSolution(numbers) : 
			new PrepartitionSolution(numbers);
		long best_residue = best.residue();
		
		Solution test;
		long test_residue;
		// Make a random move, and if it improves, then switch
		for (int i=1; i<iters; i++){
			test = best.rand_move();
			test_residue = test.residue();
			if (test_residue < best_residue){
				best=test;
				best_residue=test_residue;
			}
		}
		return best_residue;
	}
	
	public static long sim_annealing(boolean set_soln, int iters, long[] numbers){
	    
		Solution best = set_soln ? new SetSolution(numbers) : 
			new PrepartitionSolution(numbers);
		Solution current = best;
		long best_residue = best.residue();
		long current_residue = best_residue;
		
		Solution test;
		long test_residue;
		
		for (int i=1; i<iters; i++){
			test = current.rand_move();
			test_residue = test.residue();
			// If it's better than the current, make it current
			if (test_residue < current_residue){
				current = test;
				current_residue = test_residue;
			}
			// Otherwise, with some probability, make it current anyways
			else if (gen.nextDouble()<Math.exp(-(test_residue-current_residue)/
					(Math.pow(10, 10)*Math.pow(.8, Math.floor(i/300))))){
				current = test;
				current_residue = test_residue;
			}
			// If current is the best, then switch the best
			if (current_residue < best_residue){
				best = current;
				best_residue = current_residue;
			}
		}
		return best_residue;
	}
	
    // input file is a list of 100 (unsorted) integers
	public static void main(String[] args) {
	    
        String filename = args[0];
        KarmarkarKarp first = new KarmarkarKarp(filename);
        Long res = first.residue();
        System.out.println(res);
        // run kk with input file, print residue
        
        // generate 50 random instances of the problem as described above
        long[][] instances = new long[50][100];
        
        //note a single Random object is reused here
        long seed = 1000000000000L;
        Random gen = new Random();
        
        
        
        for (int i=0; i < 50; i++) {
            for (int j=0; j < 100; j++) {
                long inst = gen.nextLong();
                inst %= seed;
                if (inst < 0) {
                    inst *= -1;
                }
                instances[i][j] = inst;
            }
        }
        
        
        // for each instance find the result from kk
        Long b1, b2, b3, b4;
        Long b11, b21, b31;
        System.out.println("----------------------");
        
        long prev;
        long c;
        // int[][] times = new int[50][100];
        
        for (int i=0; i < 50; i++) {
            prev = System.currentTimeMillis();
            
            b11 = rep_random(true, 25000, instances[i]);
            c = System.currentTimeMillis();
            b1 = c - prev;
            prev = c;
            System.out.println(b11);
            
            b21 = hill_climb(true, 25000, instances[i]);
            c = System.currentTimeMillis();
            b2 = c - prev;
            prev = c;
            System.out.println(b21);
            
            b31 = sim_annealing(true, 25000, instances[i]);
            c = System.currentTimeMillis();
            b3 = c - prev;
            prev = c;
            System.out.println(b31);
            
            KarmarkarKarp kk = new KarmarkarKarp(instances[i]);
            Long res2 = kk.residue();
            c = System.currentTimeMillis();
            b4 = c - prev;
            System.out.println(res2);
            
            // System.out.println(b1);
            // System.out.println(b2);
            // System.out.println(b3);
            // System.out.println(b4);

        }
        System.out.println("----------------------");
	}
	
}
