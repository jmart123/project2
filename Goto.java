public class Goto implements Instruction
{

	private int parameter;
	private int newCounter; //For new PC after the Goto

	Goto(int param){
		parameter = param;
	}

	public int run(vmStack vms, int programCounter){

		// Increment program counter by PC+parameter(PC relative offset)
		return programCounter + (parameter / 4) - 1;
		
	}

	// Goto setter
	public void setNC(int pc){
		newCounter = pc;
	}
	
	// Goto getter
	public int getNC(){
		return newCounter;
	}
}
