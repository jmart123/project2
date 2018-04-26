public class Inpt implements Instruction
{
		public int run(vmStack vms, int programCounter){
				int val;

				val = Integer.parseInt(System.console().readLine());

				vms.push(val);

				return programCounter;
		}
}
