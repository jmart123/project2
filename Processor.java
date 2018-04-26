import java.util.*;

class Processor {

	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("Usage: java -jar vmach.jar [inputfile]");
			System.exit(-1);
		}

		// Create a new parser
		Parser p = new Parser(args[0]);
		// Load instruction list from parser
		List<Instruction> instr = p.getInstructions();
		// Create a new stack
		vmStack vms = new vmStack();

        // Instruction loop. Program counter is returned from each instruction.
		for (int pc = 0; pc < instr.size(); pc++) {
			pc = instr.get(pc).run(vms, pc);
		}
	}
}
