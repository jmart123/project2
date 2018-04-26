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

	public int peek(int idx) {
		if (sp < 1024 && sp >= 0) {
			return stack[idx];
		} else {
			System.out.println("fatal error: tried to peek outside of stack bounds.");
			System.exit(-1);
		}	

		return 420; // blaze it
	}
	
	public void dump() {
		for (int i = 0; i < stack.length; i++) {
			System.out.println(stack[i]);
		}
	}

	public int getStackPointer() {
		return sp;
	}
}
