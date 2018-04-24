public interface Instruction
{
		public void run(vmStack vms);
}

public class Add implements Instruction
{
		private int parameter;

		public void setParamater(int param) {
				parameter = param;
		}		
		
		public void run(vmStack vms){
				int a;
				int b;

				a = vms.pop();
				b = vms.pop();

				int sum = a + b;

				vms.push(sum);
		}
}
