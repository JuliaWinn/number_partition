/*
 * NOTE: this is basically just an adaptation of NodeHeap from PA1, but
 * from node to int and from min to max
 */

public class MaxHeap {
	// Array to store the heap
	long[] heap;
	// keeps track of the first null location
	int last;
	
	MaxHeap(int size){
		heap=new long[size];
		last=0;
	}
	
	// Swaps the elements at given indices
	private void swap(int i, int j){
		long temp = heap[i];
		heap[i]=heap[j];
		heap[j]=temp;
	}

	
	//  Adds the number to the heap, bubbles it up
	public void add(Long i){
		heap[last]=i;
		int index=last;
		last++;
		while (heap[index]>heap[index/2]){
			swap(index,index/2);
			index=index/2;
		}
	}
	
	// Returns the head of the heap destructively
	public long poll(){
		long highest=heap[0];
		heap[0]=heap[last-1];
		last--;
		fix(0);
		return highest;
	}
	
	// Fixes the subheap with the head at the given index
	public void fix (int index){
		int max_child;
		boolean cont=true;
		while (cont) {
			max_child=-1;
			// If has two children, find the larger one
			if (2*index+1<last){
				max_child = (heap[2*index]>heap[2*index+1]) ? 2*index
						: 2*index+1;
			}
			// Otherwise find the only child
			else if (2*index<last) {
				max_child = 2*index;
			}
			// If the largest child is larger than at the index, swap and cont
			if (max_child!=-1 && heap[index]<heap[max_child]) {
				swap(index,max_child);
				index=max_child;
			}
			else
				cont=false;
		}
	}
	
	public boolean is_empty(){
		return last==0;
	}
	
}
