public class Ifnz implements Instruction
{
    private int parameter; //represents PC relative offset
    private int newCounter;

    Ifnz(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        int a;

        //peek value from stack, do not POP
        a = vms.peek(vms.getStackPointer());

        //check conditional
        if(a != 0){
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
