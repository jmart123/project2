public class Dup implements Instruction
{
    private int parameter; //represents stack offset

    Dup(int param){
        parameter = param/4; //we are indexing on integers. Each offset is a multiple of 4.
    }

    public int run(vmStack vms, int programCounter){
        int a;

        //peek the value to dup based on offset
        a = vms.peek(vms.getStackPointer() - parameter);

        //push the peeked value to the stack
        vms.push(a);

		return programCounter;
    }
}
