public class Ifle implements Instruction
{
    private int parameter; //represents PC relative offset

    Ifle(int param){
        parameter = param;
    }

    public int run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional 
        if (a <= b){
            return programCounter + (parameter / 4) - 1;
        } else {
			return programCounter;
		}
    }
}
