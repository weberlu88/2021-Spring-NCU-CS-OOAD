package model;

/** relational class for group:basicObject's M:N linking. **/
public class GroupRelation {
	
	private Group group;
	private BasicObject object;
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public BasicObject getObject() {
		return object;
	}
	public void setObject(BasicObject object) {
		this.object = object;
	}

}
