
public class SubsetSumIndividual extends Individual {

	protected final int[]     values;		// reference is copied during clone()
	private   final int       sum;

	protected       boolean[] subset;		// cloned during clone()

	public SubsetSumIndividual(int[] values, int sum) {
		this.values = values;
		this.sum    = sum;
		this.subset = new boolean[values.length];
	}

	@Override
	public SubsetSumIndividual clone() {
		SubsetSumIndividual copy = (SubsetSumIndividual) super.clone();
		copy.subset = copy.subset.clone();

		return copy;
	}

	@Override
	protected double evaluate() {
		int realSum = 0;

		for (int i = 0;  i < values.length;  ++i)
			if (subset[i])
				realSum += values[i];

		return Math.abs(realSum - sum);
	}

	@Override
	public Individual crossover(Individual other) {
		SubsetSumIndividual copy = clone();
		SubsetSumIndividual rhs  = (SubsetSumIndividual) other;

		int point = (int)(Math.random() * (subset.length-1)) + 1;

		for (int index = point;  index < subset.length;  ++index)
			copy.subset[point] = rhs.subset[point];

		return copy;
	}

	@Override
	public Individual mutate() {
		SubsetSumIndividual copy = clone();

		int bit = (int)(Math.random() * subset.length);
		copy.subset[bit] = !copy.subset[bit];

		return copy;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder("[");

		for (int i = 0;  i < values.length;  ++i)
			if (subset[i]) {
				buf.append(' ');
				buf.append(values[i]);
			}

		buf.append(" (");
		buf.append(getFitness());

		buf.append(") ]");

		return buf.toString();
	}

}
