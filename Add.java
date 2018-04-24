public class Add Implements Instruction
{
		public void run(vmStack vms){
				int a;
				int b;
				int sum;

				a = vms.pop();
				b = vms.pop();

				sum = a + b;
				vms.push(sum);
		}
}
