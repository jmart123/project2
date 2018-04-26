public class Ifez implements Instruction
{
    private int parameter; //represents PC relative offset

    Ifez(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if (a == 0){
            return programCounter + (parameter / 4) - 1;
        } else {
			return programCounter;
		}
    }
}
