import java.util.ArrayList;


public class VirtualFile {
	String name;
	ArrayList<Integer> allocatedBlocks;
	public VirtualFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getAllocatedBlocks() {
		return allocatedBlocks;
	}
	public void setAllocatedBlocks(ArrayList<Integer> allocatedBlocks) {
		this.allocatedBlocks = allocatedBlocks;
	}
	boolean deleted;
	public VirtualFile(String name, ArrayList<Integer> allocatedBlocks) {
		this.name = name;
		this.allocatedBlocks = allocatedBlocks;
		deleted=false;
	}
}
