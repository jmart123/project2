public class Push implements Instruction
{
    private int parameter; // represents the value to be pushed

    Push(int param){
        parameter = param; //value to be pushed is passed to constructor when Push instruction is created
    } 
    Push(){
        parameter = 0; //default must be 0
    }

    public void run(vmStack vms, int programCounter){
        vms.push(parameter); //push parameter to the stack
    }
}
