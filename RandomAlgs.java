import java.util.Random;


public class RandomAlgs {
	static final Random gen = new Random();
	
	/*
	 *  nums: how many numbers to partition
	 *  iters: how many iterations
	 *  set_soln: true if using SetSolution, false if PrepartitionSolution
	 */
	public static long rep_random(int nums, int iters, boolean set_soln){
		// Generate a random solution, make it the best
		long best = set_soln ? new SetSolution(nums).residue() : 
			new PrepartitionSolution(nums).residue();
		/*
		 *  The rest of the iterations: gen a random, and if it improves, make
		 *  it the best.
		 */
		long test;
		for (int i=1; i<iters; i++){
			test = set_soln ? new SetSolution(nums).residue() : 
				new PrepartitionSolution(nums).residue();
			best = Math.min(best, test);
		}
		return best;
	}
	
	public static long hill_climb(int nums, int iters, boolean set_soln){
		// Remember both the best residue, and the soln which got it
		Solution best = set_soln ? new SetSolution(nums) : 
			new PrepartitionSolution(nums);
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
	
	public static long sim_annealing(int nums, int iters, boolean set_soln){
		Solution best = set_soln ? new SetSolution(nums) : 
			new PrepartitionSolution(nums);
		long best_residue = best.residue();
		
		Solution current = best;
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
}
