package Classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Dijkstra {
	
	private ProjectData data;
	private int sourceNode;
	private int[][] initialSet;
	private ArrayList<Integer[]> table;
	private ArrayList<Integer> nodes;
	private ArrayList<Integer[]> file;
	//evaluatedNodes follow structure Source, Cose, Previous Hop.
	private ArrayList<Integer[]> evaluatedNodes;
	
	public Dijkstra(ProjectData data){
		this.data = data;
		this.sourceNode = data.sourceNode;
		this.initialSet = data.initialArray;
		table = new ArrayList<Integer[]>();
		this.nodes = data.nodes;
		this.file = data.data;
		this.evaluatedNodes = new ArrayList<Integer[]>();
		this.initializeSet();
	}
	//use the Array List organized set for the new array.
	public void initializeSet(){
		int initRows = data.initRows;
		
		for(Integer node : nodes){
			Integer[] valueHolder = new Integer[nodes.size()];
			table.add(valueHolder);
		}

		//this.printTable();
		// Now we step through and initialize the correct values for the table.
		for(Integer node : nodes){
			System.out.println("\nGetting node " + node);
			Integer[] row = table.get(node);
			// Set the row's inverse position to 0, because a node's distance from itself is 0
			row[node] = 0;
			for(Integer[] line : file){
				
				if(node == line[0] && line[0] != null){
					
					Integer vectorTo = line[1];
					Integer cost = line[2];
					
					row[vectorTo] = cost;
					
					// Set the inverse path as well.
					Integer[] pointNode = table.get(vectorTo);
					pointNode[node] = cost;

					System.out.println("\nSetting vector " + node + " to " + line[1] + " to " + line[2]);
				}  else if (node != line[0] && row[line[0]] == null){
					row[line[0]] = 999;
				}
			}
			
			// Anything left as null set to 999
			for(int i = 0; i < row.length; i++){
				if(row[i] == null){
					row[i] = 999;
				}
			}
		}
		
		
	}
	
	public void algorithm(int sourceNode){
		Integer[] node = table.get(sourceNode);
		this.evaluateNode(node, null);
		this.massageList();
		this.printEvalList();
	}
	
	public void printTable(){
		System.out.println("\nPrinting current set");
		this.data.printNodes();
		int count = 0;
		for(Integer[] node : table){
			System.out.print("Node: " + count + " - ");
			System.out.println(Arrays.toString(node));
			count = count + 1;
		}
	}
	
	public void evaluateNode(Integer[] node, Integer[] previousNode){
		//Add what we already know into our table.
		int currentNode = table.indexOf(node);
		
		for(Integer edge : node){
			Integer edgeIndex = Arrays.asList(node).indexOf(edge);
			Integer[] write = {currentNode, edge, edgeIndex, currentNode};
			evaluatedNodes.add(write);
		}

		for(Integer vector : node){
			
			Integer vectorIndex = Arrays.asList(node).indexOf(vector);
			Integer connectingNode[] = table.get(vectorIndex);
			for(Integer path : connectingNode){
				Integer pathIndex = Arrays.asList(connectingNode).indexOf(path);
				if(vector + path < node[pathIndex]){
					Integer cost = path + vector;
					Integer[] update = {currentNode, cost, pathIndex, vectorIndex};
					this.testAgainstEvals(update);
				}
				Integer[] nextNode = table.get(pathIndex);
				for(Integer trail : nextNode){
					Integer trailIndex = Arrays.asList(connectingNode).indexOf(path);
					if(path + trail + vector < node[pathIndex]){
						Integer cost = path + vector + trail;
						Integer[] update = {currentNode, cost, trailIndex, vectorIndex};
						this.testAgainstEvals(update);
					}
				}
			}
		
		}
	}
	
	
	private void testAgainstEvals(Integer[] test){
		if(this.evaluatedNodes.size() == 0){
			this.evaluatedNodes.add(test);
		}
		boolean addCheck = false;
		boolean replaceCheck = false;
		Integer markedSetIndex = null;
		for(Integer[] entry : evaluatedNodes){
			if(test[0] == entry[0] && test[2] == entry[2]){
				if(test[1] < entry[1]){
					addCheck = true;
					replaceCheck = true;
					markedSetIndex = evaluatedNodes.indexOf(entry);
				} else if(test[1] >= entry[1]){
					return;
				}
			} else {
				addCheck = true;
			}
		}
		if(addCheck && !replaceCheck){
			evaluatedNodes.add(test);
		}
		if (replaceCheck && markedSetIndex != null){
			evaluatedNodes.set(markedSetIndex, test);
		}
	}
	
	public void printEvalList(){
		System.out.println("\nPrinting the finished evaluated paths for node " + this.data.sourceNode);
		for(Integer[] entry : evaluatedNodes){
			System.out.println("\nFrom source node " + entry[0] + " to node " + entry[2] + " costs " + entry[1] + " with first hop from node " + entry[3]);
		}
	}
	
	public void massageList(){
		ArrayList<Integer[]> garbage = new ArrayList<Integer[]>();
		for(Integer[] entry : evaluatedNodes){
			if(entry[1] == 999){
				Integer[] duplicate = entry;
				garbage.add(duplicate);
			}
		}
		for(Integer[] trash : garbage){
			evaluatedNodes.remove(trash);
		}
	}
	

}
