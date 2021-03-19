
public class BasicObject {
	public String name = ""; 

}

class UseCase extends BasicObject{
	
}

class ClassObject extends BasicObject{
	
}

class Line {
	BasicObject left;
	BasicObject right;

	public Line(BasicObject left, BasicObject right) {
		this.left = left;
		this.right = right;
	}
	
	public BasicObject contains(BasicObject o) {
		if (left == o)
			return right;
		else if (right ==o)
			return left;
		else
			return null;
	}
}
