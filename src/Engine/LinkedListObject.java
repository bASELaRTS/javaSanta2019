package Engine;

public class LinkedListObject {
	private LinkedListObject m_previous;
	private LinkedListObject m_next;
	private Object m_object;
	
	public LinkedListObject() {
		this.m_previous = null;
		this.m_next = null;
		this.m_object = null;
	}
	
	public LinkedListObject(Object o) {
		this.m_previous = null;
		this.m_next = null;
		this.m_object = o;
	}
	
	public void setObject(Object o) {this.m_object=o;}
	public Object getObject() {return this.m_object;}
	
	public void setPrevious(LinkedListObject llo) {this.m_previous=llo;}
	public LinkedListObject getPrevious() {return this.m_previous;}
	
	public void setNext(LinkedListObject llo) {this.m_next=llo;}
	public LinkedListObject getNext() {return this.m_next;}
	
}
