public class Div implements Instruction
{
    public int run(vmStack vms, int programCounter){
        int a;
        int b;
        int q; //quotient

        //get values from stack
        a = vms.pop();
        b = vms.pop();

        //divide values
        q = a / b;

        //push quotient to the stack
        vms.push(q);

		return programCounter;
    }
}
