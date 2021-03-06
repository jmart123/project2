public class Xor implements Instruction
{
    public int run(vmStack vms, int programCounter){
        int a;
        int b;
        int xor;

        //get values from the stack
        a = vms.pop();
        b = vms.pop();

        //xor a and b
        xor = a ^ b;

        //push the value of xor to the stack
        vms.push(xor);

		return programCounter;
    }
}
