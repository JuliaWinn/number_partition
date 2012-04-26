import java.util.Random;
/*
 * Implementation of the normal solution (group into 2 sets). Note that it
 * is slightly more complicated than it needs to be, since instead of calcing
 * the new residue from scratch when you make a step, I set it up so that it
 * just does the math to find what the change in the residue would be. I'm 
 * not sure if this is actually better though, since it's still linear time
 * to copy S anyways.
 */
public class SetSolution implements Solution{
	// The numbers you are partitioning
	long[] nums;
	// Each value is 1 or -1, represents sets
	int[] S;
	// Signed storage of residue
	private long residue;
	
	final static Random gen = new Random();
	
	/*
	 * Makes a random solution with len many numbers
	 */
	public SetSolution(int len){
		nums=new long[len];
		for (int i=0; i<len; i++)
			nums[i]=gen.nextLong();
		
		S = new int[nums.length];
		// Generate random sets
		for (int i=0; i<S.length; i++)
			S[i]= gen.nextBoolean() ? 1 : -1;
		// Calculate the residue
		residue = 0;
		for (int i=0; i<nums.length; i++)
			residue+=S[i]*nums[i];
	}
	
	/*
	 *  Takes a list of numbers, generates a random solution, calc residue
	 */
	public SetSolution(long[] nums){
		this.nums=nums;
		S = new int[nums.length];
		// Generate random sets
		for (int i=0; i<S.length; i++)
			S[i]= gen.nextBoolean() ? 1 : -1;
		// Calculate the residue
		residue = 0;
		for (int i=0; i<nums.length; i++)
			residue+=S[i]*nums[i];
	}
	
	/*
	 * Constructs a solution for specific nums, set, and residue.
	 * ONLY use this when you already know the residue, and you don't
	 * want to do the linear time process of going down the list of values
	 * to calculate it.
	 */
	private SetSolution(long[] nums, int[] S, long res){
		this.nums=nums;
		this.S=S;
		this.residue=res;
	}
	
	// Returns the residue for the solution
	public long residue(){
		return Math.abs(residue);
	}
	
	// Returns a random solution 1 move away
	public SetSolution rand_move(){
		// Make a copy of the solution sets
		int[] newS = new int[S.length];
		System.arraycopy(S, 0, newS, 0, S.length);
		// Make a copy of the residue
		long newRes = residue;
		// Find the element to switch sets
		int to_flip = gen.nextInt(S.length);
		// Move it to the other set, and update the residue
		newS[to_flip] *= -1; 
		newRes += 2*S[to_flip]*nums[to_flip];
		// 50% of the time, flip a second one
		if (gen.nextBoolean()){
			// Find a new position to flip, not the same as the first one
			int to_flip2=to_flip;
			while(to_flip2==to_flip)
				to_flip2=gen.nextInt(S.length);
			// Move it to the other set, and update the residue
			newS[to_flip2] *= -1; 
			newRes += 2*S[to_flip2]*nums[to_flip2];
		}
		return new SetSolution(nums, newS, newRes);
	}
}
