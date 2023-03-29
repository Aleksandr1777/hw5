import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] unsortedArray = new int[100000];
        Random random = new Random();
        for (int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = random.nextInt(100);
        }
       // print(unsortedArray);
        long start1 = System.currentTimeMillis();
        int[] sortedArrayBySelectionSort = selectionSort(unsortedArray);
        long end1 = System.currentTimeMillis();
        print(sortedArrayBySelectionSort);
        long absoluteTime1 = end1 - start1;
        
        long start2 = System.currentTimeMillis();
        int[] sortedArrayByBubbleSort = bubbleSort(unsortedArray);
        long end2 = System.currentTimeMillis();
        long absoluteTime2 = end2 - start2;
        print(sortedArrayByBubbleSort);

        System.out.println(absoluteTime1);
        System.out.println(absoluteTime2);



    }

    public static int[] selectionSort(int[] array){
        int[] arrayCopy = Arrays.copyOf(array,array.length);

        for (int i = 0; i < arrayCopy.length; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int j = i; j < arrayCopy.length; j++) {
             if (arrayCopy[j]<min) {
                 min = arrayCopy[j];
                 index = j;
             }
            }
            if (index>-1) {
                int z = arrayCopy[i];
                arrayCopy[i] = arrayCopy[index];
                arrayCopy[index] = z;
            }
        }
        return arrayCopy;
    }

    public static int[] bubbleSort(int[] array){
        int[] arrayCopy = Arrays.copyOf(array,array.length);
        for (int j = 0; j<arrayCopy.length ; j++) {
            for (int i = arrayCopy.length-1; i > j; i--) {
                if (arrayCopy[i] < arrayCopy[i - 1]) {
                    int z = arrayCopy[i];
                    arrayCopy[i] = arrayCopy[i - 1];
                    arrayCopy[i - 1] = z;
                }
            }
        }
        return arrayCopy;
    }
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println("");
    }


}