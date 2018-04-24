public class Inpt implements Instruction
{
		public void run(vmStack vms){
				int val;

				val = System.console().readLine();

				vms.push(val);
		}
}
