public class Swap implements Instruction
{
		public void run(vmStack vms, int programCounter){
				//case with empty stack

			 	if(vms.getStackPointer == -1) return;

				//need case with only 1 value on the stack
			 	if(vms.getStackPointer == 0) vms.push(0);

				//case where there are two or more values on the stack
				int tmp;
				int a;
				int b;

        //pop values
				a = vms.pop();
				b = vms.pop();

        //push in opposite order
				vms.push(a);
				vms.push(b);
		}
}
