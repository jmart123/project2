/*
	CS365 Group Project 2 - Stack Virtual Machine

	Gurudev Ball-Khalsa
	Jeffery Martin
	Alexander Nehls
	Tristan Spakes
*/

import java.util.*;

class Processor {

	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("Usage: java -jar vmach.jar [inputfile]");
			System.exit(-1);
		}

		// Create a new stack
		vmStack vms = new vmStack();
		
		// Create a new parser
		Parser p = new Parser(args[0], vms);
		
        // Instruction loop. Program counter is returned from each instruction.
		for (; vms.pc > vms.ic; vms.pc--) {
			vms.pc = p.parse(vms.peek(vms.pc)).run(vms, vms.pc);
		}
	}
}
