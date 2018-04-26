public class Print implements Instruction
{
    //print the value on top of the stack
    public int run(vmStack vms, int programCounter){
        
		System.out.println();
		System.out.println("vmach print: " + Integer.toString(vms.peek(vms.getStackPointer())));
        System.out.println();
		
		return programCounter;
    }
}
