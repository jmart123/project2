import java.util.*;

public class vmStack {
	
	private int	  sp;
	private int[] stack;
	
	public vmStack() {
		sp = -1;
		stack = new int[1024];
	}

	public void push(int val) {
		if (sp < 1024) 
			stack[sp++] = val;
		else		   
			throw new Exception("Pushed beyond stack capacity");
	}

	public int pop() {
		if (sp >= 0) 
			return stack[sp--];
		else
			throw new Exception("Tried to pop from an empty stack");
	}

	public int getStackPointer() {
		return sp;
	}
}
