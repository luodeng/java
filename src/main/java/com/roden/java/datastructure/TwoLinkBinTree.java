package com.roden.java.datastructure;

public class TwoLinkBinTree<E> {
	public static class TreeNode{
		Object data;
		TreeNode left;
		TreeNode right;
		public TreeNode(){
			
		}
		public TreeNode(Object data){
			this.data=data;
		}
		public TreeNode(Object data,TreeNode left,TreeNode right){
			this.data=data;
			this.left=left;
			this.right=right;
		}
	}
	private TreeNode root;
	public TwoLinkBinTree(){
		this.root=new TreeNode();
	}
	public TwoLinkBinTree(E data){
		this.root=new TreeNode(data);
	}
	public TreeNode addNode(TreeNode parent,E data,boolean isLeft){
		TreeNode newNode=new TreeNode(data);
		if(isLeft){
			parent.left=newNode;
		}else{
			parent.right=newNode;
		}
		return newNode;
	}
	public boolean empty(){
		return root.data==null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
