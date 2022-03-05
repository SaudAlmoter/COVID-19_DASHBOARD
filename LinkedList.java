public class LinkedList<T> implements List<T> {

	class Node<T> {
		public T data;
		public Node<T> next;

		public Node () {
			data = null;
			next = null;
		}//constrature

		public Node (T e) {
			data = e;
			next = null;
		}//constratureT
	}//Node
	private Node<T> head;
	private Node<T> current;

	public LinkedList () {
		head = current = null;
	}//constructureD

	@Override
	public boolean empty (){
		// TODO Auto-generated method stub
		return head == null;
	}//empty

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}//NeverFull

	@Override
	public int size() {
		// TODO Auto-generated method stub
		Node<T> tmp = head;
		if (empty())
			return 0;
		else {
			int C = 0;
			while(tmp != null) {
				tmp = tmp.next; 
				C++;
			}//while
			return C;
		}//else
	}//size

	@Override
	public void findFirst() {
		// TODO Auto-generated method stub
		current = head;
	}//findFirst

	@Override
	public void findNext() {
		// TODO Auto-generated method stub
		current = current.next;
	}//findNext

	@Override
	public boolean last() {
		// TODO Auto-generated method stub
		return current.next == null;
	}//last

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}//retrieve

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		current.data = e;
	}//update

	@Override
	public void insert(T e) {
		// TODO Auto-generated method stub
		Node<T> tmp;
		if (empty())
			current = head = new Node<T> (e);
		else {
			tmp = current.next;
			current.next = new Node<T> (e);
			current = current.next;
			current.next = tmp;
		}//else
	}//insert

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		if (current == head) 
			head = head.next;
		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}//else
		if (current.next == null)
			current = head;
		else
			current = current.next;
	}//remove
}//Linkedlist
