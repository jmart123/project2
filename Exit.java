public class Exit implements Instruction
{
		private int parameter;

		Exit(int param){
				parameter = param;
		}

		public void run(vmStack vms, int pc){
			System.exit(parameter);
		}
}
