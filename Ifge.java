public class Ifge implements Instruction
{
    private int parameter; //represents relative offset

    Ifge(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackpointer() - 1);

        //check conditional
        if(a >= b){
            programCounter += (paramter - 1); //program counter is also incremented in the processor
        }
    }
}
