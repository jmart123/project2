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
		int parameter;
		int opcode;

		for (int i = 0; i < size; i++) {

			instruction = raw.get(i);
			opcode = instruction >> 28;

			switch (opcode) {
				
				case 8:

				parameter = (instruction << 8) >> 8;

				switch((instruction >> 24) & 0xf) {
					
					case 0:
					instr.set(i, new Ifeq(parameter));
					
					case 1:
					instr.set(i, new Ifne(parameter));
					
					case 2:
					instr.set(i, new Iflt(parameter));
					
					case 3:
					instr.set(i, new Ifgt(parameter));
					
					case 4:
					instr.set(i, new Ifle(parameter));
					
					case 5:
					instr.set(i, new Ifge(parameter));
				}
				
				case 9:

				parameter = (instruction << 8) >> 8;

				switch((instruction >> 24) & 0xf) {
					
					case 0:
					instr.set(i, new Ifez(parameter));

					case 1:
					instr.set(i, new Ifnz(parameter));

					case 2:
					instr.set(i, new Ifmi(parameter));

					case 3:
					instr.set(i, new Ifpl(parameter));
				}

				case 12:
				
				parameter = (instruction << 4) >> 4;
				instr.set(i, new Dup(parameter));

				case 13:
				
				instr.set(i, new Print());

				case 14:
				
				instr.set(i, new Dump());

				case 15:
				
				parameter = (instruction << 4) >> 4;
				instr.set(i, new Push(parameter));
			}
		}
	}
}	
