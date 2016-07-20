/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoExtractor;

import java.util.ArrayList;

/**
 *
 * @author Anamitra Paul
 */
public class Task extends k.robust.Task{
   
   private int faceValue;
   private double taskAverage;
   private ArrayList<Agent> topAgents;
   public static int taskCount = 0;
   
   public Task(int t) {
	   super(t);
	   taskCount++;
       faceValue = taskCount;
       taskAverage = 0;
   }
   
   public void setTaskAverage(double taskAverage) {
       this.taskAverage = taskAverage;
   }
   
   public void setTopAgents(ArrayList<Agent> topAgents) {
       this.topAgents = topAgents;
   }
   
   public int getFaceValue() {
       return faceValue;
   }
   
   public double getTaskAverage() {
       return taskAverage;
   }
   
   public ArrayList<Agent> getTopAgents() {
       return topAgents;
   }
   
}
