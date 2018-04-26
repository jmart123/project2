public class Ifmi implements Instruction
{
    private int parameter; //represents PC relative offset
    private int newCounter; //program counter IF the conditional is met

    Ifmi(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if (a < 0){
            return programCounter + (parameter / 4) - 1;
//            setNC(programCounter);
        } else {
			return programCounter;
		}
    }

    //set new program counter
    public void setNC(int pc){
        newCounter = pc;
    }

    //returns updated program counter
    public int getNC(){
        return newCounter;
    }
}   
