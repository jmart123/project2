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
			size  = toIntExact((new File(filename)).length() / 4) - 1;
			instr = new ArrayList<Instruction> ((size / 4) - 1);
			raw	  = new ArrayList<Integer> ((size / 4) - 1);
			
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

	// method to manually add instruction to program list
	public void add(Instruction i) {
		instr.add(i);
		size++;
	}

	// read data from file into raw
	public void read() {

		try {
			if (input.readInt() != 0xfeedbeef) {
				System.out.println("magic header missing...FEEDBEEF");
				System.exit(-1);
			}
		}

		catch (IOException e) {
			System.out.println("No data to read from file");
			System.exit(-1);
		}

		for (int i = 0; i < size / 4; i++) {
			
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
		int opcode;
		int subcode = 0;

		for (int i = 0; i < size; i++) {
			instruction = raw.get(i);
			opcode = instruction >> 28;

			switch (opcode) {
				case 1: opcode = 0; //Exit,Swap,Inpt,Nop
					subcode = (instruction >> 24) & 0xf;
					switch (subcode) {
						case1: subcode = 0; //Exit
						       instr.set(i, new Exit());
						       break;
						case2: subcode = 1; //Swap
						       instr.set(i, new Swap());
						       break;
						case3: subcode = 2; //Inpt
						       instr.set(i, new Inpt());
						       break;
						case4: subcode = 3; //Nop
						       instr.set(i, new Nop());
						       break;
					}
					break;
				case 2: opcode = 1; //Pop
					break;
				case 3: opcode = 2; //Add,Sub,Mul,Div,Rem,And,Or,Xor
					subcode = (instruction >> 24) & 0xf;
					switch (subcode) {
						case1: subcode = 0; //Add
						       instr.set(i, new Add());
						       break;
						case2: subcode = 1; //Sub
						       instr.set(i, new Sub());
						       break;
						case3: subcode = 2; //Mul
						       instr.set(i, new Mul());
						       break;
						case4: subcode = 3; //Div
						       instr.set(i, new Div());
						       break;
						case5: subcode = 4; //Rem
						       instr.set(i, new Rem());
						       break;
						case6: subcode = 5; //And
						       instr.set(i, new And());
						       break;
						case7: subcode = 6; //Or
						       instr.set(i, new Or());
						       break;
						case8: subcode = 7; //Xor
						       instr.set(i, new Xor());
						       break;
					}
					break;
				case 4: opcode = 3; //Neg,Not
					subcode = (instruction >> 24) & 0xf;
					switch (subcode) {
						case1: subcode = 0; //Neg
						       instr.set(i, new Neg());
						       break;
						case2: subcode = 1; //Not
						       instr.set(i, new Not());
						       break;
					}
					break;
				case 5: opcode = 7; //Goto
					instr.set(i, new Goto((Instruction << 4) >> 4));
					break;
			}
		}
	}
}	
