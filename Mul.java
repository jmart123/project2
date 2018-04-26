public class Mul implements Instruction
{
	  int a;
	  int b;
	  int prod;

	  public int run(vmStack vms, int programCounter){
		    //retrieve values from stack
		    a = vms.pop();
		    b = vms.pop();

		    //multiply values
		    prod = a * b;

		    //push product
		    vms.push(prod);
			
			return programCounter;
	 }		
}
