import java.util.*;

public class ThreeBrothersProblem {

	/**
	 * The brothersInTheBar method returns maximum number of rounds three brothers
	 * can drink.
	 **/
	public int brothersInTheBar(int[] glasses) {

		int rounds = 0; // number of rounds
		int i = 0; // main counter
		int length = glasses.length; // length of initial array
		int[] removed = new int[length]; 

		if (length < 1 || length > 105) {
			throw new RuntimeException("Length of an array needs to be between 1 and 105.");
		}

		if (length < 3) {
			// brothers can't drink if there are not enough glasses
			return 0;
		}

		for (int r = 0; r < length; r++) {
			removed[r] = 0;
		}

		while (i < length - 2) {
			// if three same sizes sequence is found
			if (glasses[i] == glasses[i + 1] && glasses[i + 1] == glasses[i + 2]) {
				rounds++; // increment number of rounds
				removed[i] = removed[i + 1] = removed[i + 2] = 1;
				
        // initialize help counters
				// move left counter to the last "non-removed" position
				int j = i - 1;
				while ((j>-1) && removed[j] == 1){
					j--;
				}
        // move right counter right of the sequence
				int k = i + 3;

				// try to find three same size glasses left and right from just "removed"
				// matching sizes sequence
				while ((j > -1) && (k < length)) {
					
					// do two left and one right match?
					if (j > 0 && glasses[j - 1] == glasses[j] && glasses[j] == glasses[k]) {
						rounds++; // increment number of rounds
						removed[j - 1] = removed[j] = removed[k] = 1;
						j -= 2; // move two places left
						k++; // move one place right
						// do one left and two right match?
					} else if ((k < length - 1) && glasses[j] == glasses[k] && glasses[k] == glasses[k + 1]) {
						rounds++; // increment number of rounds
						removed[j] = removed[k] = removed[k + 1] = 1;
						j--; // move one place left
						k += 2; // move two places right
					} else
						break;
				}
				i = k; // position main counter to the most right place

			} else
				i++; // no matching sequence found - go further through the array of sizes
		}

		return rounds;
	}

	public static void main(String[] args) {

		ThreeBrothersProblem threeBrothersProblem = new ThreeBrothersProblem();

		int[] glasses1 = { 1, 1, 1, 12, 13, 14, 15, 16 };

		int rounds1 = threeBrothersProblem.brothersInTheBar(glasses1);

		System.out.println(
				"For input array: " + Arrays.toString(glasses1) + " brothers can drink: " + rounds1 + " rounds.");

	}
}
