public class Div implements Instruction
{
    public void run(vmStack vms){
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
    }
}
