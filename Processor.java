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

/*			// Special case for Goto instructions - Updates PC
			if(instr.get(i) instanceof Goto) {
				instr.get(i).run(vms, i);
				i = ((Goto)instr.get(i)).getNC() - 1; 
			} else if(instr.get(i) instanceof Ifeq) {
				instr.get(i).run(vms, i);
				i = ((Ifeq)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifne) {
				instr.get(i).run(vms, i);
				i = ((Ifne)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Iflt) {
				instr.get(i).run(vms, i);
				i = ((Iflt)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifgt) {
				instr.get(i).run(vms, i);
				i = ((Ifgt)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifle) {
				instr.get(i).run(vms, i);
				i = ((Ifle)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifge) {
				instr.get(i).run(vms, i);
				i = ((Ifge)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifez) {
				instr.get(i).run(vms, i);
				i = ((Ifez)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifnz) {
				instr.get(i).run(vms, i);
				i = ((Ifnz)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifmi) {
				instr.get(i).run(vms, i);
				i = ((Ifmi)instr.get(i)).getNC() - 1; 	
			} else if(instr.get(i) instanceof Ifpl) {
				instr.get(i).run(vms, i);
				i = ((Ifpl)instr.get(i)).getNC() - 1; 	
			} else { // Normal case for instructions - run the instr
				instr.get(i).run(vms, i);
			}
*/
			i = instr.get(i).run(vms, i);
		}
	}
}
