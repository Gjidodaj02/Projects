public class StepsGjidoda {

//variables
private int myStepWidth;
private int myNumSteps;
private char myFillStyle;
	
	// converts input to new variable names
	public StepsGjidoda(int newStepWidth, int newNumSteps, char newFillStyle)
	{
		myStepWidth = newStepWidth;
		myNumSteps = newNumSteps;
		myFillStyle = newFillStyle;
	}// full constructor
	
	//null constructor, check for errors
	{
		myStepWidth = -99;
		myNumSteps = -11111;
		myFillStyle = 'f';
	}//empty constructor
	
	//method that sets step width
	public void setStepWidth(int newStepWidth)
	{
		myStepWidth = newStepWidth;
	}//set step width
	
	//method that sets num of steps
	public void setNumSteps(int newNumSteps)
	{
		myNumSteps = newNumSteps;
	}//set num steps
	
	//method that sets fill style
	public void setFillStyle(char newFillStyle)
	{
		myFillStyle = newFillStyle;
	}//set fill style
	
	//method that returns step width
	public int getStepWidth()
	{
		
		return myStepWidth;
	}//get step width
	
	//method that retunrs num of steps
	public int getNumSteps()
	{
		
		return myNumSteps;
	}//get num steps
	
	//method that retunrs fill style
	public char getFillStyle()
	{
		
		return myFillStyle;
	}//get fill style
	
	//method that calculates area of steps
	public int calcArea()
	{
		//variables
		int area = 0, myStepsNum = getNumSteps(), myWidthNum = getStepWidth();
		
		for (int i = 1; i <= myStepsNum; i++)
			{
				area += (myWidthNum * i);
			}//for
		
		return area;
	}//calcArea
	
	//method that draws the steps (doesnt print!!)
	public void drawSteps()
	{
		//variables
		int width, i, j, mySteps = getNumSteps(), myWidth = getStepWidth();
		char myStyle = getFillStyle();
	
		
		 for (i = 1; i <= mySteps; i++) 
		 { 
			 width = myWidth * i;
			 
			 for (j = 0; j < width; j++) 
				 { 
					System.out.print(myStyle); 	 
				 }//for 
		 
			 System.out.println();
		 }//for
	 }//drawSteps
	
	//method that turns data into a string, 
	public String toString()
	{
		//variables
		String result;
		int widthOfStep = getStepWidth(), numOfSteps = getNumSteps(), 
				 totalArea = calcArea();
		char styleOfSteps = getFillStyle();
		
		result = "Width of steps: " + widthOfStep + "\n";
		result += "Number of Steps: " + numOfSteps + "\n";
		result += "Fill style for steps: " + styleOfSteps + "\n";
		result += "Total area of fill style characters: " + totalArea;
		return result;
		
	}//toString
}//StepsGjidoda