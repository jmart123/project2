public class Swap implements Instruction
{
	public void run(vmStack vms, int pc){
		
		//case with empty stack
	 	if(vms.size() == 0) return;

		//case with only 1 value on the stack
	 	if(vms.size() == 1) vms.push(0);

		//case with two or more values on the stack
		int a;
		int b;
				
		a = vms.pop();
		b = vms.pop();

		vms.push(a);
		vms.push(b);
	}
}
