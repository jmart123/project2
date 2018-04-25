import java.util.*;
import java.io.*;
import static java.lang.Math.toIntExact;

public class Parser {
	
	private List<Instruction> instr;
	private List<Integer>	  raw;
	private DataInputStream	  input;

	public Parser() {
		instr = new ArrayList<Instruction> ();
	}

	public Parser(String filename) {
		
		try {
			int filesize = toIntExact((new File(filename)).length() / 4);
			instr = new ArrayList<Instruction> (filesize);
			
			try {
				input = new DataInputStream(new FileInputStream(filename));
			}

			catch (FileNotFoundException e) {
				System.out.println(filename + ": file not found");
			}
			
			read();
			parse();
		} 
		
		catch (ArithmeticException e) {
			System.out.println("file was too large");
		}
	}

	public void read() {
		// read data from file into raw
	}

	public void parse() {
		// parse raw data into instruction list
	}
}	
