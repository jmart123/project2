import java.util.*;

public class vmStack {
	
	private int[] stack; // stack
	private int	  sp;	 // stack pointer
	public  int	  ic;    // instruction counter
	public  int	  pc;	 // program counter

	// constructor
	public vmStack() {
		pc = 1023;
		ic = 1023;
		sp = -1;
		stack = new int[1024];
	}

	// add instruction (used inside Parser.read()
	public void addInstr(int instr) {
		stack[ic--] = instr;
	}

	// push
	public void push(int val) {
		if (sp < ic - 1) {
			stack[++sp] = val;
		} else {		   
			System.out.println("fatal error: tried to push onto full stack.");
			System.exit(-1);
		}
	}

	// pop and return value
	public int pop() {
		if (sp >= 0) {
			 return stack[sp--];
		} else {
			System.out.println("fatal error: tried to pop empty stack.");
			System.exit(-1);
		}

		return 420; // to make the compiler shut up
	}
	
	// peek a stack value
	public int peek(int idx) {
		if (idx < 1024 && idx >= 0) {
			return stack[idx];
		} else {
			System.out.println("fatal error: tried to peek outside of memory.");
			System.exit(-1);
		}	

		return 420; // blaze it
	}
	
	// print the stack to console
	public void dump() {
		
		System.out.println("Stack dump:");

		for (int i = sp; i >= 0; i--) {
			System.out.println(stack[i]);
		}

		System.out.println();
	}

	// get stack pointer
	public int getStackPointer() {
		return sp;
	}
}
