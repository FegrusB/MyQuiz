package primeNumbers;

public class Number {
	
	//Number class, to store an int and isPrime boolean.
	
	//Value of number to store (int). If that number has been set to prime  
	private int value;
	private Boolean isPrime;

	// A constructor method. 
	public Number(int numIn, Boolean boolIn) {
		
		value = numIn;
		isPrime = boolIn;
	
	}

	//Getters and setters, no setter for value, set on instantiation and not changed after.
	public int getValue() {return value;}
	public Boolean getPrime() {return isPrime;}
	public void setPrime(Boolean in) {isPrime = in;}
	
	//Simple method to print the value of both variable to the console.
	public void print() {
		
		System.out.print(value + " ");
		System.out.println(isPrime);
		
	}
	
}
