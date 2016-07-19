

public abstract class Individual implements Cloneable, Comparable, Variable {

	private static final double IDEAL_FITNESS = 0;

	private double fitness;

	public Individual() {
		fitness = Double.NaN;
	}

	public double getFitness() {
		if (Double.isNaN(fitness))
			fitness = evaluate();

		return fitness;
	}

	protected abstract double evaluate();

	public boolean isIdeal() {
		return getFitness() == IDEAL_FITNESS;
	}

	@Override
	public Individual clone() {
		try {
			Individual copy = (Individual) super.clone();
			copy.fitness = Double.NaN;

			return copy;
		} catch (CloneNotSupportedException e) {
			throw new Error("Unexpected error", e);
		}
	}

	@Override
	public int compareTo(Object obj) {
		Individual other = (Individual) obj;
		return new Double(getFitness()).compareTo(new Double(other.getFitness()));
	}

}
