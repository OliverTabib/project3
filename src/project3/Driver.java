package project3;

public class Driver {
	public static void main(String[] args) {
		MDeque<Integer> d = new MDeque<Integer>();
		int[] i = {1,2,3,4};
		for(int x:i) {
			d.pushBack(x);
			
		}
		System.out.println(d.peekMiddle());
	}

}
