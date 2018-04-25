public class Exit implements Instruction
{
		private int parameter;

		Exit(int param){
				parameter = param;
		}

<<<<<<< HEAD
		public void run(vmStack vms, int programCounter){
				System.Exit(parameter);
=======
		public void run(vmStack vms, int pc){
			System.exit(parameter);
>>>>>>> 0ac06a1fd5261340b51bfabf1bb319c5776eee3a
		}
}
