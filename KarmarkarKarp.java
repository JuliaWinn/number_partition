import java.io.File;
import java.util.*;

public class KarmarkarKarp {
	MaxHeap nums;

	/*
	 * Makes a new KarmarkarKarp from a given file
	 */
	public KarmarkarKarp(String filename){
		Scanner in;
		try{
			in=new Scanner(new File(filename));
		}
		catch(Exception e){
			System.out.println("No such file!");
			return;
		}
		nums=new MaxHeap(100);
		for (int i=0; i<100; i++){
			nums.add(in.nextLong());
		}
	}
	/*
	 * Makes a new KarmarkarKarp from any collection
	 */
	public KarmarkarKarp(Collection<Long> c){
		nums = new MaxHeap(c.size());
		Iterator<Long> i = c.iterator();
		while(i.hasNext()){
			nums.add(i.next());
		}
	}
	
	public long residue(){
		while(true){
			// Always has a greatest element
			Long max = nums.poll();
			if (nums.is_empty())
				return max;
			Long next = nums.poll();				
			nums.add(max-next);
		}
	}
}
