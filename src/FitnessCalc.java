import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FitnessCalc {
	
	static double getFitness(Page page) {
		double fitness = 0.0;
		double sumArea = 0.0;
		for (int i = 0; i < page.advertSize(); i++) {
			Advert advert = page.getAdverts().get(i);
			fitness += advert.getCost();
			sumArea += advert.getArea();
		}
		
		if(sumArea > page.getArea())
			return -1;
		else
			return fitness + (page.advertSize());
	}
	
	static double diffMirror(double value) {
    	while(!(value > PSO.lowerBoundary && value < PSO.upperBoundary)) {
			
    		double diff = 0.0;
    		if (value < PSO.lowerBoundary) {
    			diff = value - PSO.lowerBoundary;
    			value = PSO.lowerBoundary - diff;
    		} 
    		
    		if (value > PSO.upperBoundary){
    			diff = value - PSO.upperBoundary;
    			value = PSO.upperBoundary - diff;
    		}
    		
		}
    	return value;
    }
	
	public static void writeResult(String result, String fName) throws IOException {
		String fileName = "output_files/" + fName;
		File file = new File(fileName);
		if(!file.exists()) file.createNewFile();
		FileWriter writer = new FileWriter(file);
		writer.write(result);
		writer.close();
	}
	
}
