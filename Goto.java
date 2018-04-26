public class Goto implements Instruction
{

	private int parameter;
	private int newCounter; //For new PC after the Goto

	Goto(int param){
		parameter = param;
	}

	public void run(vmStack vms, int programCounter){
		// Increment program counter by PC+parameter(PC relative offset)

		programCounter += (parameter/4);
		setNC(programCounter);
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
