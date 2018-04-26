import java.util.*;

class Processor {
	
	public static void main(String[] args) {
		
		Parser p = new Parser(args[0]);
		List<Instruction> instr = p.getInstructions();
		List<Integer> raw = p.getRaw();
    
    vmStack vms = new vmStack();

		System.out.println("instruction list size: " + instr.size());		
		System.out.println("raw values list size: " + raw.size());

//		System.out.println(instr.get(0) + ": " + raw.get(0));

		for (int i = 0; i < instr.size(); i++) {
			//System.out.println(instr.get(i));
			instr.get(i).run(vms, i);
		}
	}
}
