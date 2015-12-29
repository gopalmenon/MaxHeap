import java.text.DecimalFormat;

public class MaxHeap {
	
	private int[] internalRepresentation;
	private int heapSize;
	private boolean validMaxheap;
	
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
		
		this.validMaxheap = true;
		
	}

	private int getLeftChild(int index) {
		return index * 2 + 1;
	}

	private int getRightChild(int index) {
		return index * 2 + 2;
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
		
		for (int index = this.heapSize / 2 - 1; index >= 0; --index) {
			maxHeapify(index);
		}
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer returnValue = new StringBuffer();

		if (!this.validMaxheap) {
			returnValue.append("Warning!!! This is not a valid Max Heap\n");
		}
		
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(0);
		decimalFormat.setGroupingSize(3);
		returnValue.append("[");
		boolean firstTime = true;
		for (int index = 0; index < this.heapSize; ++index) {
			if (firstTime) {
				firstTime = false;
			} else {
				returnValue.append("; ");
			}
			returnValue.append(decimalFormat.format(this.internalRepresentation[index]));
		}
		returnValue.append("]");
		return returnValue.toString();
	}
	
	public void heapSort() {
		
		this.validMaxheap = false;
		int temp = 0;
		int savedHeapSize = this.heapSize;
		for (int index = this.heapSize - 1; index > 0; --index) {
		
			temp = this.internalRepresentation[0];
			this.internalRepresentation[0] = this.internalRepresentation[this.heapSize - 1];
			this.internalRepresentation[this.heapSize - 1] = temp;
			--this.heapSize;
			maxHeapify(0);
			
		}
		
		this.heapSize = savedHeapSize;
	}

}
