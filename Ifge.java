public class Ifge implements Instruction
{
    private int parameter; //represents relative offset
    private int newCounter; //updated program counter

    Ifge(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check conditional
        if(a >= b){
            programCounter += (parameter/4); //program counter is also incremented in the processor
            setNC(programCounter);
        }
    }

    //set new program counter
    public void setNC(int pc){
        newCounter = pc;
    }

    //get updated program counter
    public int getNC(){
        return newCounter;
    }
}
