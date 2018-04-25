import java.util.*;
import java.io.*;
import static java.lang.Math.toIntExact;

public class ParserT {
	
	private List<Instruction> instr; // callable instruction list (final result)
	private List<Integer>	  raw;	 // raw integer bytecode
	private DataInputStream	  input; // binary input stream
	private int				  size;  // equals filesize/4 (NUM_INSTRUCTIONS)

	public ParserT() {
		instr = new ArrayList<Instruction> ();
		size  = 0;
	}

	public ParserT(String filename) {
		
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
			System.out.println("File reader encountered unexpected EOF");
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

		for (int i = 0; i < size; i++) {
			//instruction = raw.get(i);
			//opcode = instruction; 
		}
	}
}	
