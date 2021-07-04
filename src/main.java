import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		/**
		 *
		 */
		String lock = "1";
		String command = "";
		VfsInterface t;
		system sys;
		Parse parse = new Parse();
		System.out.println("->1-New System 2-Old one");

lock = new Scanner(System.in).nextLine();
		// saved state
		System.out.println("Type your commands");
		switch (lock) {
		case "1":
			while (!command.equals("exit")) {
				command = new Scanner(System.in).nextLine();
				String str[] = command.trim().split(" ");
				if (str[0].equals("createSystem")) {
					if (str[1].equals("con"))
						t = new Contiguous();
					else if (str[1].equals("index"))
						t = new Indexed();
					else
						break;
					sys = new system(Integer.parseInt(str[2]), t);
					System.out.println("->System has been initialized");
					Main(sys);
					t.write(sys, "/Users/pratik.barjatiya/Downloads/Virtual-File-System-Simulator-master/metadata/.metadata.vsf");
				} else if (!str[0].equals("exit"))
					System.out.println("->Wrong command try again");
			}
			break;

		case "2":
			// create System
			try {
				sys = parse.read("/Users/pratik.barjatiya/Downloads/Virtual-File-System-Simulator-master/metadata/.metadata.vsf");
				System.out
						.println("->System has been loaded enter your commands");
				Main(sys);
			} catch (Exception e) {
				System.out
				.println("->There is no system to load!");
			}
			break;
		}
	}

	public static void Main(system sys) {
		/**
		 *
		 */
		String command = "";
		while (!command.equals("exit")) {
			command = new Scanner(System.in).nextLine();
			String str[] = command.trim().split(" ");
			switch (str[0]) {
			case "createFile":
				sys.createFile(str[1], Integer.parseInt(str[2]));
				System.out.println("->file has been created");
				break;
			case "createFolder":
				sys.createFolder(str[1]);
				System.out.println("->folder has been created");
				break;
			case "deleteFolder":
				sys.deleteFolder(str[1]);
				System.out.println("->folder has been deleted");
				break;
			case "deleteFile":
				sys.deleteFile(str[1]);
				System.out.println("->file has been deleted");
				break;
			case "status":
				sys.DisplayDiskStatus();
				break;
			case "structure":
				sys.DisplayDiskStructure();
				break;
			case "exit":
				break;
			default:
				System.out.println("->No match command");
			}
		}
	}
}
