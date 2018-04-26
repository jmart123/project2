// Driver file

class Processor {
  private List<Integer> instList;
  private vmStack vms;

	public static void main(String[] args) {
		Parser p = new Parser(args[0]);
		
		for () {
			System.out.println(i);
		}

    instList = p.getInstructions();
    vms = new vmStack(); 


		for(int i = 0; i < instList.size(); i++){
		  instList.get(i).run(vms, i);    
    }
	}
}
