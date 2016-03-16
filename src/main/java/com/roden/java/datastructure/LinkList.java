package com.roden.java.datastructure;

public class LinkList<T> {
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
	
	private Node header;
	private Node tail;
	private int size;
	
	public LinkList(){
		header=null;
		tail=null;		
	}
	public LinkList(T element){
		header=new Node(element,null);
		tail=header;	
		size++;
	}
	public int lenght(){
		return size;
	}
	public T get(int index){
		return getNodeByIndex(index).data;
	}
	private Node getNodeByIndex(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("索引越界");		 
		}
		Node current=header;
		for(int i=0;i<size&&current!=null;i++,current=current.next){
			if(i==index)
				return current;
		}
		return null;
	}
	public int locate(T element){
		Node current=header;
		for(int i=0;i<size&&current!=null;i++,current=current.next){
			if(current.data.equals(element))
				return i;
		}
		return -1;
	}
	public void insert(T element,int index){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("索引越界");		 
		}
		if(header==null){
			add(element);
		}else{
			if(index==0){
				addHeader(element);
			}else{
				Node prev=getNodeByIndex(index-1);
				prev.next=new Node(element,prev.next);
				size++;
			}
		}
	}
	public void add(T element){
		if(header==null){
			header=new Node(element,null);
			tail=header;
		}else{
			Node newNode=new Node(element,null);
			tail.next=newNode;
			tail=newNode;
		}
		size++;
	}
	public void addHeader(T element){
		header=new Node(element,null);
		if(tail==null){
			tail=header;
		}
		size++;
	}
	public T delete(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("索引越界");		 
		}
		Node del=null;
		if(index==0){
			del=header;
			header=header.next;
		}else{
			Node prev=getNodeByIndex(index-1);
			del=prev.next;
			prev.next=del.next;
			del.next=null;
		}
		size--;
		return del.data;
	}
	public T romove(){
		return delete(size-1);
	}
	public boolean empty(){
		return size==0;
	}
	public void clear(){
		header=null;
		tail=null;
		size=0;
		
	}
	public String toString(){
		if(empty()){
			return "[]";
		}else{
			StringBuilder sb=new StringBuilder("[");
			for(Node current=header;current!=null;current=current.next){
				sb.append(current.data.toString()+", ");
			}
			int len=sb.length();
			return sb.delete(len-2, len).append("]").toString();
		}
	}
	public static void main(String[] args) {
		LinkList<String> list=new LinkList<String>();
		list.insert("aaaa", 0);
		list.add("bbbb");
		list.add("cccc");
		list.insert("dddd",1);
		System.out.println(list);
		list.delete(2);
		System.out.println(list);
		System.out.println(list.locate("cccc"));
		System.out.println(list.get(2));

	}

}
