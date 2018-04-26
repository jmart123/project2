public class Ifgt implements Instruction
{
    private int parameter; //represents the offset

    Ifgt(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //get values from stack through peek, not pop
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional for greater than
        if(a > b){
            programCounter += (parameter - 1); //the program counter is also incremented in the processor
        }
    }
}
