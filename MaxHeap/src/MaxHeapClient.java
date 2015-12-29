import java.util.Random;

public class MaxHeapClient {

	public static final int HEAP_SIZE = 10;
	public static void main(String[] args) {
		
		MaxHeapClient maxHeapClient = new MaxHeapClient();
		maxHeapClient.testMaxHeap();

	}
	
	private void testMaxHeap() {
		
		int[] unsortedArray = new int[HEAP_SIZE];
		Random randomNumberGenerator = new Random();
		for (int index = 0; index < HEAP_SIZE; ++index) {
			unsortedArray[index] = randomNumberGenerator.nextInt();
		}
		
		MaxHeap maxHeap = new MaxHeap(unsortedArray);
		System.out.println(maxHeap.toString());
		
		maxHeap.heapSort();
		System.out.println(maxHeap.toString());
		
	}

}
