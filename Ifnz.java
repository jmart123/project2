public class Ifnz implements Instruction
{
    private int parameter; //represents PC relative offset

    Ifnz(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if(a != 0){
            programCounter += (paramter - 1);
        }
    }
}
