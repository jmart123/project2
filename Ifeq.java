public class Ifeq implements Instruction
{
    private int parameter; //this parameter represents the PC relative offset

    Ifeq(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack. does not pop
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional
        if(a == b){
            programCounter += (parameter - 1); //add the PC + PC relative offset
        }
    }
}
