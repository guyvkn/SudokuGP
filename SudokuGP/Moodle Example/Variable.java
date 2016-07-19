
public interface Variable {

	Individual mutate();
	Individual crossover(Individual other);

}
