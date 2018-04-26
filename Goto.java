public class Goto implements Instruction
{

    private int parameter;

    Goto(int param){
        parameter = param;
    }

    public void run(vmStack vms, int programCounter){
        //vms.programCounter += parameter;
        //need to increment program counter by PC+parameter(PC relative offset)

        System.out.println("Parameter is " + parameter);
        
        programCounter += parameter;

    }
}
