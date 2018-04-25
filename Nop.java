public class Nop implements Instruction
{
		public void run(vmStack vms, int programCounter){
				return; //instruction does nothing, move program counter -- done in Processor.java
		}
}
