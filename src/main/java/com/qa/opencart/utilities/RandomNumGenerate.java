package com.qa.opencart.utilities;

import java.util.Random;

public class RandomNumGenerate {
	
	public static String generateRandomNumber() {		
		Random rand = new Random();        
        return String.valueOf(rand.nextInt(1000)); // Generate random numbers in range 0 to 999 
	}
}
