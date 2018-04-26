public class Nop implements Instruction
{
		public int run(vmStack vms, int programCounter){
				return programCounter; //instruction does nothing, move program counter -- done in Processor.java
		}
}
