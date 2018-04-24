public class Mul implements Instruction
{
	  int a;
	  int b;
	  int prod;

	  public void run(vmStack vms){
		    //retrieve values from stack
		    a = vms.pop();
		    b = vms.pop();

		    //multiply values
		    prod = a * b;

		    //push product
		    vms.push(prod);
	  }
}
