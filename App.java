import java.util.Random;


public class App 
{
    /**
     * Populates an array with random values given a particular size.
     * 
     * @param arrayLength int representing size of array.
     * @return Array of size arrayLength filled with random values.
     */
    public static int[] createRandomArray(int arrayLength)
    {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++)
        {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    /**
     * Function that checks whether array has been sorted.
     * 
     * @param array Array to verify.
     * @return boolean representing sorted-ness of array.
     */
    public static boolean isSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Uses bubble sort method to sort an array.
     * 
     * @param array Array to sort.
     */
    public static void bubbleSort(int[] array)
    {
        for (int i = array.length; i > 1; i--)  // i > 1 ideal.
        {
            for (int j = 0; j < i - 1; j++)
            {
                if (array[j] > array[j + 1])
                {    
                    // Same as swap function.
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Alternative method of mergeArray that takes two arrays
     * and merges them together after sorting.
     * 
     * @param a "Left" array to sort and merge.
     * @param b "Right" array to sort and merge.
     * @return
     */
    public static int[] mergeArray(int[] a, int[] b)
    {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        
        while(i < a.length && j < b.length)
        {
            if(a[i] <= b[j])
            {
                c[k] = a[i];
                k++;
                i++;
            }
            else
            {
                c[k] = b[j];
                k++;
                j++;
            }
        }

        while (i < a.length)
        {
            c[k] = a[i];
            k++;
            i++;
        }

        while (i < b.length)
        {
            c[k] = b[j];
            k++;
            j++;
        }

        return c;
    }

    /**
     * Main method of mergeArray that uses one array as a parameter and splits it
     * accordingly, then sorts the subarrays and merges them together.
     * 
     * @param a Array to sort.
     * @param start Starting index of array.
     * @param middle Middle index of array.
     * @param end Ending index of array.
     */
    public static void mergeArray(int [] a, int start, int middle, int end)
    {
        int[] c = new int[end - start];
        int i = start, j = middle, k = 0;

        while (i < middle && j < end)
        {
            if (a[i] <= a[j])
            {
                c[k] = a[i];
                k++;
                i++;
            }
            else
            {
                c[k] = a[j];
                k++;
                j++;
            }
        }

        while (i < middle)
        {
            c[k] = a[i];
            k++;
            i++;
        }

        while (j < end)
        {
            c[k] = a[j];
            k++;
            j++;
        }

        for (i = start; i < end; i++)
        {
            a[i] = c[i - start];
        }
    }

    /**
     * Uses merge sort method to sort an array.
     * 
     * @param a Array to sort.
     * @param start Starting index of array. 
     * @param end Ending index of array.
     */
    public static void mergeSort(int[] a, int start, int end)
    {
        if (end - start <= 1)
        {
            return;
        }

        int middle = (start + end) / 2;
        mergeSort(a, start, middle);
        mergeSort(a, middle, end);

        mergeArray(a, start, middle, end);
    }

    /**
     * Shortened version of mergeSort that only needs one parameter.
     * 
     * @param a Array to sort using merge sort.
     */
    public static void mergeSort(int[] a)
    {
        mergeSort(a, 0, a.length);
    }

    public static void main(String[] args) throws Exception 
    {
        int arrayLength = 100_000;  // Has to be large so we can compare merge sort's time.
        System.out.println("Size of array: " + arrayLength + ".");

        // Make random arrays for each sort.
        int[] array = createRandomArray(arrayLength);
        int[] array2 = createRandomArray(arrayLength);

        // Set initial start time and create a variable that will store the runtime of each method.
        long start_time = System.currentTimeMillis() / 1000;
        float time_spent;
        
        // Sort and print total time spent using bubble sort method. 
        bubbleSort(array);
        time_spent = System.currentTimeMillis() / 1000 - start_time;  // Time from start to finish.
        System.out.println("Bubble sort time: " + time_spent + " seconds.");

        // Reset start_time and sort array using merge sort. Use milliseconds this time.
        start_time = System.currentTimeMillis();
        mergeSort(array2);
        
        // Print total time spent using merge sort method.
        time_spent = System.currentTimeMillis() - start_time;
        System.out.println("Merge sort time: " + time_spent + " milliseconds.");
    }
}
