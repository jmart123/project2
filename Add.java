public class Add implements Instruction
{
		public int run(vmStack vms, int programCounter){
				int a;
				int b;
				int sum;

				//get values from stack
				a = vms.pop();
				b = vms.pop();

				//subtract
				sum = a + b;

				//push difference
				vms.push(sum);

				return programCounter;
		}
}
