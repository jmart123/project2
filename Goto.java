public class Goto implements Instruction
{

    private int parameter;
    private int newCounter;

    Goto(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        //vms.programCounter += parameter;
        //need to increment program counter by PC+parameter(PC relative offset)
        
        programCounter += (parameter/4);
        setNC(programCounter);
    }

    public void setNC(int pc){
        this.newCounter = pc;
    }

    public int getNC(){
        return this.newCounter;
    }
}
