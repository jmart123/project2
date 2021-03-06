public class And implements Instruction
{
    public int run(vmStack vms, int programCounter){
        int a;
        int b;
        int logicalAnd;

        //get values from stack
        a = vms.pop();
        b = vms.pop();

        //compute logical and
        logicalAnd = a & b;

        //push the logical and of the two values to the stack
        vms.push(logicalAnd);
    
		return programCounter;
	}
}
