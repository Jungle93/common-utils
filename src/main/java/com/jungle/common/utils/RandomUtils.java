package com.jungle.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author darwin
 */
public final class RandomUtils {

	private static final int RANDOM_RANGE = 1000000000;

	public static <T> T randomItem ( Item<T>[] items ) {

		if (items == null || items.length == 0) {
			throw new IllegalArgumentException( "item list should not be empty." );
		}
		double[] probabilities = new double[items.length];
		for (int i = 0; i < items.length; i++) {
			probabilities[i] = items[i].getProbability();
		}
		final int index = randomIndex( probabilities );
		if (index > -1) {
			return items[index].getData();
		}
		return null;
	}

	public static int randomIndex ( double[] probabilities ) {

		double totalProbability = 0;
		for (int i = 0; i < probabilities.length; i++) {
			totalProbability = NumberUtils.add( probabilities[i], totalProbability );
		}
		if (totalProbability > 1) {
			/*probabilities table is empty.illegal parameters.*/
			throw new IllegalArgumentException( "total probabilities is grate than 1." );
		}

		int maxRange = (int) Math.floor( NumberUtils.mul( totalProbability, RANDOM_RANGE ) );
		final SecureRandom secureRandom;
		try {
			secureRandom = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException( "secure random algorithm not found." );
		}
		final int randomNumber = secureRandom.nextInt( RANDOM_RANGE );
		if (randomNumber < maxRange) {
			int range = 0;
			for (int i = 0; i < probabilities.length; i++) {
				range += NumberUtils.mul( probabilities[i], RANDOM_RANGE );
				if (randomNumber < range) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int randomIndexProInPercent ( double[] probabilityInPercent ) {

		if (probabilityInPercent == null || probabilityInPercent.length == 0) {
			/*probability table is empty.illegal parameters.*/
			throw new IllegalArgumentException( "probability is null or empty." );
		}
		double[] probabilities = new double[probabilityInPercent.length];
		for (int i = 0; i < probabilityInPercent.length; i++) {
			probabilities[i] = NumberUtils.div( probabilityInPercent[i], 100 );
		}
		return randomIndex( probabilities );
	}

	public static class Item<T> {

		private final double probability;

		private final T data;

		public Item ( final double probability, final T data ) {

			this.probability = probability;
			this.data = data;
		}

		public double getProbability () {

			return probability;
		}

		public T getData () {

			return data;
		}

	}

}
