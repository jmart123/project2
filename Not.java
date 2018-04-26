public class Not implements Instruction
{
    public int run(vmStack vms, int programCounter){
        int a;
        int logicalNot;

        //get value from stack
        a = vms.pop();

        //logically not value from stack
        logicalNot = ~a;

        //push the logicalNot to the stack
        vms.push(logicalNot);
		
		return programCounter;
    }
}
