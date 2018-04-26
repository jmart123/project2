public class Ifne implements Instruction
{
    private int parameter; //represents PC relative offset
    private int newCounter; //updated program counter

    //constructor takes an int, which is the offset
    Ifne(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack, do not pop
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional
        if(a != b){
            programCounter += (parameter/4);
            setNC(programCounter);
        }
    }

    public void setNC(int pc){
        newCounter = pc;
    }

    public int getNC(){
        return newCounter;
    }
}
