package primeNumbers;

import java.util.Scanner;

public class PimeNumbers {

	//short program to find prime numbers, and compare the compute time of different algorithms to do so. 
	
	public static void main(String[] args){
		
		//main, get users input parameters, run chosen algorithms  
		
		//limit, the largest number to be checked.
		//choice, input to chose option
		//numbers, array of type number, stores the int being checked, and a bool for primeness/non-primeness
		//myInput, scanner for getting user input for limit and choice
		int limit;
		int choice;
		Number[] numbers;
		Scanner myInput = new Scanner (System.in);
		
		//read the maximum value to be checked from console
		System.out.print("enter limit:");
		limit = getNum(myInput);
		
		//set length of numbers array
		numbers = new Number[limit];
		
		//give user choice of algorithms or speed test, read choice from console
		System.out.println("1. test all");
		System.out.println("2. sieve of eratosthenes");
		System.out.println("3. test times");
		System.out.print("Which algorithm would you like to use:");
		choice = getNum(myInput);
		
		//call setUp to populate numbers array, pass numbers to and run method for users choice, 
		switch (choice) {
			
			case 1:numbers = setUp(limit,true); testAll(numbers);break;
			case 2:numbers = setUp(limit,true); sieveOfE(numbers,limit);break;
			case 3:testTimes(numbers,limit);break;
			
		}
			

		myInput.close();	
		
	}
	
	
	public static Number[] setUp(int limit,Boolean initial){
		
		//method for setting up numbers array. sets each instance of number contained within the array to hold an int value (1-limit), and the T/F value it is passed.
		
		//new array of numbers, with length limit
		Number numbers[] = new Number[limit];
		
		//for each number in numbers, give an incremental int value, and the starting bool value
		for (int i = 0; i<limit; i++) {
			
			numbers[i] = new Number(i+1, initial);
			
		}

		//return the populated array
		return numbers;
		
	}
	
	
	public static int getNum(Scanner myInput){
		
		//short method for reading an int from the console
		
		int num;

		num = myInput.nextInt();

		return num;
			
	}

	
	public static long testAll(Number[] nums) {
		
		//most basic prime checking technique. check each number individually 
		
		//startTime, long for storing initial system time values, for calculating run times
		//enlapsedTime, computed time taken for method to run. 
		long startTime = System.nanoTime();
		long enlapsedTime;
		
		//for each number in nums
		for(Number entry : nums) {
			
			//num, the int value stored in the number.Value variable
			int num = entry.getValue();
			
			//check if num == 1. 1 will return true prime with main algorithm, but it is not prime
			if(num == 1) {
				
				entry.setPrime(false);
				
			//check numbers < 5, 4 will return prime with main algorithm but is not prime
			}else if(num < 5) {
				
				
				Boolean finished = false;
				while(!finished) {
					
					//untill i == num, if num mod i == 0, num set non-prime & break
					for(int i = 2; i<num+1;i++) {
					
						if(i==num) {finished = true;}
						if(finished == false & num%i==0) {entry.setPrime(false);finished = true;}
						
					}
				}
				
			//main algorithm, for every number < num/2, if num mod i == 0, num set non-prime & break
			}else {

					for(int i = 2; i<num/2;i++) {

						if(num%i==0) {entry.setPrime(false);break;}
						
					}
				
				
			}
			//print numbers[i], prints -> value isPrime 
			entry.print();
			
		}
		
		//calc run time, print a blank line, return run time
		enlapsedTime = System.nanoTime() - startTime;
		System.out.println();		
		
		return enlapsedTime;
		
	}
	
	
	public static long sieveOfE(Number[] nums,int limit) {
		
		//sieve of eratosthenes implimentation. set multiples of each number. 
		
		//startTime, long for storing initial system time values, for calculating run times
		//enlapsedTime, computed time taken for method to run. 
		long startTime = System.nanoTime();
		long enlapsedTime;
		
		//set 1 to false, 1 is not prime
		nums[0].setPrime(false);
		
		//for each number in numbers
		for(Number entry : nums) {
			
			//if entry is prime (initial state) calc multiples and set all to not prime. 
			if(entry.getPrime()) {
				
				int ans = 0;
				
				int x = entry.getValue();
				
				//set every multiple of entry < limit to non-prime
				for(int y = 2; ans<limit-1; y ++ ) {
					
					ans = x * y;
					if(ans <= limit) {nums[ans-1].setPrime(false);};
					
				}
								
			}
			
			//print numbers[i], prints -> value isPrime
			entry.print();
			
		}
		
		//calc run time, print a blank line, return run time
		enlapsedTime = System.nanoTime() - startTime;
		System.out.println();
		
		return enlapsedTime;
		
	}
	
	
	public static void testTimes(Number[] nums,int limit) {
		
		//uses both algorithms, saves returned run times, compares the two
		
		//time1, stores the time to execute for testAll
		//time2, stores the time to execute for sieveOfE
		//diff, stores computed difference between time1 and time2
		long time1;
		long time2;
		long diff;
		
		//sets up nums, sends to test all, saves returned time as time1
		nums = setUp(limit,true);
		time1 = testAll(nums);
		
		//sets up nums, sends to sieveOfE, saves returned time as time2
		nums = setUp(limit,true);
		time2= sieveOfE(nums,limit);
		
		//print times taken to console
		System.out.println("Testing all took: " + time1 + " nanoseconds");
		System.out.println("The sieve of eratosthenes took: " + time2 + " nanoseconds");

		//if time1 bigger calc difference, and % difference, print to console
		if(time1>time2) {
			
			diff = time1 - time2;
			double percent = diff / (time1/100);
			
			System.out.println("The sieve of eratosthenes was quicker by: " + diff + " nanoseconds");
			System.out.println("that is: " + percent + "%");
					
		//if time2 bigger calc difference, and % difference, print to console
		}else if(time1<time2) {
			
			diff = time2 - time1;
			double percent = diff / (time2/100);
			
			System.out.println("Testing all was quicker by: " + diff + " nanoseconds");
			System.out.println("that is: " + percent + "%");
					
		}
		

		
	}
	
}
