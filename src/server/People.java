package server;

public class People implements Comparable<People>{
	private String ID;
	private int score;
	
	public People(String ID, int score)
	{
		this.ID=ID;
		this.score=score;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public int getScore()
	{
		return score;
	}

	public int compareTo(People peo) {
		if(this.score < peo.score)return 1;
		else if(this.score==peo.score)return 0;
		else return -1;
	}
	
}
