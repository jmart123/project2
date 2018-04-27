import java.util.*;
import java.io.*;
import static java.lang.Math.toIntExact;

public class Parser {
	
	private DataInputStream	  input; // binary input stream
	private int				  size;  // equals filesize/4 (NUM_INSTRUCTIONS)
	private vmStack			  vms;	 // virtual machine memory

	public Parser() {
		size = 0;
	}

	public Parser(String filename, vmStack s) {
		
		vms = s;

		try {
			size  = toIntExact((new File(filename)).length() / 4);
			
			try {
				input = new DataInputStream(new FileInputStream(filename));
			}

			catch (FileNotFoundException e) {
				System.out.println(filename + ": file not found");
				System.exit(-1);
			}
			
			read();
		} 
		
		catch (ArithmeticException e) {
			System.out.println(filename + ": file was too large");
			System.exit(-1);
		}
	}

	// read data from file into heap
	public void read() {

		//check for magic header  
		try {
			if (input.readInt() != 0xfeedbeef) {
				System.out.println("magic header missing...0xFEEDBEEF");
				System.exit(-1);
			}
		}

		catch (IOException e) {
			System.out.println("No data to read from file");
			System.exit(-1);
		}

		//read through the input file and add bytecode to heap
		for (int i = 0; i < size - 1; i++) {
			
			//read instruction  
			try {
				vms.addInstr(Integer.reverseBytes(input.readInt()));
			}

			catch (IOException e) {
				System.out.println("File reader encountered unexpected EOF");
			}
		}
	}

	// parse bytecode into instruction 
	public Instruction parse(int instr) {
		
		int parameter;
		int opcode;
		int subcode;

		opcode = instr >>> 28;

		//switch on the opcode, which is the final 4 bits of the raw value
		switch (opcode) {

			case 0: // Exit, Swap, Inpt, Nop
			subcode = (instr >>> 24) & 0xf;

			switch (subcode) {
				
				case 0: // Exit
				parameter = instr & 0xff;
				return new Exit(parameter);
				
				case 1: // Swap
				return new Swap();
				
				case 2: // Inpt
				return new Inpt();
				
				case 3: // Nop
				return new Nop();
				
				default:
				System.out.println("fatal error: unknown subcode " + subcode);
				System.exit(-1);
			}

			case 1: // Pop
			return new Pop();
			
			case 2: // Add, Sub, Mul, Div, Rem, And, Or, Xor			
			subcode = (instr >>> 24) & 0xf;
			
			switch (subcode) {
			
				case 0: // Add
				return new Add();
			
				case 1: // Sub
				return new Sub();
			
				case 2: // Mul
				return new Mul();
		
				case 3: // Div
				return new Div();
			
				case 4: // Rem
				return new Rem();
				
				case 5: // And
				return new And();
			
				case 6: // Or
				return new Or();
			
				case 7: // Xor
				return new Xor();
				
				default:
				System.out.println("fatal error: unknown subcode " + subcode);
				System.exit(-1);
			}

			case 3: // Neg, Not
			subcode = (instr >>> 24) & 0xf;
			
			switch (subcode) {
				
				case 0: // Neg
				return new Neg();

				case 1: // Not
				return new Not();
				
				default:
				System.out.println("fatal error: unknown subcode " + subcode);
				System.exit(-1);
			}
			
			case 7: // Goto
			parameter = (instr << 4) >>> 4;
			return new Goto(parameter);

			//check to create conditionals
			case 8:
			parameter = (instr << 8) >>> 8;
			subcode = (instr >>> 24) & 0xf;

			switch(subcode) {
				
				case 0: //Ifeq
				return new Ifeq(parameter);

				case 1: //Ifne
				return new Ifne(parameter);

				case 2: //Iflt
				return new Iflt(parameter);

				case 3: //Ifgt
				return new Ifgt(parameter);

				case 4: //Ifle
				return new Ifle(parameter);

				case 5: //Ifge
				return new Ifge(parameter);
				
				default:
				System.out.println("fatal error: unknown subcode " + subcode);
				System.exit(-1);
			}

			//now check unary conditionals
			case 9:
			parameter = (instr << 8) >>> 8;
			subcode = (instr >>> 24) & 0xf;

			switch(subcode) {
				
				case 0: //Ifez
				return new Ifez(parameter);

				case 1: //Ifnz
				return new Ifnz(parameter);

				case 2: //Ifmi
				return new Ifmi(parameter);

				case 3: //Ifpl
				return new Ifpl(parameter);
				
				default:
				System.out.println("fatal error: unknown subcode " + subcode);
				System.exit(-1);
			}

			case 12: //Dup
			parameter = (instr << 4) >>> 4;
			return new Dup(parameter);

			case 13: //Print
			return new Print();

			case 14: //Dump
			return new Dump();

			case 15: //Push
			parameter = (instr << 4) >> 4;
			return new Push(parameter);

			//default case. does not create instr that is not recognized.
			default:
			System.out.println("fatal error: unknown instr " + opcode);
			System.exit(-1);
		}

		return new Exit(420); // because java doesn't understand default switch cases
	}
}	
