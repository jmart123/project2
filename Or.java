public class or implements Instruction
{
    public void run(vmStack vms, int programCounter){
        int a;
        int b;
        int logicalOr;

        //get values from stack
        a = vms.pop();
        b = vms.pop();

        //compute the logical or of a and b
        logicalOr = a | b;

        //push the logical or to the stack
        vms.push(logicalOr);
    }
}
