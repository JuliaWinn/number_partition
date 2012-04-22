import java.io.File;
import java.util.*;

public class KarmarkarKarp {
	PriorityQueue<Integer> nums;

	public static void main(String[] args){
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i=1; i<=7; i++)
			a.add(i);
		KarmarkarKarp test = new KarmarkarKarp(a);
		System.out.println(test.residue());
	}
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
		nums=new PriorityQueue<Integer>(100, new Comp());
		for (int i=0; i<100; i++){
			nums.add(in.nextInt());
		}
	}
	/*
	 * Makes a new KarmarkarKarp from any collection
	 */
	public KarmarkarKarp(Collection<Integer> c){
		nums = new PriorityQueue<Integer>(1, new Comp());
		Iterator<Integer> i = c.iterator();
		while(i.hasNext()){
			nums.add(i.next());
		}
	}
	
	public int residue(){
		// Always has a greatest element
		Integer max = nums.poll();
		Integer next = nums.poll();
		if (next==null)
			return max;
		nums.add(max-next);
		return residue();
	}
}

class Comp implements Comparator<Integer>{
	public int compare(Integer a, Integer b){
		return b-a;
	}
}
