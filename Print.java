public class Print implements Instruction
{
    //print the value on top of the stack
    public void run(vmStack vms, int programCounter){
        System.out.println(vms.peek(vms.getStackPointer()));
    }
}
