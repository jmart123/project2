public class Dump implements Instruction
{
    public int run(vmStack vms, int programCounter){
        vms.dump(); //uses processor to iterate through the stack
		return programCounter;
    }
}
