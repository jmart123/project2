public class Iflt implements Instruction
{
    private int parameter; //represents PC relative offset
    private int newCounter; //updated program counter after conditional

    Iflt(int param){
        parameter = param;
    }   

    public void run(vmStack vms, int programCounter){
        int a;
        int b;

        //peek values from stack. does not pop.
        a = vms.peek(vms.getStackPointer());
        b = vms.peek(vms.getStackPointer() - 1);

        //check condition, increase program counter if it is met
        if(a < b){
            programCounter += (parameter/4);
            setNC(programCounter);
        }
    }

    //set new program counter
    public void setNC(int pc){
        newCounter = pc;
    }

    //get new counter
    public int getNC(){
        return newCounter;
    }

}
