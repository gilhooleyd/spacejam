package game;

public class List<Thing> 
{
	public Thing data;
	public List<Thing> previous, next;
	
	public List()
	{
	}
	
	public List(Thing data, List<Thing> previous)
	{
		this.data = data;
		this.previous = previous;
	}
	
	public void remove()
	{
		previous.next = next;
		if (next != null)
			next.previous = previous;
	}
	
	// creates a wrapper list for the object
	// and adds it immediately after this list
	public void add(Thing a)
	{
		List<Thing> wrapper = new List<Thing>(a, null);
		wrapper.previous = this;
		wrapper.next = next;
		if (next != null)
			next.previous = wrapper;
		
		next = wrapper;
	}
	
}
