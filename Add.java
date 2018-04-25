public class Add implements Instruction
{
<<<<<<< HEAD
		public void run(vmStack vms, int programCounter){
				int a;
				int b;
				int sum;

				a = vms.pop();
				b = vms.pop();

				sum = a + b;
				vms.push(sum);
		}
=======
	public void run(vmStack vms, int pc){
		int a;
		int b;
		int sum;
				
		a = vms.pop();
		b = vms.pop();
		
		sum = a + b;
		vms.push(sum);
	}
>>>>>>> 0ac06a1fd5261340b51bfabf1bb319c5776eee3a
}
