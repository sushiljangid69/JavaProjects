/**
	Main Class for our Notepad Application
*/
package notepad;
import notepad.Splash;		//notepad is the package in which our Splash class is presented
//creating main class
public class Main
{
	public static void main(String[] args)
	{
		Splash obj=new Splash();
		obj.setVisible(true);
		obj.iterate();
	}
}