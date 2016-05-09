package Classes;

public class ProjectDriver {
	
	public static void main(String[] args){
		FileReader reader = new FileReader();
		ProjectData data = reader.readFile();
		Dijkstra program = new Dijkstra(data);
		
		program.printTable();
		program.algorithm(data.sourceNode);
	}

}
