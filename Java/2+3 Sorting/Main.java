import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final int[] unsortedArray = {2, 4, 8, 5, 2, 6, 9, 65, 78, 4, 5, 456, 498, 8, 25, 68, 7, 3, 84, 89, 498, 2, 1, 4, 6, 87435, 32, 54, 6, 2, 435, 8765, 32, 43, 76, 43}; //random numbers brought to you by forehead
        int[] array;

        array = Arrays.copyOf(unsortedArray, unsortedArray.length); //creating a copy so the original data stays unsorted
        System.out.println("Before InsertionSort");
        System.out.println(Arrays.toString(array));
        insertionSort(array);
        System.out.println("After InsertionSort");
        System.out.println(Arrays.toString(array) + "\n");

        array = Arrays.copyOf(unsortedArray, unsortedArray.length);
        System.out.println("Before MergeSort");
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println("After MergeSort");
        System.out.println(Arrays.toString(array) + "\n");

    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                int j = i;
                while (j > 0 && array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                    j--;
                }
            }
        }
    }

    public static void mergeSort(int[] array) {
        mergeRecursion(array, 0, array.length - 1);
    }

    private static void mergeRecursion(int[] array, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            //Part 1, find middle index and call function on both parts, splitting the array into ever smaller parts
            int newIndex = (startIndex + endIndex) / 2;
            mergeRecursion(array, startIndex, newIndex);
            mergeRecursion(array, newIndex + 1, endIndex);


            //Part 2, merging the parts back together into larger arrays.
            //copy to 2 new arrays, then merge those back into the original. there are better solutions
            int[] leftPart = Arrays.copyOfRange(array, startIndex, newIndex + 1);
            int[] rightPart = Arrays.copyOfRange(array, newIndex + 1, endIndex + 1);
            int leftIndex = 0;
            int rightIndex = 0;

            for (int i = startIndex; i <= endIndex; i++) {
                if (rightIndex == rightPart.length || leftIndex != leftPart.length && leftPart[leftIndex] < rightPart[rightIndex]) {
                    array[i] = leftPart[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightPart[rightIndex];
                    rightIndex++;
                }
            }

        }
    }

    private static void swap(int[] array, int a, int b) {
        int swap = array[a];
        array[a] = array[b];
        array[b] = swap;
    }

}