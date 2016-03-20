package speed.debug;

public class Debug {

	public static boolean flag;

	public static void print(String msg) {
		if (flag) {
			System.out.println(msg);
		}
	}
}