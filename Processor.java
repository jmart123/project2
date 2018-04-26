import java.util.*;

class Processor {

	public static void main(String[] args) {
		// Create a new parser
		Parser p = new Parser(args[0]);
		// Load instruction list from parser
		List<Instruction> instr = p.getInstructions();
		// Create a new stack
		vmStack vms = new vmStack();

		for (int i = 0; i < instr.size(); i++) {

			// Special case for Goto instructions - Updates PC
			if(instr.get(i) instanceof Goto){
				instr.get(i).run(vms, i);
				i = ((Goto)instr.get(i)).getNC() - 1;		            
			}
			// Normal case for instructions - run the instr
			else{
				instr.get(i).run(vms, i);
			}
		}
	}
}
