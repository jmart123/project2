public class Add implements Instruction
{
	public void run(vmStack vms, int programCounter){
			int a;
			int b;
			int sum;

      //get values from stack
			a = vms.pop();
			b = vms.pop();

      //add values
			sum = a + b;

			//push sum on stack
			vms.push(sum);
	}
}
