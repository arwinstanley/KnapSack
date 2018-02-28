/**
 * 
 * This is for the Knapsack assignment
 * 
 * @author WinstanleyA
 * @Date 2/27/18
 */
import java.io.*;
import java.util.*;

public class Knapsack {

	public static ArrayList<String> globList = new ArrayList<String>();
	/**
	 * 
	 * goes through the array recursively and finds the total without exceeding the limit
	 * 
	 * @param w is the list of weights
	 * @param index of current item
	 * @param limit is the limit of weight
	 * @return maximum without exceeding limit
	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		
		if(limit < 1 || n < 1) {
			return 0;
		}
		
		int x = knapsackSum(w, n - 1, limit - w[n-1])  +  w[n-1];
		
		int y = knapsackSum(w, n - 1, limit)  +  w[n-1];
		
		if(w[n-1] > limit) {
			return knapsackSum(w, n - 1, limit);
		}else {
		
			if(x >= y) {
				if(x <=limit) {
					return y;
				} 
			
			}else {
				if(y <=limit) {
				return y;
				}
			}
		}
		return 0;
	}
	/**
	 * 
	 * goes through the array recursively and finds the total without exceeding the limit and keeps track of the items as it goes
	 * 
	 * @param w is the list of weights
	 * @param index of current item
	 * @param limit is the limit of weight
	 * @param list is the list of items
	 * @return total without exceeding limit
	 */
	 public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		if(limit < 1 || n < 1) {
			return 0;
		}
		if(w[n-1] > limit){
			return knapsackSum(w, n - 1, limit, list);
		}
		return 0;
	}
		/**
		 * 
		 *finds the number of items to add
		 * 
		 * @param list list of items
		 * @return number of items to add
		 */
		public static int numberToAdd(Scanner list) {
			int i = -1;
			while (list.hasNextLine()) {
			    i++;
			    if(list.hasNextLine()) {   
			    	list.nextLine();
			    }
			}
			return i;
		}

		/**
		 * 
		 *creates a PrintWriter to make an output
		 * 
		 * @param fname the file name
		 * @return a PrintWriter for output
		 */
	public static PrintWriter writer(String fName) {
			File fileName = new File(fName);
			PrintWriter output = null;
			try {
				output = new PrintWriter(fileName);
			} catch (FileNotFoundException ex) {
				System.out.print("Cannot open " + fName);
				return null;
			}
			return output;
	}
	/**
	 * 
	 *creates a Scanner to make an input
	 * 
	 * @param fname the file name
	 * @return a Scanner for input
	 */
	public static Scanner reader(String fname) {
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} 
		catch (FileNotFoundException ex) {
			System.out.print("Cannot open " + fname);
			return null;
		}
		return input;	
	}

	/**
	 * 
	 *does all the work I did'nt want to do in main 
	 * 
	 * @param list list of itemas
	 * @param x index of the items
	 * @param y num of items
	 * @return a string for me to print in my output with knapsack being called already
	 */
	public static String fileToKnap(Scanner list, int x, int y) {
		
		String linetemp = list.nextLine();
		String line = list.nextLine();
		int[] intList = new int[y];
		
		for(int i = 0; i < y; i++) {
			intList[i] = Integer.parseInt(line);
			if(list.hasNextLine()) {
				line = list.nextLine();
			}
		}
		
		String nums = linetemp + " ";
		for(int i = 0; i < intList.length; i++) {
			nums += intList[i] + ", ";
		}
		nums = nums.substring(0, nums.length() - 2);
		
		
		int sum = knapsackSum(intList, y, Integer.parseInt(linetemp));
		
	
		
		return (globList.get(x) + " " + nums + "\n\n" + sum);
		
	}
	/**
	 * 
	 *main runs all of my files to test the knapsack algorithm
	 * 
	 * @param args list of files
	 * @return none
	 */
	public static void main(String[] args) {
		PrintWriter out = writer("knapsack.txt");
		Scanner file = reader(args[0]);
		
		String temp;
		while(file.hasNextLine()){
			temp = file.nextLine();
			globList.add(temp);
		}
		
		Scanner input = new Scanner(System.in);
		int k;
		ArrayList<Integer> list;
		
		if(globList.size() < 1) {
			
			System.out.println("Enter a file name: ");
			
			globList.add(input.nextLine());
			
			file = reader(globList.get(0));
		}
		
		for(int i = 0; i < globList.size(); i++) {	
			
			if(file != null) {
				
				file = reader(globList.get(i));
				
				k = (numberToAdd(file));
				
            	file = reader(globList.get(i));
				
				list = new ArrayList<Integer>();
				
				out.println(fileToKnap(file, i, k, list) + "\n");
				
				for(int p = 0; p < list.size(); p++) 
					out.println(list.get(p));
			}
		}
		file.close();
		input.close();
		out.close();
		
	}	
	/**
	 * 
	 *does all the work I did'nt want to do in main 
	 * 
	 * @param list list of itemas
	 * @param x index of the items
	 * @param y num of items
	 * @return a string for me to print in my output with knapsack being called already
	 */
	public static String fileToKnap(Scanner file, int x, int y, List<Integer> toSum) {
		
		String linetemp = file.nextLine();
		String line = file.nextLine();
		int[] intList = new int[y];
	
		for(int i = 0; i < y; i++) {
			intList[i] = Integer.parseInt(line);
			if(file.hasNextLine()) {
				line = file.nextLine();
			}
		}
		String nums = linetemp + " ";
		for(int j = 0; j < intList.length; j++) {
			nums += intList[j] + ", ";
		}
		nums = nums.substring(0, nums.length() - 2);
		
		int total = knapsackSum(intList, y, Integer.parseInt(linetemp), toSum);
		
		return (globList.get(x) + "   " + nums + "\n" + total);
		
	}
}