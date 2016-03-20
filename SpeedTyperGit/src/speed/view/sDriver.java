package speed.view;

import speed.debug.Debug;


public class sDriver {

	public static void main(String[] args) {
		Debug.flag = true;
		Debug.print("AddressBookDriver/Main()");
		sFrame f = new sFrame();
		f.initGUI();
	}
}