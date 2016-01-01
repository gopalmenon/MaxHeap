import java.text.DecimalFormat;


public class ElementsToSort {
	
	private int[] internalRepresentation;
	
	//Constructor
	ElementsToSort(int[] inputArray) {
		
		//Allocate array for internal representation
		this.internalRepresentation = new int[inputArray.length];
		
		//Initialize internal representation
		for (int arrayIndex = 0; arrayIndex < inputArray.length; ++arrayIndex) {			
			this.internalRepresentation[arrayIndex] = inputArray[arrayIndex];
			
		}
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer returnValue = new StringBuffer();
		
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(0);
		decimalFormat.setGroupingSize(3);
		returnValue.append("[");
		boolean firstTime = true;
		for (int index = 0; index < this.internalRepresentation.length; ++index) {
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
	public void insertionSort() {
		
		int currentKey = 0, subIndex = 0;
		for (int currentIndex = 1; currentIndex < this.internalRepresentation.length; ++currentIndex) {
			
			currentKey = this.internalRepresentation[currentIndex];
			subIndex = currentIndex - 1;
			
			while (subIndex >= 0 && this.internalRepresentation[subIndex] > currentKey) {
				
				this.internalRepresentation[subIndex + 1] = this.internalRepresentation[subIndex];
				--subIndex;
				
			}
			
			this.internalRepresentation[subIndex + 1] = currentKey;
			
		}
				
	}
	
	
}
