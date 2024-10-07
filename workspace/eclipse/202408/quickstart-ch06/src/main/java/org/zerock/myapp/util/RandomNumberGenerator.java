package org.zerock.myapp.util;

import java.util.Random;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class RandomNumberGenerator {
	private static Random randomNumberGenerator;
	
	
	static {
		randomNumberGenerator = new Random();
	}	// static initializer
	
	public static int generateInt(int originInclusive, int boundExclusive) {
		return randomNumberGenerator.nextInt(originInclusive, boundExclusive);
	} // generateInt
	
	public static int[] generateIntArray(int size, int originalInclusive, int boundExclusive) {		
		randomNumberGenerator.setSeed(randomNumberGenerator.nextLong());
		return randomNumberGenerator.ints(size, originalInclusive, boundExclusive).toArray();
	} // generateIntArray
	
	public static long[] generateLongArray(int size, int originalInclusive, int boundExclusive) {
		randomNumberGenerator.setSeed(randomNumberGenerator.nextLong());
		return randomNumberGenerator.longs(size, originalInclusive, boundExclusive).toArray();
	} // generateIntArray

} // end class
