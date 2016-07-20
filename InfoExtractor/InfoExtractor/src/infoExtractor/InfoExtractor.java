package infoExtractor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class InfoExtractor {
	
	public static void main(String[] args) {
//		List holderList = new ArrayList();
		JSONParser parser = new JSONParser();
		try {
			int numOfAgents = 0;
			int numOfTasks = 0;
			double[] costPerAgent = new double[0];
			int[][] tasksPerAgent = new int[0][];
			int[] tempTasks = new int[0];
			ArrayList<JSONArray> tasksEachAgentCanDo = new ArrayList<JSONArray>();
			JSONArray mainArray = (JSONArray) parser.parse(new FileReader("D:\\Downloads\\prop_cost_norm_task\\00120n_00020m_000t.json"));
			if (mainArray != null) {
				int len = mainArray.size();
				for (int i = 0; i < mainArray.size(); i++) {
					if (mainArray.get(i).getClass().equals(Long.class)) {
						if (i == 0) {
							numOfAgents = (int)(long)mainArray.get(i);
							//System.out.println(numOfAgents);
							tasksPerAgent = new int[numOfAgents][];
							costPerAgent = new double[numOfAgents]; 
						}
						else if (i == 1) {
							numOfTasks = (int)(long)mainArray.get(i);
							// System.out.println(numOfTasks);
							tempTasks = new int[numOfTasks];
						}
						
					}
					else if (mainArray.get(i).getClass().equals(JSONArray.class)) {
						if (i == 2) {
							//costPerAgent = ((ArrayList<Object>)mainArray.get(i));
							for (int j = 0; j < ((List)mainArray.get(i)).size(); j++) {
								if (((List)mainArray.get(i)).get(j).getClass().equals(Double.class)) {
									costPerAgent[j] = (((Double)((List)mainArray.get(i)).get(j)).doubleValue());
								} else if (((List)mainArray.get(i)).get(j).getClass().equals(Long.class)){
									costPerAgent[j] = (((Long)((List)mainArray.get(i)).get(j)).doubleValue());
								}
								
							}
							//System.out.println(Arrays.toString(costPerAgent));
						}
						else if (i == 3) {
							tasksEachAgentCanDo = ((ArrayList<JSONArray>)mainArray.get(i));
							//ArrayList<Integer> tasksPerAgent = ((ArrayList<Integer>)tasksEachAgentCanDo);
							//System.out.println(tasksPerAgent.get(1).getClass());
							for (int j = 0; j < tasksEachAgentCanDo.size(); j++) {
								tempTasks = new int[tasksEachAgentCanDo.get(j).size()];
								for (int k = 0; k < tasksEachAgentCanDo.get(j).size(); k++) {
									tempTasks[k] = (((Long)tasksEachAgentCanDo.get(j).get(k)).intValue());
								}
								tasksPerAgent[j] = tempTasks;
								//System.out.println(Arrays.toString(tasksPerAgent[j]));
							}
//							System.out.println(Arrays.toString(tasksPerAgent));
						}
					}
					else {
						System.out.println("Unexpected Type");
						System.out.println(mainArray.get(i).getClass());
					}
				}
			}
			System.out.println("Done Parsing");
			KRobustnessHeuristicAlgorithm.DoIt(numOfAgents, numOfTasks, costPerAgent, tasksPerAgent, 3);
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

}