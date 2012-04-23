import java.util.Arrays;
import java.util.Random;


public class PrepartitionSolution implements Solution{
	// The numbers you are partitioning
	long[] nums;
	// The partition: associates an id number with each value
	int[] P;
	
	final static Random gen = new Random();

	
	/*
	 * Takes a list of numbers, generates a random solution
	 */
	public PrepartitionSolution(long[] nums){
		P = new int[nums.length];
		this.nums=nums;
		// Note that unlike spec, choose numbers from 0 to n-1, not 1 to n
		for (int i=0; i<nums.length; i++)
			P[i]=gen.nextInt(nums.length);
	}
	
	private PrepartitionSolution(long[] nums, int[] P){
		this.nums=nums;
		this.P=P;
	}
	
	public Solution rand_move(){
		// Make a new copy of P
		int[] newP = new int[nums.length];
		System.arraycopy(P, 0, newP, 0, P.length);
		
		// Find the index and value to switch to
		int flip_index = gen.nextInt(nums.length);
		int new_id = P[flip_index];
		while (new_id == flip_index)
			new_id = gen.nextInt(nums.length);
		
		newP[flip_index]=new_id;
		
		return new PrepartitionSolution(nums, newP);
	}
	
	public long residue(){
		Long[] sum_by_id = new Long[nums.length];
		for (int i=0; i<nums.length; i++)
			sum_by_id[P[i]]+=nums[i];
		return (new KarmarkarKarp(Arrays.asList(sum_by_id))).residue();
	}
}