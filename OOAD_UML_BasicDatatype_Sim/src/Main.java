import java.util.ArrayList;

public class Main {

	static ArrayList<Line> links = new ArrayList<>();
	
	public static ArrayList<BasicObject> findPairs(BasicObject o) {
		ArrayList<BasicObject> pairs = new ArrayList<BasicObject>();
		
		for(Line i:links) {
			if (i.contains(o) != null)
				pairs.add(i.contains(o));
		}
		
		return pairs;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello there");
		BasicObject usecase1 = new UseCase();
		BasicObject usecase2 = new UseCase();
		BasicObject usecase3 = new UseCase();
		BasicObject usecase4 = new UseCase();
		BasicObject class1 = new ClassObject();
		BasicObject class2 = new ClassObject();
		
		links.add(new Line(usecase1, usecase2));
		links.add(new Line(usecase1, usecase3));
		links.add(new Line(usecase1, usecase4));
		links.add(new Line(class2, usecase2));
		
		ArrayList<BasicObject> res = findPairs(usecase1);
		System.out.println(res);
		
		res = findPairs(usecase2);
		System.out.println(res);
	}

}
