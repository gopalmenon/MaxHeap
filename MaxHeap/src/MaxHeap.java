
public class MaxHeap {
	
	private int[] internalRepresentation;
	private int heapSize;
	
	//Constructor - build a maxheap from an input array
	MaxHeap(int[] inputArray) {
		
		//Allocate array for internal representation and set size of max heap
		this.heapSize = inputArray.length;
		this.internalRepresentation = new int[this.heapSize];
		
		//Initialize internal representation
		for (int arrayIndex = 0; arrayIndex < this.heapSize; ++arrayIndex) {			
			this.internalRepresentation[arrayIndex] = inputArray[arrayIndex];
			
		}
		
	}
	
	private int getParent(int index) {
		return index / 2;
	}

	private int getLeftChild(int index) {
		return index * 2;
	}

	private int getRightChild(int index) {
		return index * 2 + 1;
	}
	
	private void MaxHeapify(int rootIndex) {
		
	}

}
