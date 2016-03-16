package com.roden.java.datastructure;

import java.util.Arrays;

public class SequenceList<T> {
	private int DEFAULT_SIZE=16;
	private int capacity;
	private Object[] elementdata;
	private int size=0;
	public SequenceList(){
		capacity=DEFAULT_SIZE;
		elementdata=new Object[capacity];
	}
	public SequenceList(T element){
		this();
		elementdata[0]=element;
		size++;
	}
	public SequenceList(T element,int initsize){
		capacity=1;
		while(capacity<initsize){
			capacity<<=1;
		}
		elementdata=new Object[capacity];
		elementdata[0]=element;
		size++;
	}
	public int length(){
		return size;
	}
	public T get(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("索引越界");			
		}
		return (T)elementdata[index];
	}
	public int locate(T element){
		for(int i=0;i<size;i++){
			if(elementdata[i].equals(element))
				return i;
		}
		return -1;
	}
	public void insert(T element,int index){
		if(index<0||index>size){
			throw new IndexOutOfBoundsException("索引越界");			
		}	
		ensureCapacity(size+1);
		System.arraycopy(elementdata,index,elementdata,index+1,size-index);
		elementdata[index]=element;
		size++;
	}
	public void add(T element){
		insert(element,size);
	}
	public void ensureCapacity(int size){
		if(size>capacity){
			while(capacity<size){
				capacity<<=1;
			}
			elementdata=Arrays.copyOf(elementdata, capacity);
		}
	}
	public T delete(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("索引越界");			
		}
		T oldValue=(T)elementdata[index];
		int nummMoved=size-index-1;
		if(nummMoved>0){
			System.arraycopy(elementdata, index+1, elementdata, index, nummMoved);
		}
		elementdata[--size]=null;		
		return oldValue;
	}
	public T remove(){
		return delete(size-1);
	}
	public void clear(){
		Arrays.fill(elementdata, null);
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder("[");
		for(int i=0;i<size;i++){
			sb.append(elementdata[i].toString()+", ");			
		}
		int len=sb.length();
		return sb.delete(len-2, len).append("]").toString();
	}
	public static void main(String arg[]){
		SequenceList<String> list=new SequenceList<String>();
		list.add("aaaa");
		list.add("bbbbb");
		list.add("accccaa");
		list.insert("ddddd", 1);
		System.out.println(list);
		list.delete(2);
		System.out.println(list);
		System.out.println(list.locate("accccaa"));
	}
}
