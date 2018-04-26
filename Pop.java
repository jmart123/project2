public class Pop implements Instruction
{
		public int run(vmStack vms, int programCounter){
				int notUsed;

				notUsed = vms.pop(); //just pops the value. value goes unused.

				return programCounter;
		}	
}
