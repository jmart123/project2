import java.util.*;

class Processor {

	  public static void main(String[] args) {

		    Parser p = new Parser(args[0]);
		    List<Instruction> instr = p.getInstructions();
		    List<Integer> raw = p.getRaw();

        vmStack vms = new vmStack();

		    System.out.println("instruction list size: " + instr.size());		
		    System.out.println("raw values list size: " + raw.size());


		    for (int i = 0; i < instr.size(); i++) {
		        if(instr.get(i) instanceof Goto){
		            instr.get(i).run(vms, i);
//                i = ((Goto)instr.get(i)).getNC() - 1;		            
		        }
            else{
		            instr.get(i).run(vms, i);
            }
		    }
	  }
}
