package com.roden.java.datastructure;

import java.util.ArrayList;
import java.util.List;

public class TreeChild<E> {
	private static class SonNode{
		private int pos;
		private SonNode next;
		public SonNode(int pos,SonNode next){
			this.pos=pos;
			this.next=next;
		}
	}
	public static class Node<T>{
		T data;
		SonNode first;
		public Node(T data){
			this.data=data;
			this.first=null;			
		}
		public String toString(){
			if(first!=null){
				return "Tree$Node[data="+data+",first="+first.pos+"]";
			}else{
				return "Tree$Node[data="+data+",first=-1]";
			}
		}
	}
	private final int DEFAULT_TREE_SIZE=100;
	private int treeSize=0;
	private Node<E>[] nodes;
	private int nodeNums;
	public TreeChild(E data){
		treeSize=DEFAULT_TREE_SIZE;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data);
		nodeNums++;
	}
	public TreeChild(E data,int treeSize){
		this.treeSize=treeSize;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data);
		nodeNums++;
	}
	public void addNode(E data,Node parent){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==null){
				nodes[i]=new Node(data);
				if(parent.first==null){
					parent.first=new SonNode(i,null);
				}else{
					SonNode next=parent.first;
					while(next.next!=null){
						next=next.next;
					}
					next.next=new SonNode(i,null);
				}
				nodeNums++;
				return;
			}
		}
		throw new RuntimeException("树已满");
	}
	public boolean empty(){
		return nodes[0]==null;
	}
	public Node<E> root(){
		return nodes[0];
	}
	public List<Node<E>> children(Node parent){
		 List<Node<E>> list=new ArrayList<>();
		 SonNode next=parent.first;
		 while(next!=null){
			 list.add(nodes[next.pos]);	
			 next=next.next;
		 }
		 return list;
	}
	public Node<E> child(Node parent,int index){		
		SonNode next=parent.first;
		for(int i=0;next!=null;i++){
			if(index==i){
				return nodes[next.pos];
			}
			next=next.next;
		}
		 return null;
	}
	public int deep(){
		return deep(root());
	}
	private int deep(Node node){
		if(node.first==null){
			return 1;
		}else{
			int max=0;
			SonNode next=node.first;
			while(next!=null){
				int tmp=deep(nodes[next.pos]);
				if(tmp>max)
					max=tmp;
				next=next.next;
			}
			return max+1;
		}
		
	}
	public int pos(Node node){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==node){
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		TreeChild<String> tp=new TreeChild<String>("root");
		TreeChild.Node root=tp.root();
		System.out.println(root);
		tp.addNode("节点1", root);		
		tp.addNode("节点2", root);
		tp.addNode("节点3", root);
		System.out.println(root);
		System.out.println(tp.deep());
		List<TreeChild.Node<String>> nodes=tp.children(root);
		System.out.println(nodes.get(0));
		tp.addNode("节点4", nodes.get(0));
		System.out.println(tp.deep());
	}

}
