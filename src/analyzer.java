import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


public class analyzer {
	public static void main(String args[]){
		//make hashmap to store entrance occurrences, id. 1,2,3,4,5
		HashMap<String, Integer> entries= new HashMap<String, Integer>();
		//initialize doubles for each girl's ranking
		double madokaRank=0;
		double sayakaRank=0;
		double homuraRank=0;
		double mamiRank=0;
		double kyoukoRank=0;
		int entryCounter=0;
		File csv= new File("data.csv");
		try {
			Scanner sc=new Scanner(csv);
			while(sc.hasNextLine()){
				String entry= sc.nextLine();
				System.out.println(entry);
				//add or increment the number of time this entry has occurred
				if (entries.containsKey(entry)){
					entries.put(entry, entries.get(entry)+1);
				}
				else{
					entries.put(entry, 1);
				}
				//add the ranking value to the cumulative rank
				String[] rankings=entry.split(",");
				for(int i=0; i<rankings.length; i++){
					if(i==0){madokaRank+=Integer.parseInt(rankings[i]);}
					if(i==1){sayakaRank+=Integer.parseInt(rankings[i]);}
					if(i==2){homuraRank+=Integer.parseInt(rankings[i]);}
					if(i==3){mamiRank+=Integer.parseInt(rankings[i]);}
					if(i==4){kyoukoRank+=Integer.parseInt(rankings[i]);}
				}
				entryCounter++;
				
			}
			sc.close();
			//now we print out the data
			//start with the average ranking. Should be sorted descending
			TreeMap<String, Double> rankingsHM= new TreeMap<String, Double>();
			rankingsHM.put("Madoka", madokaRank);
			rankingsHM.put("Sayaka",sayakaRank);
			rankingsHM.put("Homura",homuraRank);
			rankingsHM.put("Mami", mamiRank);
			rankingsHM.put("Kyouko", kyoukoRank);
			System.out.printf("*****\nPrinting Data\n*****\n");
			System.out.println("Average rankings");
			for(String s: rankingsHM.keySet()){
				System.out.printf("%s: %.4f\n",s,rankingsHM.get(s)/entryCounter);
			}
			
			//For funsies, did anyone put 1,2,3,4,5 as the ranking?
			System.out.print("Was '1,2,3,4,5' ever entered? ");
			if(entries.containsKey("1,2,3,4,5")){
				System.out.println("Yes!");
			}
			else{
				System.out.println("No.");
			}
			
			//ok now we print the most occurring entry
			Entry<String, Integer> max= null;
			for(Entry<String, Integer> e: entries.entrySet()){//entry
				if(max==null|| e.getValue()>max.getValue()){
					max=e;
				}
			}
			System.out.printf("The most occuring entry was %s, occurring %d times\n", max.getKey(), max.getValue());
			System.out.println("My work here is done.");			
			
		} catch (FileNotFoundException e) {
			// I forgot to name the data file correctly 
			e.printStackTrace();
		}
	}
}
