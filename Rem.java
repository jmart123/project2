public class Rem implements Instruction
{
        public void run(vmStack vms, int programCounter){
            int a;
            int b;
            int rem;

            //get values from stack
            a = vms.pop();
            b = vms.pop();

            //find remainder (modulus)
            rem = a % b;

            //push remainder to the stack
            vms.push(rem);
        }
}
