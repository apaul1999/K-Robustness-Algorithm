/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoExtractor;

import java.util.Arrays;

/**
 *
 * @author Anamitra Paul
 */
public class Agent extends k.robust.Agent{
    
    private int[] taskSet;
    private double cost;
    private double initCost;
    private double costPerTask;
    private double initCostPerTask;
    public static int number;
    
    public Agent(int t, int[] taskSet, double cost, k.robust.Task[] tasks) {
    	super (t, tasks, cost);
    	this.taskSet = taskSet;
    	this.cost = cost;
    	this.initCost = cost;
    	this.costPerTask = cost / taskSet.length;
    	this.initCostPerTask = initCost / taskSet.length;
    	number++;
    }
    
    public double getCost() {
        return cost;
    }
    
    public double getInitCost() {
    	return initCost;
    }
    
    public int[] getTaskSet() {
    	return taskSet;
    }
    
    public double getCostPerTask() {
    	return costPerTask;
    }
    
    public double getInitCostPerTask() {
    	return initCostPerTask;
    }
    
    public void setCost(int cost) {
    	this.cost = cost;
    }
    
    public void setCostPerTask(double costPerTask) {
    	this.costPerTask = costPerTask;
    }
    
    public String toString() {
 	   return Arrays.toString(tasks) + "(Cost: " + initCost + ")";
    }
}
