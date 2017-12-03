import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PSO {
	
	static final double upperBoundary = 50;
	static final double lowerBoundary = 10;
	private final int PARTICLE_SIZE = 150;
	private Page[] particles = new Page[PARTICLE_SIZE];
	public static List<Double> runResults;
	
	public PSO() throws IOException {
		
		Swarm swarm = new Swarm(PARTICLE_SIZE);
		
		swarm.initSwarm();
		particles = swarm.getParticles();
		
        Page gBest = particles[0];
        Page currentGBest = null;
        runResults = new ArrayList<>();
        
        String iterationResult = "Iteration,Best_Fitness\n";
        int epoch = 0;
        double globalMean = 0;
        int counter = 0;

        
        while(counter < 100) {
        	
        	swarm = new Swarm(PARTICLE_SIZE);
    		swarm.initSwarm();
    		particles = swarm.getParticles();
    		
            gBest = particles[0];
            currentGBest = null;
           
            epoch = 0;
            double runMean = 0;
            while(epoch < 100){
            	
                currentGBest = getGlobalBest();
                // if(any particle's pBest value is better than the gBest value, make it the new gBest value.
                if(currentGBest.getFitness() > gBest.getFitness()){
                    gBest = currentGBest;
                }
                
                if(counter == 3)
            		iterationResult += epoch + "," + gBest.getFitness() + "\n";
               
                runMean += gBest.getFitness();
                swarm.calculateVelocities(gBest);
                swarm.updateParticles(gBest);
                
                epoch++;

            }
            runResults.add(gBest.getFitness());
        	globalMean += gBest.getFitness();
            counter++;
            System.out.println("Run : " + counter + " - Global Best:" + gBest.getFitness() + " - Mean:"+ (runMean / 100) +" - Advert Size:" + gBest.advertSize() + " - Total Area:" + gBest.sumAdvertCapacity());
        }
        
        FitnessCalc.writeResult(iterationResult,"test2.txt");
        Collections.sort(runResults);
        System.out.println("-------------------");
        System.out.println("100 Run Best:" + runResults.get(runResults.size() - 1) + "- 100 Run Mean:" + (globalMean / 100) + " - 100 Run St. Dev:" + calculateStandartDev(globalMean / 100));
        
	}
	
	
	public Page getGlobalBest() {
		Page gBest = particles[0];
		for (int i = 1; i < particles.length; i++) {
			if(particles[i] != gBest){
            	Page currentParticle = particles[i];
                if(currentParticle.getFitness() > gBest.getFitness()){
                    gBest = particles[i];
                }
            }
		}
		return gBest;
	}
	
	public double calculateStandartDev(double mean) {
		
		double result = 0.0;
		for (Double value : runResults) {
			result += Math.pow((value - mean), 2); 
		}
		return Math.sqrt(result / (runResults.size() - 1)); 
	}
	
}
