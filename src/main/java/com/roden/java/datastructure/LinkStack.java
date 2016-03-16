package com.roden.java.datastructure;



public class LinkStack<T> {
	private class Node{
		private T data;
		private Node next;
		public Node(){
			
		}
		public Node(T data,Node next){
			this.data=data;
			this.next=next;
		}
	}
	private Node top;
	private int size;
	public LinkStack(){
		top=null;
	}
	public LinkStack(T element){
		top=new Node(element,null);
		size++;
	}
	public int lenght(){
		return size;
	}
	public  void push(T element){
		top=new Node(element,top);
		size++;
	}
	public T pop(){
		Node oldTop=top;
		top=top.next;
		oldTop.next=null;
		size--;
		return oldTop.data;
	}
	public T peek(){
		return top.data;
	}
	public boolean empty(){
		return size==0;
	}
	public void clear(){
		top=null;
		size=0;
	}
	public String toString(){
		if(empty()){
			return "[]";
		}else{
			StringBuilder sb=new StringBuilder("[");
			for(Node current=top;current!=null;current=current.next){
				sb.append(current.data.toString()+", ");
			}
			int len=sb.length();
			return sb.delete(len-2, len).append("]").toString();
		}
	}
	public static void main(String[] args) {
		SequenceStack<String> stack=new SequenceStack<String>();
		stack.push("aaaa");
		stack.push("bbbb");
		stack.push("cccc");
		stack.push("dddd");
		System.out.println(stack);		
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);		

	}

}
