public class Swap implements Instruction
{
	public void run(vmStack vms, int pc){
		
		//case with empty stack
        if(vms.getStackPointer() == -1) return;

		//need case with only 1 value on the stack
		if(vms.getStackPointer() == 0) vms.push(0);
        
        int a = vms.pop();
        int b = vms.pop();

		vms.push(a);
		vms.push(b);
	}
}
