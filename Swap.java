public class Swap implements Instruction
{
<<<<<<< HEAD
		public void run(vmStack vms, int programCounter){
				//case with empty stack
=======
	public void run(vmStack vms, int pc){
		
		//case with empty stack
        if(vms.getStackPointer() == -1) return;
>>>>>>> 0ac06a1fd5261340b51bfabf1bb319c5776eee3a

		//need case with only 1 value on the stack
		if(vms.getStackPointer() == 0) vms.push(0);
        
        int a = vms.pop();
        int b = vms.pop();

<<<<<<< HEAD
				//need case with only 1 value on the stack
			 	if(vms.getStackPointer == 0) vms.push(0);

				//case where there are two or more values on the stack
				int tmp;
				int a;
				int b;

        //pop values
				a = vms.pop();
				b = vms.pop();

        //push in opposite order
				vms.push(a);
				vms.push(b);
		}
=======
		vms.push(a);
		vms.push(b);
	}
>>>>>>> 0ac06a1fd5261340b51bfabf1bb319c5776eee3a
}
