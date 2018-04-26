// Driver file
import java.util.*;
class Processor {

	  public static void main(String[] args) {
        List<Instruction> instList;
        vmStack vms;

		    Parser p = new Parser(args[0]);
        instList = p.getInstructions();
        vms = new vmStack(); 

		    for(int i = 0; i < instList.size(); i++){
		        instList.get(i).run(vms, i);    
        }
	  }

}
