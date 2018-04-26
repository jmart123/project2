public class Ifez implements Instruction
{
    private int parameter; //represents PC relative offset

    Ifez(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if(a == 0){
            programCounter += (parameter - 1);
        }
    }
}
