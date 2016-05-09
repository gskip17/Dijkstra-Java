package Classes;

import java.util.ArrayList;
import java.util.Arrays;

public class ProjectData {
	
	public int[][] initialArray;
	public int initRows;
	public int initColumns;
	public int sourceNode;
	public ArrayList<Integer> nodes;
	public int[][] table;
	public ArrayList<Integer[]> data;
	
	public ProjectData(){
		nodes = new ArrayList<Integer>();
		data = new ArrayList<Integer[]>();
	}
	
	public void setInitialArray(int[][] array){
		this.initialArray = array;
		this.setNodeArray();
		this.table = new int[nodes.size()][nodes.size()];
		this.transformDataToArrayList();
		this.printArrayList();
	}
	
	public void setSourceNode(int src){
		this.sourceNode = src;
	}
	
	public void setInitRowsColumns(int rows, int columns){

		this.initRows = rows;
		this.initColumns = columns;
	}
	
	public void setNodeArray(){
		for(int i = 0; i < initRows; i++){
			Integer currentNode = initialArray[i][0];
			if(i == 0){
				nodes.add(currentNode);
			}
			else if(nodes.contains(currentNode)){
				continue;
			} else {
				nodes.add(currentNode);
			}
		}
		for(int i = 0; i < initRows; i++){
			Integer currentNode = initialArray[i][1];
			if(nodes.contains(currentNode)){
				continue;
			} else {
				nodes.add(currentNode);
			}
		}
	}
	
	public void printNodes(){
		System.out.print("\nNodes: ");
		for(int node : nodes){
			System.out.print(node + " ");
		}
		System.out.print("\n");
	}
	
	public int getNumNodes(){
		int count = 0;
		for(@SuppressWarnings("unused") Integer node : nodes){
			count = count + 1;
		}
		return count;
	}
	
	public void transformDataToArrayList(){
		for(int i = 0; i < initRows; i++){
			Integer a = initialArray[i][0];
			Integer b = initialArray[i][1];
			Integer c = initialArray[i][2];
			
			Integer[] row = {a,b,c};
			data.add(row);
		}
	}
	
	public void printArrayList(){
		System.out.println("\nPrinting data ArrayList structure...");
		for(Integer[] node : data){
			System.out.println(Arrays.toString(node));
		}
	}
	
}
