
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Sorter {


    static int fixedNumbers[];
    static int numbers[][]= new int[100][];
    public static void main(String[] args) {

        System.out.println("how large would you like to sort the array ");
        Scanner kboard = new Scanner(System.in);
        int option=0;
       do  {

           option=kboard.nextInt();


           fixedNumbers=generateArray(option);
           if(option<=5){
               System.out.println("original Array");
               System.out.println(Arrays.toString(fixedNumbers));
           }
           for(int i=0; i<100;i++){
               numbers[i]=fixedNumbers.clone();
           }
           long start = System.nanoTime();


           for (int i = 0; i < 100; i++) {
               insertionSort(numbers[i]);

           }
           long end = System.nanoTime();
           long elapsed = end - start;
           System.out.println("elapsed time insertion sort=" + elapsed);
           System.out.println("time per insertion sort= " + elapsed / 100 + " nanoseconds");
           if(option<=5){
               System.out.println("insertion sorted array");
               System.out.println(Arrays.toString(numbers[1]));
           }

           for(int i=0; i<100;i++){
               numbers[i]=fixedNumbers.clone();
           }
           start = System.nanoTime();
           for (int i = 0; i < 100; i++) {
               mergeSort(numbers[i].length, numbers[i]);

           }
           end = System.nanoTime();
           elapsed = end - start;
           System.out.println("elapsed time merge sort=" + elapsed);
           System.out.println("time per merge sort= " + elapsed / 100 + " nanoseconds");
           if(option<=5){
               System.out.println("merge sorted array");
               System.out.println(Arrays.toString(numbers[1]));
           }


           for(int i=0; i<100;i++){
               numbers[i]=fixedNumbers.clone();
           }
           start = System.nanoTime();
           for (int i = 0; i < 100; i++) {
               quickSort1(numbers[i], 0, numbers[i].length - 1);

           }
           end = System.nanoTime();
           elapsed = end - start;
           System.out.println("elapsed time quick sort1=" + elapsed);
           System.out.println("time per quick sort1= " + elapsed / 100 + " nanoseconds");
           if(option<=5){
               System.out.println("quick sort1 sorted array");
               System.out.println(Arrays.toString(numbers[1]));
           }

           for(int i=0; i<100;i++){
               numbers[i]=fixedNumbers.clone();
           }
           start = System.nanoTime();
           for (int i = 0; i < 100; i++) {
               quickSort2(numbers[i], 0, numbers[i].length - 1);

           }
           end = System.nanoTime();
           elapsed = end - start;
           System.out.println("elapsed time quick sort2=" + elapsed);
           System.out.println("time per quick sort2= " + elapsed / 100 + " nanoseconds");

           if(option<=5){
               System.out.println("quick sort 2sorted array");
               System.out.println(Arrays.toString(numbers[1]));
           }

           for(int i=0; i<100;i++){
               numbers[i]=fixedNumbers.clone();
           }
           start = System.nanoTime();
           for (int i = 0; i < 100; i++) {

               quickSort3(numbers[i], 0, numbers[i].length - 1);

           }
           end = System.nanoTime();
           elapsed = end - start;
           System.out.println("elapsed time quick sort3=" + elapsed);
           System.out.println("time per quick sort3= " + elapsed / 100 + " nanoseconds");

           if(option<=5){
               System.out.println("quick sort3 sorted array");
               System.out.println(Arrays.toString(numbers[1]));
           }
       }while (option!=0);
    }

    public static int[] insertionSort(int arr[]) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = current;
        }

        return arr;
    }

    public static int[] mergeSort(int n, int arr[]) {
        if (n > 1) {
            int u[] = Arrays.copyOfRange(arr, 0, n / 2);
            int v[] = Arrays.copyOfRange(arr, n / 2, n);
            mergeSort(u.length, u);
            mergeSort(v.length, v);
            return merge(u.length, v.length, u, v, arr);
        }

        return arr;

    }

    private static int[] merge(int ulen, int vlen, int uArr[], int vArr[], int s[]) {
        int i = 0, j = 0, k = 0;
        while (i < ulen && j < vlen) {

            if (uArr[i] < vArr[j]) {
                s[k] = uArr[i];
                i++;
            } else {
                s[k] = vArr[j];
                j++;

            }
            k++;
        }
        if (i >= ulen) {
            int count = 0;
            int rest[] = Arrays.copyOfRange(vArr, j, vlen);
            while (count < rest.length) {
                s[k] = rest[count];
                k++;
                count++;
            }
        } else {
            int count = 0;

            int rest[] = Arrays.copyOfRange(uArr, i, ulen);
            while (count < rest.length) {
                s[k] = rest[count];
                k++;
                count++;
            }
        }

        return s;

    }

    public static int[] quickSort1(int arr[], int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort1(arr, low, pivot - 1);
            quickSort1(arr, pivot + 1, high);
        }
        return arr;
    }

    public static int partition(int arr[], int low, int high) {
        int i = low + 1;
        int pivot = arr[low]; //make the first element the pivot point
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] < pivot) {//if item is less than pivot
                int temp = arr[i];//swap item
                arr[i] = arr[j];
                arr[j] = temp;
                i += 1;

            }
        }
        int temp = arr[low];//swap pivot and last item
        arr[low] = arr[i - 1];
        arr[i - 1] = temp;

        return i - 1;
    }

    public static int[] quickSort2(int arr[], int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            if (pivot - 1 - low > 16)//if the partition makes a sub array less than 16 in in length then we choose to insertion sort it
                quickSort2(arr, low, pivot - 1);
            else {
                int[] temp = insertionSort(Arrays.copyOfRange(arr, low, pivot ));
                for (int i = 0; i < temp.length; i++) {
                    arr[low] = temp[i];
                    low++;
                }
            }
            if (high - pivot + 1 > 16)//if the partition makes a sub array less than 16 in in length then we choose to insertion sort it
                quickSort2(arr, pivot + 1, high);
            else {
                int[] temp = insertionSort(Arrays.copyOfRange(arr, pivot+1 , high+1));
                for (int i = 0; i < temp.length; i++) {
                    arr[pivot + 1] = temp[i];
                    pivot++;
                }
            }
        }
        return arr;
    }
    public static int[] quickSort3(int arr[], int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            if (pivot - 1 - low <16) {//if subarray is less than 16 then no problems
                quickSort3(arr, low, pivot - 1);
            }
            else{
                int range= pivot-1-low;
                Random rand= new Random();
                int n = rand.nextInt(range);
                n+=low;//select the index that will swap;
                int temp= arr[n];
                arr[n]=arr[low];
                arr[low]=temp;
                quickSort3(arr, low, pivot - 1);


            }
            if (high - pivot + 1 <16) {//if subarray is less than 16 then no problems
                quickSort3(arr, pivot + 1, high);
            }
            else{
                int range=high-pivot+1;
                Random rand= new Random();
                int n = rand.nextInt(range);
                n+=pivot;//select the index that will swap;
                int temp= arr[n];
                arr[n]=arr[pivot+1];
                arr[pivot+1]=temp;
                quickSort3(arr, pivot + 1, high);

            }
        }
        return arr;
    }
    public static int[] generateArray(int size){
        int tempSize=(int)Math.pow(2,size);
        return ThreadLocalRandom.current().ints().limit(tempSize).toArray();
    }

}
