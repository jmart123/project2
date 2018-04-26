public class Sub implements Instruction
{
		public int run(vmStack vms, int programCounter){
				int a;
				int b;
				int diff;

				//get values from stack
				a = vms.pop();
				b = vms.pop();

				//subtract
				diff = a - b;

				//push difference
				vms.push(diff);

				return programCounter;
		}
}
