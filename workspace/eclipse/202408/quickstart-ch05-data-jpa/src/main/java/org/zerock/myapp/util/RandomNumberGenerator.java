package org.zerock.myapp.util;

import java.util.Random;


public class RandomNumberGenerator {
	private static Random randomNumberGenerator;
	
	
	static {
		randomNumberGenerator = new Random();
	}	// static initializer
	
	
	public static int generateInt(int originInclusive, int boundExclusive) {										// Half-open
		return randomNumberGenerator.nextInt(originInclusive, boundExclusive);
	} // generateInt
	
	public static int[] generateIntArray(int size, int originalInclusive, int boundExclusive) {			// Half-open
		randomNumberGenerator.setSeed(randomNumberGenerator.nextInt());
		return randomNumberGenerator.ints(size, originalInclusive, boundExclusive).toArray();
	} // generateIntArray
	
	
	public static long[] generateLongArray(int size, int originalInclusive, int boundExclusive) {		// Half-open
		randomNumberGenerator.setSeed(randomNumberGenerator.nextInt());
		return randomNumberGenerator.longs(size, originalInclusive, boundExclusive).toArray();
	} // generateIntArray

} // end class
