import java.util.*;

public class vmStack {
	
	private int	  sp;
	private int[] stack;
	
	public vmStack() {
		sp = -1;
		stack = new int[1024];
	}

	public void push(int val) {
		if (sp < 1023) {
			stack[++sp] = val;
		} else {		   
			System.out.println("fatal error: tried to push onto full stack.");
			System.exit(-1);
		}
	}

	public int pop() {
		if (sp >= 0) {
			 return stack[sp--];
		} else {
			System.out.println("fatal error: tried to pop empty stack.");
			System.exit(-1);
		}

		return 420; // to make the compiler shut up
	}

	public int getStackPointer() {
		return sp;
	}
}
