import java.util.ArrayList;

public class system {
	int sizeKB, allSpace = 0;
	VfsInterface vfsInterface;
	Directory root;
	ArrayList<Boolean> state;
	public int getAllSpace() {
		return allSpace;
	}

	public void setAllSpace(int allSpace) {
		this.allSpace = allSpace;
	}

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}

	public ArrayList<Boolean> getState() {
		return state;
	}

	public void setState(ArrayList<Boolean> state) {
		this.state = state;
	}

	public ArrayList<Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(ArrayList<Space> spaces) {
		this.spaces = spaces;
	}

	ArrayList<Space> spaces;

	public system(int sizeKB, VfsInterface vfsInterface) {
		this.sizeKB = sizeKB;
		this.vfsInterface = vfsInterface;
		state = new ArrayList<>(sizeKB);
		for (int i = 0; i < sizeKB; i++) {
			state.add(false);
		}
		root = new Directory("root");
		spaces = new ArrayList<>();
		spaces.add(new Space(0, sizeKB - 1, false));
	}

	public boolean createFile(String path, int sizeKB) {
		if (sizeKB >= this.sizeKB - this.allSpace)
			return false;
		String[] paths = path.trim().split("\\\\");
		Directory iter;
		iter = getDire(root, paths, 0);
		if (iter != null) {
			if (vfsInterface.createFile(iter, paths[paths.length - 1], sizeKB, spaces,
					state)) {
				this.allSpace += sizeKB;
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean createFolder(String path) {
		String[] paths = path.trim().split("\\\\");
		Directory iter;
		iter = getDire(root, paths, 0);
		if (iter != null)
			return vfsInterface.createDir(iter, paths[paths.length - 1]);
		else
			return false;
	}

	public boolean deleteFile(String path) {
		String[] paths = path.trim().split("\\\\");
		Directory iter;
		iter = getDire(root, paths, 0);
		if (iter != null) {
			int fileSize = vfsInterface.deleteFile(iter, paths[paths.length - 1],
					spaces, state);
			if (fileSize != 0) {
				this.allSpace -= fileSize;
				return true;
			}
			return false;
		} else
			return false;
	}

	public boolean deleteFolder(String path) {
		String[] paths = path.trim().split("\\\\");
		Directory iter;
		iter = getDire(root, paths, 0);
		if (iter != null) {
			int filesSize = vfsInterface.deleteDir(iter, spaces, state);
			if (filesSize != 0) {
				this.allSpace -= filesSize;
				return true;
			}
			return false;
		} else
			return false;
	}

	public Directory getDire(Directory dir, String[] path, int level) {
		if(path.length==1)
			return dir;
		for (Directory temp : dir.subDirectory) {
			if (path[level+1].equals(temp.name) && level != path.length - 2) {
				return getDire(temp, path, level + 1);
			}
		}
		if (path[level].equals(dir.name) && level == path.length - 2) {
			return dir;
		}
		return null;
	}

	public void DisplayDiskStructure() {
		root.printDirectoryStructure(0);
	}

	public void DisplayDiskStatus() {
		System.out.println("Empty space:\n" + (sizeKB - allSpace) + " KB");
		System.out.println("Allocated space:\n" + allSpace + " KB");
		System.out.println("Empty Blocks in the Disk:");
		for (int i = 0; i < state.size(); i++) {
			System.out.println("Block:[" + i + "] is "
					+ (state.get(i) ? "Allocated" : "Empty"));
		}
	}
}
