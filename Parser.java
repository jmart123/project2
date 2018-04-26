import java.util.*;
import java.io.*;
import static java.lang.Math.toIntExact;

public class Parser {
	
	private List<Instruction> instr; // callable instruction list (final result)
	private List<Integer>	  raw;	 // raw integer bytecode
	private DataInputStream	  input; // binary input stream
	private int				  size;  // equals filesize/4 (NUM_INSTRUCTIONS)

	public Parser() {
		instr = new ArrayList<Instruction> ();
		size  = 0;
	}

	public Parser(String filename) {
		
		try {
			size  = toIntExact((new File(filename)).length() / 4);
			instr = new ArrayList<Instruction> ();
			raw	  = new ArrayList<Integer> ();
			
			System.out.println("filesize: " + size);

			try {
				input = new DataInputStream(new FileInputStream(filename));
			}

			catch (FileNotFoundException e) {
				System.out.println(filename + ": file not found");
				System.exit(-1);
			}
			
			read();
			parse();
		} 
		
		catch (ArithmeticException e) {
			System.out.println(filename + ": file was too large");
			System.exit(-1);
		}
	}

	public List<Instruction> getInstructions() {
		return instr;
	}

	// method to manually add instruction to program list
	public void add(Instruction i) {
		instr.add(i);
		size++;
	}

	// read data from file into raw
	public void read() {

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

		for (int i = 0; i < size - 1; i++) {
			
			try {
				raw.add(input.readInt());
			}

			catch (IOException e) {
				System.out.println("File reader encountered unexpected EOF");
			}
		}
	}

	// parse raw data into instruction list
	public void parse() {
		
		int instruction;
		int parameter;
		int opcode;
		int subcode;

		for (int i = 0; i < size - 1; i++) {

			instruction = raw.get(i);
			opcode = instruction >> 28;

			switch (opcode) {

				case 0: // Exit, Swap, Inpt, Nop
				subcode = (instruction >> 24) & 0xf;

				switch (subcode) {
					
					case 0: // Exit
					parameter = instruction & 0xff;
					instr.add(new Exit(parameter));
					break;
					
					case 1: // Swap
					instr.add(new Swap());
					break;
					
					case 2: // Inpt
					instr.add(new Inpt());
					break;
					
					case 3: // Nop
					instr.add(new Nop());
					break;	
				}

				break;
				
				case 1: // Pop
				instr.add(new Pop());
				break;
				
				case 2: // Add, Sub, Mul, Div, Rem, And, Or, Xor			
				subcode = (instruction >> 24) & 0xf;
				
				switch (subcode) {
				
					case 0: // Add
					instr.add(new Add());
					break;
				
					case 1: // Sub
					instr.add(new Sub());
					break;
				
					case 2: // Mul
					instr.add(new Mul());
					break;
			
					case 3: // Div
					instr.add(new Div());
					break;
				
					case 4: // Rem
					instr.add(new Rem());
					break;
					
					case 5: // And
					instr.add(new And());
					break;
				
					case 6: // Or
					instr.add(new Or());
					break;
				
					case 7: // Xor
					instr.add(new Xor());
					break;
				}

				break;
				
				case 3: // Neg, Not
				subcode = (instruction >> 24) & 0xf;
				
				switch (subcode) {
					
					case 0: // Neg
					instr.add(new Neg());
					break;

					case 1: // Not
					instr.add(new Not());
					break;
				}
				
				break;
				
				case 7: // Goto
				parameter = (instruction << 4) >> 4;
				instr.add(new Goto(parameter));
				break;

				case 8:
				parameter = (instruction << 8) >> 8;
				subcode = (instruction >> 24) & 0xf;

				switch(subcode) {
					
					case 0:
					instr.add(new Ifeq(parameter));
					break;

					case 1:
					instr.add(new Ifne(parameter));
					break;

					case 2:
					instr.add(new Iflt(parameter));
					break;

					case 3:
					instr.add(new Ifgt(parameter));
					break;

					case 4:
					instr.add(new Ifle(parameter));
					break;

					case 5:
					instr.add(new Ifge(parameter));
					break;
				}

				break;
				
				case 9:
				parameter = (instruction << 8) >> 8;
				subcode = (instruction >> 24) & 0xf;

				switch(subcode) {
					
					case 0:
					instr.add(new Ifez(parameter));

					case 1:
					instr.add(new Ifnz(parameter));

					case 2:
					instr.add(new Ifmi(parameter));

					case 3:
					instr.add(new Ifpl(parameter));
				}

				break;

				case 12:
				parameter = (instruction << 4) >> 4;
				instr.add(new Dup(parameter));
				break;

				case 13:
				instr.add(new Print());
				break;

				case 14:
				instr.add(new Dump());
				break;

				case 15:
				parameter = (instruction << 4) >> 4;
				instr.add(new Push(parameter));
				break;
			}
		}
	}
}	
