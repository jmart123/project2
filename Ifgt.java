public class Ifgt implements Instruction
{
    private int parameter; //represents the offset
    private int newCounter; //new programCounter after conditional

    Ifgt(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;
        int b;

        //get values from stack through peek, not pop
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional for greater than
        if (a > b){
            return programCounter + (parameter / 4) - 1; //the program counter is also incremented in the processor
//            setNC(programCounter);
        } else {
			return programCounter;
		}
    }

    //set new program counter
    public void setNC(int pc){
        newCounter = pc;
    }

    //return updated program counter
    public int getNC(){
        return newCounter;
    }
}
