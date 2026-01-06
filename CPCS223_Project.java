package cpcs223_project;
//Sheemah Alshalwi, ID: 2306353
//Deema Alfadhli, ID: 2307731
//Shahad Alshehri, ID: 2306119
//Raghad Alamoudi, ID: 2308393
//CAR

import java.util.*;

public class CPCS223_Project {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //take the size from the user
        System.out.print("Enter the size of the array: ");
        int n = scan.nextInt();
        int A[] = new int[n];
        //fill the array with random values
        for (int i = 0; i < n; i++) {
            A[i] = (int) (Math.random() * 999999) + 1;
        }
        //printing the array
        System.out.println(" * The array before sorting * ");
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("]");
        //sort the array using quick sort
        quickSort(A, 0, n - 1);
        //print array after sorting
        System.out.println(" * The array after sorting * ");
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("]");
        System.out.println();
        //take a key from the user
        System.out.print("\nEnter the key you want to search for: ");
        int key = scan.nextInt();
//linear
        // Call the Linear Search method and calculate the time consumed
        long linearStart = System.nanoTime(); // Start time
        int indexLinear = linearSearch(A, key); // Call the search function
        long linearEnd = System.nanoTime(); // End time
        // Print the search result
        if (indexLinear >= 0) {
            System.out.println("The element is found at index: " + indexLinear);
        } else {
            System.out.println("The element is not found in the array");
        }
        // Calculate and print the total time consumed
        System.out.println("The total time consumed during Linear Search is: " + (linearEnd - linearStart) + " nanoseconds");
        System.out.println();
//
//Binary
        //call the Binary search method and calculate the time consomed\
        long BinaryStart = System.nanoTime(); // Start time
        int indexBinary = BinarySearch(A, key); // Call the search function
        long BinaryEnd = System.nanoTime(); // End time
        // Print the search result
        if (indexBinary >= 0) {
            System.out.println("The element is found at index: " + indexBinary);
        } else {
            System.out.println("The element is not found in the array");
        }
        // Calculate and print the total time consumed
        System.out.println("The total time consomed during Binary search is: " + (BinaryEnd - BinaryStart) + " nanoseconds");
        System.out.println();
//
//interpolation
        //call the interpolation search method and calculate the time consomed\
        long interpolationStart = System.nanoTime(); // Start time
        int indexInterpolation = interpolationSearch(A, key); // Call the search function
        long interpolationEnd = System.nanoTime(); // End time
        // Print the search result
        if (indexInterpolation >= 0) {
            System.out.println("The element is found at index: " + indexInterpolation);
        } else {
            System.out.println("The element is not found in the array");
        }
        // Calculate and print the total time consumed
        System.out.println("The total time consomed during interpolation search is: " + (interpolationEnd - interpolationStart) + " nanoseconds");
        System.out.println();
//
//jump
        // Call the Jump Search method and calculate the time consumed
        long jumpStart = System.nanoTime(); // Start time
        int indexJump = jumpSearch(A, key); // Call the search function
        long jumpEnd = System.nanoTime(); // End time
        // Print the search result
        if (indexJump >= 0) {
            System.out.println("The element is found at index: " + indexJump);
        } else {
            System.out.println("The element is not found in the array");
        }
        // Calculate and print the total time consumed
        System.out.println("The total time consumed during Jump Search is: " + (jumpEnd - jumpStart) + " nanoseconds");
        System.out.println();

    }

    // Linear Search
    public static int linearSearch(int[] arr, int key) {
        // Iterate through each element in the array
        for (int i = 0; i < arr.length; i++) {
            // If the current element matches the key, return its index
            if (arr[i] == key) {
                return i;
            }
        }
        return -1; // If the key is not found in the array, return -1
    }

    // Binary Search
    public static int BinarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (array[mid] == key) {
                return mid; // found the key 
            } else if (array[mid] < key) {
                low = mid + 1; // search in upper half
            } else if (array[mid] > key) {
                high = mid - 1; // search in lower half
            }
        }
        return -1; // key not found ( unsuccessful search )
    }

    // Interpolation Search
    public static int interpolationSearch(int[] A, int key) {
        int low = 0;
        int high = (A.length) - 1;
        int position;
        while (low <= high && A[low] <= key && A[high] >= key) {
            // check if the low = high
            if (A[high] == A[low]) {
                if (A[low] == key) {
                    return low;
                } else {
                    return -1;
                }
            }
            
            // compute the position
            position = low + ((key - A[low]) * (high - low)) / (A[high] - A[low]);
            if(position<low || position>high){
                return -1;
            }
            // change the range and repeat loop
            if (A[position] == key) {
                return position;
            } else if (A[position] < key) {
                low = position + 1;
            } else {
                high = position - 1;
            }
        }
        return -1;
    }

    //    // Jump Search
    public static int jumpSearch(int[] A, int key) {
        int n = A.length;
        // Calculate block size to be jumped
        int step = (int) Math.sqrt(n);
        int m = step;
        // Find the block where the element is present
        int prev = 0;
        while (m < n && A[m - 1] < key) {
            prev = m;
            m += step;
        }
        // Perform linear search within the block
        for (int i = prev; i < m && i < n; i++) {
            if (A[i] == key) {
                return i; //position of element being searched
            }
        }
        return -1;  // Element not found
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pi = partition(arr, low, high);

            // Recursively sort the two sub-arrays
            quickSort(arr, low, pi - 1);  // Left side of pivot
            quickSort(arr, pi + 1, high); // Right side of pivot
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Choose the pivot (taking the last element as pivot)
        int pivot = arr[high];

        // Pointer for the smaller element
        int i = low - 1;

        // Traverse through all elements and rearrange based on pivot
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                // Swap elements that are smaller than the pivot
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element with the element at i+1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the partition index (pivot index)
        return i + 1;
    }

}
