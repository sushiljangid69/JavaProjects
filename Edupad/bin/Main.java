/**
	* Main Class for our Edupad Application this is the trigger point of Edupad application
*/
package notepad;
import notepad.Splash;		//notepad is the package in which our Splash class is presented
//creating main class
public class Main
{
	public static void main(String[] args)
	{
		/**
			*firstly the object of Splash class is created so that it can show the splash screen of application
		*/
		Splash obj=new Splash();
		/**
			method object.setVisible(boolean value);
			here object is obj which is referring to Splash class
			@param boolean true | boolean false
		*/
		obj.setVisible(true);
		/**
			method object.iterate(void);
			iterate() method is responsible for showing prgress in splash screen and further proceed to Main Notepad Class's Window
		*/
		obj.iterate();
	}
}