import java.util.ArrayList;

public class Page {

	private double width;
	private double height;
	private double area;
	private double capacity;
	private ArrayList<Advert> adverts;
	private ArrayList<Integer> velocities;
	private ArrayList<Advert> pbest;
	private double fitness = 0;

	public Page() {
		this.width = 100;
		this.height = 80;
		this.area = width * height;
		this.capacity = this.area;
		adverts = new ArrayList<>();
		velocities = new ArrayList<>();
		pbest = new ArrayList<>();
	}

	/* Getter and Setters */
	public double getFitness() {
		if (fitness == 0) {
			fitness = FitnessCalc.getFitness(this);
		}
		return fitness;
	}

	public ArrayList<Advert> getDatas() {
		return adverts;
	}

	public ArrayList<Integer> getVelocities() {
		return velocities;
	}

	public void setVelocities(ArrayList<Integer> velocities) {
		this.velocities = velocities;
	}

	public int getVelocity(int index) {
		return velocities.get(index);
	}

	public void setPbest(ArrayList<Advert> pbest) {
		for (int i = 0; i < pbest.size(); i++) {
			Advert cloneAdvert = new Advert(pbest.get(i).getGenes(), pbest.get(i).getArea(), pbest.get(i).getCost());
			pbest.set(i, cloneAdvert);
		}
	}

	public ArrayList<Advert> getPbest() {
		return pbest;
	}

	public ArrayList<Advert> getAdverts() {
		return adverts;
	}

	public int advertSize() {
		return adverts.size();
	}

	public Advert getPBestValue(int index) {
		return pbest.get(index);
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public double getArea() {
		return area;
	}

	public double getPBestFitness() {
		double fitness = 0.0;
		double sumArea = 0.0;
		for (int i = 0; i < pbest.size(); i++) {
			Advert advert = pbest.get(i);
			fitness += advert.getCost();
			sumArea += advert.getArea();
		}

		if (sumArea > this.getArea())
			return -1;
		else
			return fitness + (this.advertSize());
	}

	/* Public Methods */

	public void fillPage() {
		Advert newAdvert = new Advert();
		while (capacity - newAdvert.getArea() >= 0) {
			addAdvert(newAdvert);
			Advert cloneAdvert = new Advert(newAdvert.getGenes(), newAdvert.getArea(), newAdvert.getCost());
			pbest.add(cloneAdvert);
			velocities.add(0);
			capacity -= newAdvert.getArea();
			newAdvert = new Advert();
		}
		fitness = 0;
	}

	public double sumAdvertCapacity() {
		double sum = 0;
		for (int i = 0; i < advertSize(); i++) {
			sum += adverts.get(i).getArea();
		}
		return sum;
	}

	public void updateCapacity() {
		capacity = area;
		for (int i = 0; i < advertSize(); i++) {
			capacity -= adverts.get(i).getArea();
		}
	}

	public void addAdvert(Advert advert) {
		adverts.add(advert);
	}

	public void setAdvert(int index, Advert advert) {
		adverts.set(index, advert);
	}

	public void setPBest(int index, Advert advert) {
		pbest.set(index, advert);
	}

	public void setVelocity(int index, int value) {
		velocities.set(index, value);
	}

	@Override
	public String toString() {
		return adverts.toString();
	}

}
