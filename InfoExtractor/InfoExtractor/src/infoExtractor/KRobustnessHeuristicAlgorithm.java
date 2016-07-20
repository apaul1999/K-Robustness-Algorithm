/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoExtractor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import k.robust.TeamInterface;


/**
 *
 * @author Anamitra Paul
 */
public class KRobustnessHeuristicAlgorithm implements k.robust.TeamFinderInterface{
	
	private Team team;
	private static Team team1;
	public TeamInterface findTeam(int k) {
		team = team1;
		return team;
	}
    /**
     * @param args the command line arguments
     */
    public static void DoIt(int numOfAgents, int numOfTasks, double[] costPerAgent, int[][] tasksPerAgent, int k) {
        
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Please enter the robustness value.");
//        int k = scan.nextInt();
        int numTasks = numOfTasks;
        ArrayList<Agent> agents = new ArrayList<Agent>();
//        System.out.println(numOfAgents);
//       System.out.println(numOfTasks);
//        Agent tempAgent;
//        for (int i = 0; i < numOfAgents; i++) {
//        	for (int j = 0; j < ) {
//        		
//        	}
//        }
    	Task[] tasks = new Task[numOfTasks];
    	for(int task = 0; task < tasks.length; task++) {
    		tasks[task] = new Task(task);
    	}
        for (int i = 0; i < numOfAgents; i++) {
        	k.robust.Task[] tempTasks = new k.robust.Task[tasksPerAgent[i].length];
        	for (int j = 0; j < tasksPerAgent[i].length; j++) {
        		k.robust.Task tempTask = new k.robust.Task(tasksPerAgent[i][j]);
        		tempTasks[j] = tempTask;
        	}
        	agents.add(new Agent(i, tasksPerAgent[i], costPerAgent[i], tempTasks));
        }
//    	int[] tasksAgentOne = new int[]{1,2};
//    	int[] tasksAgentTwo = new int[]{2,3};
//    	int[] tasksAgentThree = new int[]{3};
//    	int[] tasksAgentFour = new int[]{1,2,3};
//    	
//    	Agent one = new Agent(tasksAgentOne, 2);
//    	Agent two = new Agent(tasksAgentTwo, 1);
//    	Agent three = new Agent(tasksAgentThree, 3);
//    	Agent four = new Agent(tasksAgentFour, 4);
//    	
//    	agents.add(one);
//    	agents.add(two);
//    	agents.add(three);
//    	agents.add(four);
            
        ArrayList<Agent> usedAgents = new ArrayList<Agent>();
        
        for (int task = 0; task < tasks.length; task++) {
            int counter = 0;
            double sum = 0;
            ArrayList<Agent> performingAgents = new ArrayList<Agent>();
            ArrayList<Agent> topAgents = new ArrayList<Agent>();
            
            for (int agentNum = 0; agentNum < agents.size(); agentNum++) {
                for (int taskNumAgent = 0; taskNumAgent < agents.get(agentNum).getTaskSet().length; taskNumAgent++) {
                    if (agents.get(agentNum).getTaskSet()[taskNumAgent] == tasks[task].getFaceValue() - 1) {
                        sum += agents.get(agentNum).getCostPerTask();
                        performingAgents.add(agents.get(agentNum));
                        counter++;
                        break;
                    }
                }
            }
            
            tasks[task].setTaskAverage(sum / counter);
            
            for (int i = 0; i < performingAgents.size(); i++) {
                for (int j = i + 1; j < performingAgents.size(); j++) {
                    if (performingAgents.get(i).getCostPerTask() > performingAgents.get(j).getCostPerTask()) {
                        Agent temp = performingAgents.get(i);
                        performingAgents.set(i, performingAgents.get(j));
                        performingAgents.set(j, temp);
                    }
                }
            }
            
            try {
                for (int i = 0; i <= k; i++) {
                    topAgents.add(performingAgents.get(i));
                    usedAgents.add(topAgents.get(i));
                    topAgents.get(i).setCost(0);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("A " + k + "-Robust solution does not exist for the given input.");
                return;
            }
        }
        
        for (int i = 0; i < tasks.length; i++) {
            for (int j = i + 1; j < tasks.length; j++) {
                if (tasks[i].getTaskAverage() > tasks[j].getTaskAverage()) {
                    Task temp = tasks[i];
                    tasks[i] = tasks[j];
                    tasks[j] = temp;
                }
            }
        }
        
        Set<Agent> agentSet = new HashSet<Agent>(usedAgents);
        ArrayList<k.robust.Agent> finalAgents = new ArrayList<k.robust.Agent>(agentSet);
        Iterator iterator = agentSet.iterator();
        
        System.out.println("There are " + agentSet.size() + " agents in the " + k + "-Robust Team.");
        
        double sum = 0;
        for (int i = 0; i < agentSet.size(); i++) {
        	sum += usedAgents.get(i).getInitCost();
        }
        System.out.println("The total cost of the agents is " + sum + ".");
        
        while (iterator.hasNext()) {
        	System.out.println(iterator.next());
        }
        Team finalTeam = new Team(finalAgents);
        team1 = finalTeam;
    }
    
}
