

public class Swarm {
	
	private int PARTICLE_SIZE;
	private Page[] pages;
	
	public Swarm(int particle_size) {
		PARTICLE_SIZE = particle_size;
		pages = new Page[PARTICLE_SIZE];
	}
	
	// Create swarm
	public void initSwarm() {
		for (int i = 0; i < pages.length; i++) {
			Page newParticle = new Page();
			newParticle.fillPage();
			pages[i] = newParticle;
		}
	}
	
	public void calculateVelocities(Page gBest) {
		for (int i = 0; i < pages.length; i++) {
			Functions.calculateVelocities(pages[i], gBest);
		}
	}

	public void updateParticles(Page gBest) {
		for (int i = 0; i < pages.length; i++) {
			Functions.updateParticles(pages[i], gBest);
			//pages[i].updateCapacity();
		}
	}
	
	public Page getPage(int index) {
		return pages[index];
	}
	
	/* Public Functions */
	public void savePage(int index,Page advert) {
		pages[index] = advert;
	}
	
	public int size() {
		return pages.length;
	}
	
	/* Getters and Setters */
	public Page[] getParticles() {
		return pages;
	}
}
