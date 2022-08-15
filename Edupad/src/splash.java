/**
	Splash Class is used here for creating splash screen ie. opening screen of the application
*/
package notepad;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import notepad.Notepad;
public class Splash extends JFrame
{
	//declaring container and components
	JFrame frame;
	JLabel showLabel;
	JLabel author;
	JProgressBar progress;
	JLabel picture;
	Notepad n;
	int i=0;
	public Splash()//constructor
	{
		n = new Notepad();
		setSize(400,300);
		setUndecorated(true);
		setLocation(400,300);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setLayout(null);
		getContentPane().setBackground(new Color(32,178,170));//light sea green color
		
		picture = new JLabel();
		picture.setIcon(new ImageIcon(this.getClass().getResource("resources/Untitled.png")));
		add(picture);
		picture.setBounds(50,120,350,130);
		//creating progress bar
		progress = new JProgressBar(0,100);
		progress.setBounds(0,0,400,40);
		progress.setStringPainted(true);
		progress.setValue(0);
		add(progress);
		//creating label for showing message
		showLabel = new JLabel("Welcome to the Edupad");
		add(showLabel);
		showLabel.setBounds(20,50,350,50);
		showLabel.setFont(new Font("Times New Roman",Font.ITALIC,25));
		showLabel.setForeground(Color.WHITE);
		showLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//label for showing my name
		author = new JLabel("Designed By: BCA III Year");
		add(author);
		author.setBounds(220,250,180,50);
		author.setFont(new Font("Times New Roman",Font.PLAIN,14));
		author.setForeground(Color.WHITE);
		
	}
	public void iterate()
	{
		for(i=0;i<=100;i=i+10)
		{    
			progress.setValue(i);    
			try
			{
				Thread.sleep(100);
			}catch(Exception e){System.out.println(e);}    
		}
		if(i>100)
		{
			dispose();
			n.show();
		}
	}
}