import java.util.HashSet;
import java.util.Iterator;

public class Intersection {
	
	public static void main(String[] args) {

		HashSet<Integer> A = new HashSet<>();
		HashSet<Integer> B = new HashSet<>();
		HashSet<Integer> C = new HashSet<>();
	
		A.add(1);
		A.add(2);
		A.add(3);
		A.add(4);
		
		B.add(2);
		B.add(3);
		B.add(4);
		B.add(5);
		
		C.add(3);
		C.add(4);
		C.add(5);
		C.add(6);
		System.out.println(intersection(A, B, C));
	}
	
	public static HashSet<Integer>intersection(HashSet<Integer> A,HashSet<Integer> B, HashSet<Integer> C){
		
		HashSet<Integer> res=new HashSet<Integer>();
	
		for (Iterator<Integer> iterator = A.iterator(); iterator.hasNext();) {
			Integer a = (Integer) iterator.next();
			if(B.contains(a) && C.contains(a))
				res.add(a);
		}
		return res;	
	}
}
