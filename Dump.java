public class Dump implements Instruction
{
    public void run(vmStack vms, int programCounter){
        vms.dump(); //uses processor to iterate through the stack
    }
}
