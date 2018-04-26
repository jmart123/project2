public class Ifeq implements Instruction
{
    private int parameter; //this parameter represents the PC relative offset
    private int newCounter; //updated program counter from conditional

    Ifeq(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack. does not pop
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional
        if (a == b) {
            return programCounter + (parameter / 4) - 1; //add the PC + PC relative offset
//            setNC(programCounter);
        } else {
			return programCounter;
		}
    }

    public void setNC(int pc){
        newCounter = pc;
    }

    public int getNC(){
        return newCounter;
    }
}
