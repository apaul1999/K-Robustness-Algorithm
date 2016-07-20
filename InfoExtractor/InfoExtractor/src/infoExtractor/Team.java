package infoExtractor;

import java.util.ArrayList;
import k.robust.Agent;

public class Team implements k.robust.TeamInterface{

	private ArrayList<Agent> Agents = new ArrayList<Agent>();
	
	public Team(ArrayList<Agent> Team){
		this.Agents = Team;
	}
	public ArrayList<Agent> getAgents() {
		return this.Agents;
	}
}
