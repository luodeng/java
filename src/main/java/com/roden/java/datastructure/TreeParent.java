package com.roden.java.datastructure;

import java.util.ArrayList;
import java.util.List;

public class TreeParent<E> {
	public static class Node<T>{
		T data;
		int parent;
		public Node(){
			
		}
		public Node(T data){
			this.data=data;
		}
		public Node(T data,int parent){
			this.data=data;
			this.parent=parent;
		}
		public String toString(){
			return "TreeParent$Node[data="+data+",parent="+parent+"]";
		}
	}
	private final int DEFAULT_TREE_SIZE=100;
	private int treeSize=0;
	private Node<E>[] nodes;
	private int nodeNums;
	public TreeParent(E data){
		treeSize=DEFAULT_TREE_SIZE;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data,-1);
		nodeNums++;
	}
	public TreeParent(E data,int treeSize){
		this.treeSize=treeSize;
		nodes=new Node[treeSize];
		nodes[0]=new Node<E>(data,-1);
		nodeNums++;
	}
	public void addNode(E data,Node parent){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==null){
				nodes[i]=new Node(data,pos(parent));
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
	public Node<E> parent(Node node){
		return nodes[node.parent];
	}
	public List<Node<E>> children(Node parent){
		List<Node<E>> list=new ArrayList<Node<E>>();
		for(int i=0;i<treeSize;i++){
			if(nodes[i]!=null&&nodes[i].parent==pos(parent)){
				list.add(nodes[i]);
			}
		}
		return list;
	}
	public int deep(){
		int max=0;
		for(int i=0;i<treeSize&&nodes[i]!=null;i++){
			int def=1;
			int m=nodes[i].parent;
			while(m!=-1&&nodes[m]!=null){
				m=nodes[m].parent;
				def++;
			}
			if(max<def)
				max=def;
		}
		return max;
	}
	public int pos(Node node){
		for(int i=0;i<treeSize;i++){
			if(nodes[i]==node)
				return i;
		}
		return -1;
	}
	public static void main(String[] args) {
		TreeParent<String> tp=new TreeParent<String>("root");
		TreeParent.Node root=tp.root();
		System.out.println(root);
		tp.addNode("节点1", root);
		System.out.println(tp.deep());
		tp.addNode("节点2", root);
		List<TreeParent.Node<String>> nodes=tp.children(root);
		System.out.println(nodes.get(0));
		tp.addNode("节点3", nodes.get(0));
		System.out.println(tp.deep());
	}

}
