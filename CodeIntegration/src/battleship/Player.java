package battleship;

class Player 
{
    private String name;
    private int score;

// constructor
	public Player(String name, int score) 
	{
	   this.name = name;
	   this.score = score;
	}

    // Getter
	
    public String getName() 
    { 
    	return name; 
    }   
        
    public int getScore() 
    {    
    	return score; 
    }
    
    // Setter

    public void setName(String name) 
    { 
    	this.name = name; 
    }
    public void setScore(int score) 
    { 
    	this.score = score; 
    }
}
