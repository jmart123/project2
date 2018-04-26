public class Ifpl implements Instruction
{
    private int parameter; //represents PC relative offset
    private int newCounter;

    Ifpl(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if (a >= 0){
            return programCounter + (parameter / 4) - 1;
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
