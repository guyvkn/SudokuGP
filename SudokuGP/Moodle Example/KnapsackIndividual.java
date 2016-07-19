
public class KnapsackIndividual extends SubsetSumIndividual {

	private static final double OVER_COEFFICIENT = 5;

	private final int[] weights;
	private final int   maxWeight;
	private final int   maxValue;

	public KnapsackIndividual(int[] values, int[] weights, int maxWeight) {
		super(values, Integer.MAX_VALUE);

		this.weights   = weights;
		this.maxWeight = maxWeight;

		int maxValue = 0;
		for (int value: values)
			maxValue += value;

		this.maxValue = maxValue;
	}

	@Override
	protected double evaluate() {
		int sum    = 0;
		int weight = 0;

		for (int i = 0;  i < values.length;  ++i)
			if (subset[i]) {
				sum    += values[i];
				weight += weights[i];
			}

		int diff = maxValue - sum;

		if (weight <= maxWeight)
			return diff;
		else
			return OVER_COEFFICIENT * maxValue / maxWeight * (weight - maxWeight);
	}

}
