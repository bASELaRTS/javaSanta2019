package Engine;

public class LinkedList {
	private LinkedListObject m_first;
	private LinkedListObject m_last;
	
	public LinkedList() {
		this.setFirst(null);
		this.setLast(null);
	}
	
	public void add(LinkedListObject llo) {
		if (this.getFirst()==null) {
			this.setFirst(llo);
			this.setLast(llo);
			
			this.getFirst().setNext(null);
			this.getFirst().setPrevious(null);
			
			this.getLast().setNext(null);
			this.getLast().setPrevious(null);
		} else {
			llo.setPrevious(this.getLast());
			llo.setNext(null);			
			this.getLast().setNext(llo);
			this.setLast(llo);
		}
	}
	
	public void remove(LinkedListObject llo) {
		LinkedListObject next;
		LinkedListObject prev;
		
		prev = llo.getPrevious();
		next = llo.getNext();
		
		if (prev==null) {
			this.setFirst(next);
		} else {
			prev.setNext(next);
		}
		
		if (next==null) {
			this.setLast(prev);
		} else {
			next.setPrevious(prev);
		}
		
		llo.setObject(null);
		llo = null;
	}
	
	public void clear() {
		LinkedListObject llo,next;
		llo = this.getFirst();
		while(llo!=null) {
			next = llo.getNext();
			this.remove(llo);
			llo = next;
		}
	}
	
	public int count() {
		int i;
		LinkedListObject llo;
		
		i = 0;
		llo = this.getFirst();
		while (llo!=null) {
			i++;
			llo = llo.getNext();
		}
		
		return i;
	}

	private void setFirst(LinkedListObject llo) {this.m_first=llo;}
	public LinkedListObject getFirst() {return this.m_first;}
	
	private void setLast(LinkedListObject llo) {this.m_last=llo;}
	private LinkedListObject getLast() {return this.m_last;}
}
