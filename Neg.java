public class Neg implements Instruction
{
    public void run(vmStack vms, int programCounter){
        int a;

        //get value
        a = vms.pop();

        //negate popped value
        a *= -1;

        //push negative value
        vms.push(a);
    }
}
