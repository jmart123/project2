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

  //returns instruction list
	public List<Instruction> getInstructions() {
		return instr;
	}

  //returns raw value of instruction
	public List<Integer> getRaw() {
		return raw;
	}

  //returns number of instructions
	public int getSize() {
		return size - 1;
	}

	// method to manually add instruction to program list
	public void add(Instruction i) {
		instr.add(i);
		size++;
	}

	// read data from file into raw
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

    //read through the input file and create appropriate instructions
		for (int i = 0; i < size - 1; i++) {
			
			//read instruction  
			try {
				raw.add(Integer.reverseBytes(input.readInt()));
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

    //iterate through raw list and create instructions
		for (int i = 0; i < size - 1; i++) {

			instruction = raw.get(i);
			opcode = instruction >>> 28;

      //switch on the opcode, which is the final 4 bits of the raw value
			switch (opcode) {

				case 0: // Exit, Swap, Inpt, Nop
				subcode = (instruction >>> 24) & 0xf;

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
				subcode = (instruction >>> 24) & 0xf;
				
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
				subcode = (instruction >>> 24) & 0xf;
				
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
				parameter = (instruction << 4) >>> 4;
				instr.add(new Goto(parameter));
				break;

        //check to create conditionals
				case 8:
				parameter = (instruction << 8) >>> 8;
				subcode = (instruction >>> 24) & 0xf;

				switch(subcode) {
					
					case 0: //Ifeq
					instr.add(new Ifeq(parameter));
					break;

					case 1: //Ifne
					instr.add(new Ifne(parameter));
					break;

					case 2: //Iflt
					instr.add(new Iflt(parameter));
					break;

					case 3: //Ifgt
					instr.add(new Ifgt(parameter));
					break;

					case 4: //Ifle
					instr.add(new Ifle(parameter));
					break;

					case 5: //Ifge
					instr.add(new Ifge(parameter));
					break;
				}

				break;
				
				//now check unary conditionals
				case 9:
				parameter = (instruction << 8) >>> 8;
				subcode = (instruction >>> 24) & 0xf;

				switch(subcode) {
					
					case 0: //Ifez
					instr.add(new Ifez(parameter));
					break;

					case 1: //Ifnz
					instr.add(new Ifnz(parameter));
					break;

					case 2: //Ifmi
					instr.add(new Ifmi(parameter));
					break;

					case 3: //Ifpl
					instr.add(new Ifpl(parameter));
					break;
				}

				break;

				case 12: //Dup
				parameter = (instruction << 4) >>> 4;
				instr.add(new Dup(parameter));
				break;

				case 13: //Print
				instr.add(new Print());
				break;

				case 14: //Dump
				instr.add(new Dump());
				break;

				case 15: //Push
				parameter = (instruction << 4) >> 4;
				instr.add(new Push(parameter));
				break;

        //default case. does not create instruction that is not recognized.
				default:
                System.out.println("fatal error: unknown instruction " + opcode);
                System.exit(-1);
				break;
			}
		}
	}
}	
