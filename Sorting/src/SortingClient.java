import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class SortingClient {

	public static final int NUMBER_OF_ITERATIONS = 17;
	public static final int STEP_SIZE = 2;
	public static final String OUTPUT_FILE = "output.txt";
	
	private PrintWriter out;
	private int[] collectionSizes;
	private long[] insertionSortRunTimes;
	private long[] heapSortRunTimes;
	
	public static void main(String[] args) {
		
		SortingClient sortingClient = new SortingClient();
		sortingClient.collectRunTimeStatistics();

	}
	
	private void collectRunTimeStatistics() {
		
		try {
			this.out = new PrintWriter(new FileWriter(OUTPUT_FILE));
		} catch (IOException e) {
			System.err.println("IOException while opening file ");
			e.printStackTrace();
			System.exit(0);
		}
		
		collectionSizes = new int[NUMBER_OF_ITERATIONS];
		insertionSortRunTimes = new long[NUMBER_OF_ITERATIONS];
		heapSortRunTimes = new long[NUMBER_OF_ITERATIONS];
		
		int collectionSize = 1;
		for (int iterationCounter = 0; iterationCounter < NUMBER_OF_ITERATIONS; ++iterationCounter) {
			
			collectionSize *= STEP_SIZE; 
			collectionSizes[iterationCounter] = collectionSize;
			testSorting(collectionSize, iterationCounter);
			
		}
		
		printRunTimeStatistics();
	}
	
	/**
	 * Print run time statistics in R script format for ease of graphing
	 */
	private void printRunTimeStatistics() {
		
		boolean firstTime = true;
		this.out.print("\nCollectionSizes=c(");
		for (int size : this.collectionSizes) {
			if (firstTime) {
				this.out.print(size);
				firstTime = false;
			} else {
				this.out.print(", ");
				this.out.print(size);
			}
		}
		this.out.print(")");
		
		firstTime = true;
		this.out.print("\nInsertionSortRunTimes=c(");
		for (long runTime : this.insertionSortRunTimes) {
			if (firstTime) {
				this.out.print(runTime);
				firstTime = false;
			} else {
				this.out.print(", ");
				this.out.print(runTime);
			}
		}
		this.out.print(")");
		
		firstTime = true;
		this.out.print("\nHeapSortRunTimes=c(");
		for (double runTime : this.heapSortRunTimes) {
			if (firstTime) {
				this.out.print(runTime);
				firstTime = false;
			} else {
				this.out.print(", ");
				this.out.print(runTime);
			}
		}
		this.out.print(")");
		this.out.print(getRPlottingScript());
		this.out.close();

	}

	private String getRPlottingScript() {
		
		return "\nlog.size = log2(CollectionSizes)" + 
			   "\nlog.Insertion = log2(InsertionSortRunTimes)\nlog.Heap = log2(HeapSortRunTimes)" +
			   "\nplot(log.size,log.Insertion,type='l', xlab='Log base 2 of number of elements to sort', ylab = 'Log base 2 run time in ms', col='blue', main='Run time for Sorting')" +
			   "\nlines(log.size,log.Heap, col='red')" +
			   "\nlegend(x=1, y=11,legend=c('Insertion Sort','Heap Sort'), col=c('blue', 'red'), lwd=3, lty=1)";
		
	}
	
	
	private void testSorting(int collectionSize, int iterationCounter) {
		
		int[] unsortedArray = new int[collectionSize];
		Random randomNumberGenerator = new Random();
		for (int index = 0; index < collectionSize; ++index) {
			unsortedArray[index] = randomNumberGenerator.nextInt();
		}
		
		MaxHeap maxHeap = new MaxHeap(unsortedArray);
		System.out.println(maxHeap.toString());
		
		long beforeSorting = System.currentTimeMillis();
		maxHeap.heapSort();
		long afterSorting = System.currentTimeMillis();
		heapSortRunTimes[iterationCounter] = afterSorting - beforeSorting == 0 ? 1 : afterSorting - beforeSorting;
		
		System.out.println(maxHeap.toString());
		
		ElementsToSort elementsToSort = new ElementsToSort(unsortedArray);
		System.out.println(elementsToSort.toString());
		beforeSorting = System.currentTimeMillis();
		elementsToSort.insertionSort();
		afterSorting = System.currentTimeMillis();
		insertionSortRunTimes[iterationCounter] = afterSorting - beforeSorting == 0 ? 1 : afterSorting - beforeSorting;
		System.out.println(elementsToSort.toString());
		
	}

}
