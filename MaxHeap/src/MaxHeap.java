
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
		
		//Make this a max heap
		buildMaxHeap();
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
	
	//Build a max heap at the given index. Assumption is that both left and right sub-trees
	//are already max heaps
	private void maxHeapify(int rootIndex) {
		
		int largestIndex = 0;
		int leftChild = getLeftChild(rootIndex);
		int rightChild = getRightChild(rootIndex);
		
		//Find the largest index out of the left and right children
		if (leftChild <= this.heapSize - 1 && this.internalRepresentation[leftChild] > this.internalRepresentation[rootIndex]) {
			largestIndex = leftChild;
		} else {
			largestIndex = rootIndex;
		}
		
		if (rightChild <= this.heapSize - 1 && this.internalRepresentation[rightChild] > this.internalRepresentation[largestIndex]) {
			largestIndex = rightChild;
		} else {
			largestIndex = rootIndex;
		}
		
		//Swap with root if root is not largest. Then run MaxHeapify on the subtree rooted at the largest index
		if (largestIndex != rootIndex) {

			int tempValue = this.internalRepresentation[rootIndex];
			this.internalRepresentation[rootIndex] = this.internalRepresentation[largestIndex];
			this.internalRepresentation[largestIndex] = tempValue;
			
			maxHeapify(largestIndex);
		}
		
	}
	
	//Build a max heap from the internal representation array
	private void buildMaxHeap() {
		
		for (int index = this.heapSize / 2; index >= 0; --index) {
			maxHeapify(index);
		}
		
	}

}
