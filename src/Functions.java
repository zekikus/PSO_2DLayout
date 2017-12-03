import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Functions {
	
	private static final int V_MAX = 2;
	
	public static void calculateVelocities(Page page, Page gBest) {
		
		double c1 = 2.0, c2 = 2.0;
		
		ArrayList<Integer> velocity = page.getVelocities();
		
		int minSize = page.advertSize();
		if(gBest.advertSize() < minSize)
			minSize = gBest.advertSize();
		
 		for (int i = 0; i < minSize; i++) {
 			Advert advert = page.getAdverts().get(i);
 			double rand1 = Math.random();
 			double rand2 = Math.random();
 			double temp1 = c1 * rand1;
			double temp2 = page.getPBestValue(i).getArea() - advert.getArea();
			double temp3 = c2 * rand2;
			double temp4 = gBest.getAdverts().get(i).getArea() - advert.getArea();
			int result =  (int) ((0.71829 * velocity.get(i)) + temp1  - (temp2) + temp3 - (temp4));
			
		    if(result > V_MAX){
                velocity.set(i, V_MAX);
            }else if(result < -V_MAX){
                velocity.set(i, -V_MAX);
            }else{
                velocity.set(i, result);
            }
		}
 		
 		page.setVelocities(velocity);
	}
	
	public static void updateParticles(Page page, Page gBest) {
		
		ArrayList<Advert> data = page.getDatas();
		
		for (int i = 0; i < page.advertSize(); i++) {
			if(page != gBest) {
				double newWidth = FitnessCalc.diffMirror((data.get(i).getGene(0)) + page.getVelocity(i));
				double newHeight = FitnessCalc.diffMirror((data.get(i).getGene(1)) + page.getVelocity(i));
				
				data.get(i).setGene(0, newWidth);
				data.get(i).setGene(1, newHeight);
			}
		}
		
		// Check pBest value.
		page.setFitness(0);
		double fitness = page.getFitness();
		if(fitness > page.getPBestFitness()) {
			page.setPbest(data);
		}
		
	}
	
}
