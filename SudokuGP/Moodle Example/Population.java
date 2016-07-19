import java.util.Arrays;

public class Population /* implements Iterable */ {

	private       Individual[] individuals;
	private final Selection    selection;

	public Population(int size, Individual prototype, Selection selection) {
		individuals = new Individual[size];

		for (int i = 0;  i < size;  ++i)
			individuals[i] = prototype.clone();

		this.selection = selection;
	}

	public Individual getBest() {
		return individuals[0];
	}

	public void nextGeneration() {
		Individual[] newPop = new Individual[individuals.length];

		for (int index = 0;  index < newPop.length;  ++index)
			newPop[index] = selection.reproduce(individuals);

		individuals = newPop;
		sort();
	}

	private void sort() {
		Arrays.sort(individuals);
	}

//	@Override
//	public Iterator iterator() {
//		// Cheating here... should be ArrayIterator class
//		return Arrays.asList(individuals).iterator();
//	}

}
