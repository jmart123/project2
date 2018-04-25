public class Iflt implements Instruction
{
    private int parameter; //represents PC relative offset

    Iflt(int param){
        parameter = param;
    }   

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack. does not pop.
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackpointer() - 1);

        //check condition, increase program counter if it is met
        if(a < b){
            programCounter += (parameter - 1);
        }
    }

}
