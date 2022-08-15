/**
this is the project made by Sushil Jangid and his friends
project => notepad in java
@AUTHOR SUSHIL JANGID
this is edupad program
BCA Final Year
team work done by
1:Sushil Jangid
2:Rajaram Prajapat
3:Ajay Singh
*/
package notepad;	//our application package
//importing all AWT classes needed
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;

//importing all AWT Event, Listeners and Adapters needed 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
// importing AWT other Classes
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
//importing java.io package classes that are required
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
//importing javax.swing package's needed classes
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import javax.swing.JFileChooser;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
//importing javax.swing.event package's needed classes
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
//importing Swing's other classes
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

//importing java.net package classes needed
import java.net.URI;

import java.awt.Dialog.ModalityType;
import java.util.Date;
import java.text.DateFormat;
import javax.swing.text.Element;

public class Notepad implements ActionListener
{
	/**
		Design part includes the main visible frame which is a JFrame 
		it includes a menu bar which includes File	Edit	Style	Help menues
	*/
	JFrame f;									//frame for Notepad
	JMenuBar mb;								//menu bar that will appear at the top with file,edit like option
	JTabbedPane tp;								//this is tabbed pane for documents
	/**
		File Menu provides the main functions like to open a new file or existing file, save a new file
		or save the existing file(Save As), print the file content and also include a exit button which 
		exits the application.
		File Menu includes some menu items:
		1. New and Open New Window
		2. Open
		3. Save
		4. Save As
		5. Print
		6. Exit
	*/
	JMenu file,edit,style,view,help;					//options for menu bar
	JMenuItem New,newWindow,open,save,saveas,exit,print;	//file menu items
	/**
		Edit menu provides the text based features like cut, copy and paste; along with them it also provides
		undo, redo and select all feature which are benificial for text editing
		Edit Menu includes some menu items:
		1. Undo
		2. Redo
		3. Cut
		4. Copy
		5. Paste
		6. Select All
	*/
	JMenuItem undo,redo,cut,copy,paste,selectAll,dateTime;//edit menu items
	/**
		Style menu provide us the facility to decorate the text and the page background according to our
		best experience this feature also enable us the to increase or decrease the font size and change its
		looking font style
		Style menu includes some menu items:
		1. Font
		2. Font Color
		3. Themes
	*/
	JMenuItem pagecolor,fontcolor,font;		//style menu items
	JCheckBoxMenuItem wordwrap;						//check box menu item for word wrapping
	/**
		view menu provides option to show or hide the status bar
	*/
	JMenu zoom;											//view menu item which is also acting as a menu
	JCheckBoxMenuItem showStatusBar,showLineNumbers;					//view menu items
	JMenuItem zoomin,zoomout,defaultzoom;			//zoom menu items
	JMenuItem find,search;									//view menu item

	/**
		Help menu provides us the help facility such as manual guide and feedback
		Help menu includes some menu items:
		1. Manual Guide
		2. About Us
		3. Feedback
	*/
	JMenuItem aboutus,feedback,manualguide;		//help menu items
	/**
		this is the main text area in used in the notepad application to write our desired content in
		text format
	*/
	JTextArea ta; 								//text area
	/**
		Status bar enables us to check how many words, lines or characters we have written
	*/
	JLabel statusBar;							//for showing status bar
	/**
		A popup menu is very useful when we right click on the text area then it enables us to use 
		Edit menu options
		(popup menu also includes same features like in Edit button)
	*/
	JPopupMenu popup;							//pop up for right click on text area
	JMenuItem undo1,redo1,cut1,copy1,paste1,selectAll1;		//this will work same as menu bar options but they are for popup menu or context menu when right clicked
	
	private static JTextArea lines;					//this is for showing line numbers in text area
	private JScrollPane jsp;						//this is scrollpane for text area
	JTextField searchText;
	
	//toolbar options
	JButton toolbar_help,toolbar_screenShot,toolbar_font,toolbar_find,toolbar_undo,toolbar_redo,toolbar_paste,toolbar_copy,toolbar_cut,toolbar_saveas,toolbar_save,toolbar_open,toolbar_new;
	//toolbar for notepad which gives quick access to different menu items
	JToolBar toolbar;
	
	/////////////////////////////////////////Operational Part//////////////////////////////////////
	UndoManager undoManager;					//for undo and redo operations
	Border border;								//for border of font option
	StringBuffer filePath = new StringBuffer("C:\\Users\\");//we can add default file path here
	StringBuffer fileName = new StringBuffer("Untitled");	//initial file name
	String default_name = "Untitled";						//this is also initial file name used in save option
	String applicationName="Edupad";						//my application name
	//below the fontname will take all the available font options from its environment
	String fontname[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	//fontstyle is container for all possible font styles 
	String fontstyle[] = {"Regular","Bold","Italic","Bold Italic","Upper Case","Lower Case"};
	//fontsizes is an array that contains all possible font size
	String fontsizes[] = {"2","4","6","8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
	int styl = Font.PLAIN;			//default font style
	StringBuffer styl1 = new StringBuffer("Regular");
	StringBuffer pre_font= new StringBuffer("Arial");
	int pre_size=16;
	int i,j;
	int pre_style=Font.PLAIN;
	int size = 16;					//default font size
	StringBuffer sb = new StringBuffer("Arial");	//default font family
	char c = ' ';					//this variable is too important to know whether the file should be asked for save or not
	int characters=0;
	int keypress=0;
	int cancel=0;
	
	//constructor
	public Notepad()
	{
		//for giving it the look according to system
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		///////////////////////////////////////////Design Part///////////////////////////////////
		f=new JFrame();
		
		//creating menu bar
		mb = new JMenuBar();
		
		//creating tabbed pane
		tp = new JTabbedPane();
		
		//creating popup menu
		popup=new JPopupMenu();
		
		//creating checkbox
		
		//creating menu
		file = new JMenu("File");
		edit = new JMenu("Edit");
		style = new JMenu("Format");
		view = new JMenu("View");
		help = new JMenu("Help");
		
		//file menu items
		New = new JMenuItem("New");
		New.setIcon(new ImageIcon(this.getClass().getResource("resources/new.png")));
		
		newWindow = new JMenuItem("New Window");
		newWindow.setIcon(new ImageIcon(this.getClass().getResource("resources/newWindow.png")));
	
		open = new JMenuItem("Open");
		open.setIcon(new ImageIcon(this.getClass().getResource("resources/open.png")));

		save = new JMenuItem("Save");
		save.setIcon(new ImageIcon(this.getClass().getResource("resources/save.png")));

		saveas = new JMenuItem("Save As");
		saveas.setIcon(new ImageIcon(this.getClass().getResource("resources/saveas.png")));
		
		print = new JMenuItem("Print");
		print.setIcon(new ImageIcon(this.getClass().getResource("resources/print.png")));
		
		exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon(this.getClass().getResource("resources/exit.png")));


		//edit menu items
		undo = new JMenuItem("Undo");
		undo.setIcon(new ImageIcon(this.getClass().getResource("resources/undo.png")));
		
		undo1 = new JMenuItem("Undo");
		undo1.setIcon(new ImageIcon(this.getClass().getResource("resources/undo.png")));


		redo = new JMenuItem("Redo");
		redo.setIcon(new ImageIcon(this.getClass().getResource("resources/redo.png")));
		
		redo1 = new JMenuItem("Redo");
		redo1.setIcon(new ImageIcon(this.getClass().getResource("resources/redo.png")));

		cut = new JMenuItem("Cut");
		cut.setIcon(new ImageIcon(this.getClass().getResource("resources/cut.png")));
		
		cut1 = new JMenuItem("Cut");
		cut1.setIcon(new ImageIcon(this.getClass().getResource("resources/cut.png")));

		copy = new JMenuItem("Copy");
		copy.setIcon(new ImageIcon(this.getClass().getResource("resources/copy.png")));
		
		copy1 = new JMenuItem("Copy");
		copy1.setIcon(new ImageIcon(this.getClass().getResource("resources/copy.png")));

		paste = new JMenuItem("Paste");
		paste.setIcon(new ImageIcon(this.getClass().getResource("resources/paste.png")));
		
		paste1 = new JMenuItem("Paste");
		paste1.setIcon(new ImageIcon(this.getClass().getResource("resources/paste.png")));

		selectAll = new JMenuItem("SelectAll");
		selectAll.setIcon(new ImageIcon(this.getClass().getResource("resources/selectall.png")));
		
		selectAll1 = new JMenuItem("SelectAll");
		selectAll1.setIcon(new ImageIcon(this.getClass().getResource("resources/selectall.png")));
		
		dateTime = new JMenuItem("Date and Time");
		dateTime.setIcon(new ImageIcon(this.getClass().getResource("resources/dateTime.png")));

		//style menu items
		pagecolor = new JMenuItem("Page Color");
		pagecolor.setIcon(new ImageIcon(this.getClass().getResource("resources/pagecolor.png")));
		wordwrap = new JCheckBoxMenuItem("Word Wrap");
		fontcolor = new JMenuItem("Font Color");
		fontcolor.setIcon(new ImageIcon(this.getClass().getResource("resources/fontcolor.png")));
		font = new JMenuItem("Font");
		font.setIcon(new ImageIcon(this.getClass().getResource("resources/font.png")));
		
		//view menu items
		find = new JMenuItem("Find");
		find.setIcon(new ImageIcon(this.getClass().getResource("resources/find.png")));
		search = new JMenuItem("Google Search");
		search.setIcon(new ImageIcon(this.getClass().getResource("resources/search.png")));
		showStatusBar = new JCheckBoxMenuItem("Status Bar");
		showLineNumbers = new JCheckBoxMenuItem("Line Numbers");
		zoom = new JMenu("Zoom");
		zoom.setIcon(new ImageIcon(this.getClass().getResource("resources/zoom.png")));
			//zoom menu items
			zoomin = new JMenuItem("Zoom In");
			zoomin.setIcon(new ImageIcon(this.getClass().getResource("resources/zoomin.png")));
			zoomout = new JMenuItem("Zoom Out");
			zoomout.setIcon(new ImageIcon(this.getClass().getResource("resources/zoomout.png")));
			defaultzoom = new JMenuItem("Default Scale");
			defaultzoom.setIcon(new ImageIcon(this.getClass().getResource("resources/defaultzoom.png")));

		//help menu items
		aboutus = new JMenuItem("About Us");
		aboutus.setIcon(new ImageIcon(this.getClass().getResource("resources/aboutus.png")));
		feedback = new JMenuItem("Feedback");
		feedback.setIcon(new ImageIcon(this.getClass().getResource("resources/feedback.png")));
		manualguide = new JMenuItem("Manual Guide");
		manualguide.setIcon(new ImageIcon(this.getClass().getResource("resources/manualguide.png")));
		

		//adding menu items to file menu
		file.add(New);
		file.add(newWindow);
		file.add(open);
		file.addSeparator();	//------------------------------------------------------------
		file.add(save);
		file.add(saveas);
		file.addSeparator();	//------------------------------------------------------------
		file.add(print);
		file.addSeparator();	//------------------------------------------------------------
		file.add(exit);

		//adding menu items to edit menu
		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();	//------------------------------------------------------------
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();	//------------------------------------------------------------
		edit.add(selectAll);
		edit.add(dateTime);
		
		//adding popup menu to popup
		popup.add(undo1);
		popup.add(redo1);
		popup.addSeparator();	//------------------------------------------------------------
		popup.add(cut1);
		popup.add(copy1);
		popup.add(paste1);
		popup.addSeparator();	//------------------------------------------------------------
		popup.add(selectAll1);

		//adding menu items to style menu
		style.add(wordwrap);
		wordwrap.setSelected(true);
		style.addSeparator();	//------------------------------------------------------------
		style.add(font);
		style.add(fontcolor);
		style.addSeparator();	//------------------------------------------------------------
		style.add(pagecolor);
		
		//adding menu items to view menu
		view.add(zoom);
			//adding menu items to zoom
			zoom.add(zoomin);
			zoom.add(zoomout);
			zoom.add(defaultzoom);
		view.add(find);
		view.addSeparator();	//------------------------------------------------------------
		view.add(search);
		view.addSeparator();	//------------------------------------------------------------
		view.add(showStatusBar);
		showStatusBar.setSelected(true);
		view.add(showLineNumbers);
		showLineNumbers.setSelected(true);

		//adding menu items to help menu
		help.add(manualguide);
		help.add(aboutus);
		help.add(feedback);
		

		//adding menues to menu bar
		mb.add(file);
		mb.add(edit);
		mb.add(style);
		mb.add(view);
		mb.add(help);
	
		//textarea for notepad writing
		ta = new JTextArea(30,60);
		//setting the word wrap to the text area
		ta.setLineWrap(true);
		
		//main frame of notepad
		f.setTitle(c+""+fileName +" - "+ applicationName);
		//status bar for our edupad
		statusBar = new JLabel("Characters: 0||Words: 0||Line: 1||Column: 1",JLabel.CENTER);
		statusBar.setPreferredSize(new Dimension(35,35));
		statusBar.setFont(new Font("Arial",Font.PLAIN,16));
        f.add(statusBar,BorderLayout.SOUTH);
		
		//setting fonts for menu items
		Font menuFont = new Font(mb.getFont().getFontName(), mb.getFont().getStyle(), 16);
		file.setFont(menuFont);
		edit.setFont(menuFont);
		style.setFont(menuFont);
		view.setFont(menuFont);
		help.setFont(menuFont);
		//set JMenubar
        f.setJMenuBar(mb);
		
		//toolbar for notepad which gives quick access to different menu items
		toolbar=new JToolBar("Toolbar",JToolBar.HORIZONTAL);
		toolbar.setFloatable(false);		//this will stop dragging the toolbar
		Container container=f.getContentPane();
		container.add(toolbar,BorderLayout.NORTH);
		//toolbar options
		toolbar_new=new JButton(new ImageIcon(this.getClass().getResource("resources/new.png")));
        toolbar_new.setToolTipText("New (CTRL+N)");
        toolbar_new.addActionListener(this);

        toolbar_open = new JButton(new ImageIcon(this.getClass().getResource("resources/open.png")));
        toolbar_open.setToolTipText("Open (CTRL+O)");
        toolbar_open.addActionListener(this);

        toolbar_save = new JButton(new ImageIcon(this.getClass().getResource("resources/save.png")));
        toolbar_save.setToolTipText("Save (CTRL+S)");
        toolbar_save.addActionListener(this);

        toolbar_saveas = new JButton(new ImageIcon(this.getClass().getResource("resources/saveas.png")));
        toolbar_saveas.setToolTipText("Save As (CTRL+SHIFT+S)");
        toolbar_saveas.addActionListener(this);

        toolbar_cut = new JButton(new ImageIcon(this.getClass().getResource("resources/cut.png")));
        toolbar_cut.setToolTipText("Cut (CTRL+X)");
        toolbar_cut.addActionListener(this);

        toolbar_copy = new JButton(new ImageIcon(this.getClass().getResource("resources/copy.png")));
        toolbar_copy.setToolTipText("Copy (CTRL+C)");
        toolbar_copy.addActionListener(this);

        toolbar_paste = new JButton(new ImageIcon(this.getClass().getResource("resources/paste.png")));
        toolbar_paste.setToolTipText("Paste (CTRL+V)");
        toolbar_paste.addActionListener(this);
		
		toolbar_undo = new JButton(new ImageIcon(this.getClass().getResource("resources/undo.png")));
        toolbar_undo.setToolTipText("Undo (CTRL+Z)");
        toolbar_undo.addActionListener(this);
		
		toolbar_redo = new JButton(new ImageIcon(this.getClass().getResource("resources/redo.png")));
        toolbar_redo.setToolTipText("Redo (CTRL+Y)");
        toolbar_redo.addActionListener(this);
		
        toolbar_font = new JButton(new ImageIcon(this.getClass().getResource("resources/font.png")));
        toolbar_font.setToolTipText("Set Font ");
        toolbar_font.addActionListener(this);
		
		toolbar_find = new JButton(new ImageIcon(this.getClass().getResource("resources/find.png")));
        toolbar_find.setToolTipText("Find (CTRL+F)");
        toolbar_find.addActionListener(this);
	
        toolbar_help = new JButton(new ImageIcon(this.getClass().getResource("resources/help.png")));
        toolbar_help.setToolTipText("Help");
		toolbar_help.addActionListener(this);
		
		toolbar_screenShot = new JButton(new ImageIcon(this.getClass().getResource("resources/screenShot.png")));
        toolbar_screenShot.setToolTipText("Screen Shot");
		toolbar_screenShot.addActionListener(this);

        //adding toolbar buttons to _toolbar object
        toolbar.add(toolbar_new);
        toolbar.addSeparator(new Dimension(4,4));
        toolbar.add(toolbar_open);
        toolbar.addSeparator(new Dimension(4,4));
        toolbar.add(toolbar_save);
        toolbar.add(toolbar_saveas);
        toolbar.addSeparator(new Dimension(6,6));
        toolbar.add(toolbar_cut);
        toolbar.add(toolbar_copy);
        toolbar.add(toolbar_paste);
		toolbar.add(toolbar_undo);
		toolbar.add(toolbar_redo);
        toolbar.addSeparator(new Dimension(6, 6));
		toolbar.add(toolbar_find);
        toolbar.add(toolbar_font);
        toolbar.add(toolbar_screenShot);
        toolbar.add(toolbar_help);
		
		
		
		
		
		
		
	
		//scroll bar for edupad
		f.add(ta,BorderLayout.CENTER);
		
		f.setSize(350,350);
		f.pack();
		f.setLocation(150,50);
		//////////// for showin line numbers in text area/////////////////////////////////////////////
		jsp = new JScrollPane();
		lines = new JTextArea("1  ");
		lines.setFont(new Font("Arial",Font.PLAIN,16));
		lines.setBackground(Color.LIGHT_GRAY);
		lines.setForeground(Color.BLUE);
		lines.setEditable(false);
		//  Code to implement line numbers inside the JTextArea
		ta.getDocument().addDocumentListener(new DocumentListener()
		{
			public String getText()
			{
				int caretPosition = ta.getDocument().getLength();
				Element root = ta.getDocument().getDefaultRootElement();
				String text = "1  " + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++)
				{
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de)
			{
				lines.setText(getText()+"  ");
			}
			@Override
			public void insertUpdate(DocumentEvent de)
			{
				lines.setText(getText()+"  ");
			}
			@Override
			public void removeUpdate(DocumentEvent de)
			{
				lines.setText(getText()+"  ");
			}
		});
		jsp.getViewport().add(ta);
		jsp.setRowHeaderView(lines);
		f.add(jsp);
		
		/////////////////////////////////////////////////////
		
		//adding icon to frame
		ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/icon.png"));
		f.setIconImage(icon.getImage());
		
		////////////////////////////////////////////TextArea Operations///////////////////////////////
		ta.addMouseListener(new MouseAdapter()
		{
        	public void mouseReleased(MouseEvent me)
			{
            	showPopup(me); // showPopup() is our own user-defined method
        	}
			/*public void mouseClicked(MouseEvent mee)
			{
				removeHighlights(ta);
			}*/
    	}) ;
		ta.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent arg0)
			{
				int dot = arg0.getDot();
				int mark = arg0.getMark();
				if (dot == mark)
				{
					copy1.setEnabled(false);
					copy.setEnabled(false);
					cut1.setEnabled(false);
					cut.setEnabled(false);
					toolbar_cut.setEnabled(false);
					toolbar_copy.setEnabled(false);
				}
				else
				{
					cut1.setEnabled(true);
					cut.setEnabled(true);
					copy1.setEnabled(true);
					copy.setEnabled(true);
					toolbar_cut.setEnabled(true);
					toolbar_copy.setEnabled(true);

				}
				Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);	
				if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) 
				{
					
					paste.setEnabled(true);
					paste1.setEnabled(true);
					toolbar_paste.setEnabled(true);
				}
				else
				{
					paste.setEnabled(false);
					paste1.setEnabled(false);
					toolbar_paste.setEnabled(false);
				}
			}
    	});
		ta.getDocument().addUndoableEditListener(new UndoableEditListener() 
		{
          public void undoableEditHappened(UndoableEditEvent e) 
		  {
            undoManager.addEdit(e.getEdit());
            updateButtons();
          }
        });
		ta.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent event) 
			{
				if(event.isControlDown() || event.isAltDown())
				{
					keypress = 1;
					//System.out.println("Ctrl or alt pressed   "+event.getKeyCode());
				}
				if(keypress!=1)
				{
					if((event.getKeyCode()>=33 && event.getKeyCode()<=126) || event.getKeyCode()==8 || event.getKeyCode()==9 || event.getKeyCode()==13 || (event.getKeyCode()>=186 && event.getKeyCode()<=222))
					{
						//System.out.println("key pressed");
						c = '*';
						f.setTitle(c+""+fileName +" - "+ applicationName);
					}
				}
			}
		});
		ta.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent event) 
			{
			if(event.getKeyCode()== 17 || event.getKeyCode()== 18)
				{
					keypress = 0;
					//System.out.println("ctrl or alt released");
				}
			}
		});
		//this counts the line and columns in notepad
		ta.addCaretListener(new CaretListener()
		{
			public void caretUpdate(CaretEvent e)
			{
				//for detecting the selected text
				int lineNumber=0, column=0, pos=0;

				try
				{
					pos = ta.getCaretPosition();
					lineNumber = ta.getLineOfOffset(pos);
					column = pos-ta.getLineStartOffset(lineNumber);
				}catch(Exception excp){}
				if(ta.getText().length()==0)
				{
					lineNumber=0; column=0;
				}
				String text=ta.getText();
				String words[]=text.split("\\s");
				characters=text.length();
				if(characters!=0)
				{
					selectAll.setEnabled(true);
					selectAll1.setEnabled(true);
				}
				else
				{
					selectAll.setEnabled(false);
					selectAll1.setEnabled(false);
				}
				statusBar.setText("Characters:"+text.length()+"||Words:"+words.length+"||Line:"+(lineNumber+1)+"||Column:"+(column+1));
			}
		});
		ta.addMouseWheelListener(new MouseWheelListener()
		{
			public void mouseWheelMoved(MouseWheelEvent mwe)
			{
				if (mwe.isControlDown())
				{
					if (mwe.getWheelRotation() < 0)
					{
						//System.out.println("mouse wheel Up");
						zoom(2);	//it will zoom out 
					} 
					else
					{
						//System.out.println("mouse wheel Down");
						zoom(0);	//it will zoom in
					}
    			}
		
			}
		});
		
		/////////////////////////////////////////Operational Part//////////////////////////////////
		undoManager = new UndoManager();
		border = BorderFactory.createLineBorder(Color.GRAY, 2);
		//default size of font in text area
		ta.setFont(new Font(""+pre_font,styl,pre_size));
		
		//setting keystrokes
		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		style.setMnemonic(KeyEvent.VK_S);
		view.setMnemonic(KeyEvent.VK_V);
		help.setMnemonic(KeyEvent.VK_H);
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		dateTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
		zoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,ActionEvent.CTRL_MASK));
		zoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,ActionEvent.CTRL_MASK));
		defaultzoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0,ActionEvent.CTRL_MASK));
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		
		//disabling the buttons
		undo.setEnabled(false);
		undo1.setEnabled(false);
		redo.setEnabled(false);
		redo1.setEnabled(false);
		toolbar_undo.setEnabled(false);
		toolbar_redo.setEnabled(false);
		toolbar_copy.setEnabled(false);
		toolbar_cut.setEnabled(false);
		cut.setEnabled(false);
		cut1.setEnabled(false);
		copy.setEnabled(false);
		copy1.setEnabled(false);
		paste.setEnabled(false);
		paste1.setEnabled(false);
		toolbar_paste.setEnabled(false);
		selectAll.setEnabled(false);
		selectAll1.setEnabled(false);
		
		////////////////////////////////////////Adding Action Listeners////////////////////////////////
		//file menu items action listeners
		New.addActionListener(this);
		newWindow.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		print.addActionListener(this);

		//edit menu items action listeners
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		dateTime.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		
		cut1.addActionListener(this);
		copy1.addActionListener(this);
		paste1.addActionListener(this);
		selectAll1.addActionListener(this);
		undo1.addActionListener(this);
		redo1.addActionListener(this);

		//style menu items action listeners
		wordwrap.addActionListener(this);
		fontcolor.addActionListener(this);
		font.addActionListener(this);
		pagecolor.addActionListener(this);

		//view menu items action listeners
		find.addActionListener(this);
		search.addActionListener(this);
		showStatusBar.addActionListener(this);
		showLineNumbers.addActionListener(this);
		zoomin.addActionListener(this);
		zoomout.addActionListener(this);
		defaultzoom.addActionListener(this);
		
		//help menu items action listeners
		aboutus.addActionListener(this);
		manualguide.addActionListener(this);
		feedback.addActionListener(this);
		
		//////////////////////////////////////////Frame Action Listeners////////////////////////////////
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this will close the frame
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				exit_file();
			}
			public void windowOpened(WindowEvent e)
			{
				//when the window will open then the cursor focus should be on the text area
				ta.requestFocus();
			} 
		});
	} //constructor closed
	/*
	//the class MyHighlightPainter is for high lighting the finded text
	class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{
		public MyHighlightPainter(Color color)
		{
			super(color);
		}
	}
	//creating object of HighlightPainter class
	//Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);
	*/

	//mouse wheel listener for zoom or pinch in pich out
	
	//action listener
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== find || e.getSource()==toolbar_find)
		{
			/*JDialog d = new JDialog(f,"Find");
			d.add(searchText);
			d.add(search);
			d.setSize(400,200);
			d.setLocation(200,150);
			d.setLayout(null);
			searchText.setBounds(20,30,340,30);
			search.setBounds(20,70,100,30);
			d.setVisible(true);
			search.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e18)
				{
					if(searchText.getText()!=null)
						hightlight(ta,searchText.getText());
				}
			});*/
			new Find(ta);
			keypress = 0;
		}
		if(e.getSource() == toolbar_screenShot)
		{
			try
    		{
				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_PRINTSCREEN);
				robot.keyRelease(KeyEvent.VK_PRINTSCREEN);

    		}
    		catch(Exception e222){System.out.println(e222);}
		}
		if(e.getSource() == search)
		{
			String searchTerm = ta.getText().toString().trim();
			String noSpaceStr = searchTerm.replaceAll("\\s", "+"); // using built in method to remove all spaces 
			if(noSpaceStr.length()<=36)
			{
			try 
			{
         		String search = "#q="+noSpaceStr;
         		String url = "http:////www.google.com//"+search;
         		java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
       		}
			catch (java.io.IOException ioex)
			{
           		System.out.println(ioex.getMessage());
       		}
			}
			else
			{
				String message = "Can't Search\n"+"("+"Google only support 36 characters long query"+")"+"\n Please enter query less than 36 characters";
				JOptionPane.showMessageDialog(f,message,"Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(e.getSource()== showStatusBar)
		{
			if(showStatusBar.isSelected())
			{
				statusBar.setVisible(true);
			}
			else
			{
				statusBar.setVisible(false);
			}
		}
		if(e.getSource() == showLineNumbers)
		{
			if(showLineNumbers.isSelected())
			{
				lines.setVisible(true);
				jsp.setRowHeaderView(lines);
			}
			else
			{
				lines.setVisible(false);
				jsp.setRowHeaderView(null);
			}
		}
		if(e.getSource() == zoomin)
		{
			zoom(0);
			keypress = 0;
		}
		if(e.getSource()== zoomout)
		{
			zoom(2);
			keypress = 0;
		}
		if(e.getSource()== defaultzoom)
		{
			zoom(1);
			keypress = 0;
		}
		if(e.getSource()==cut || e.getSource()==cut1 || e.getSource()==toolbar_cut)
		{
			if(ta.getText()!=null)
			{
				ta.cut();
				keypress = 0;
			}
		}
		if(e.getSource()==paste || e.getSource()==paste1 || e.getSource()==toolbar_paste)
		{
		
			ta.paste();
			keypress = 0;

		}
		if(e.getSource()==copy || e.getSource()==copy1 || e.getSource()==toolbar_copy)
		{
			ta.copy();
			keypress = 0;
		}
		if(e.getSource()==selectAll || e.getSource()== selectAll1)
		{
			ta.selectAll();
			keypress = 0;
		}
		if(e.getSource()== dateTime)
		{
			ta.setText(ta.getText() + DateFormat.getDateTimeInstance().format(new Date()));
			c = '*';
			f.setTitle(c+""+fileName +" - "+ applicationName);
			keypress = 0;
		}
		if(e.getSource()==print)
		{
			
			try
			{
				ta.print();
				keypress = 0;
			}catch(Exception evt)
			{
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		if(e.getSource()==open || e.getSource()==toolbar_open)
		{
			String data = ta.getText().trim();
			if(c=='*' && !data.equals(""))
			{
				int ans = JOptionPane.showConfirmDialog(f, "Do you want to save existing file?","Edupad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
				if(ans == JOptionPane.YES_OPTION)
				{
					save_file();
				}
				if(ans == JOptionPane.NO_OPTION)
				{
					open_file();
				}
			}	
			else
			{
				open_file();
			}
			keypress = 0;
		}
		if(e.getSource()==New || e.getSource()==toolbar_new)
		{
			String data = ta.getText().trim();
			if(c=='*')
			{
				int output = JOptionPane.showConfirmDialog(f, "Do you want to save file?","Edupad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);				if(output == JOptionPane.YES_OPTION)
				if(output == JOptionPane.YES_OPTION)
				{
					save_file();
					New_file();
				} 
				if(output == JOptionPane.NO_OPTION)
				{
					New_file();
				}
			}
			else
			{
				New_file();
			}
			keypress = 0;
		}
		if(e.getSource()== newWindow)
		{
			Notepad obj=new Notepad();
			obj.f.setVisible(true);
		}
		if(e.getSource()==exit)
		{
			exit_file();
		}
		if(e.getSource()==save || e.getSource()==toolbar_save)
		{
			save_file();
			keypress = 0;
		}
		if(e.getSource()==saveas || e.getSource()==toolbar_saveas)
		{
			saveas_file();
			keypress = 0;
		}
		if(e.getSource()==aboutus)
		{
			final String message = "Designer and Programmer:Sushil Jangid\nStudent Team:Sushil Jangid,Rajaram Prajapat and Ajay Singh\n\t(Student of BCA Final Year)\n\tSession:2019-20";
			JOptionPane.showMessageDialog(f,message,"About Us",JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==manualguide || e.getSource()==toolbar_help)
		{
			//final String manualLink ="https://docs.google.com/document/d/159Ymfjor9KcaX458rCnlCfSPFHnmODP9_xlRbfj8eKw/edit?usp=sharing";
			String manualLink = "resources/ManualGuide.pdf";
			if (Desktop.isDesktopSupported())
			{
            try
			{
				java.net.URL path = Notepad.class.getResource(manualLink);
				File f = new File(path.getFile());
				Desktop.getDesktop().open(f);
            } catch (IOException ex)
			{
                System.out.println(ex);
            }
        }
		}
		if(e.getSource()==feedback)
		{
			final String email = "sushiljangid69@gmail.com";
			try
			{
				Desktop d = Desktop.getDesktop();
				d.browse(new URI("mailto:"+email));
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
		if(e.getSource()== wordwrap)
		{
			if(wordwrap.isSelected())
			{
				ta.setLineWrap(true);
			}
			else
			{
				ta.setLineWrap(false);
			}
		}
		if(e.getSource()== pagecolor)
		{
			Color initialcolor = Color.WHITE;
			// color chooser Dialog Box
			Color color = JColorChooser.showDialog(f,"Page Color",initialcolor);
			// set Background color of the textarea (ta)
			ta.setBackground(color);
		}
		if(e.getSource()==fontcolor)
		{
			Color initialcolor = Color.BLACK;
			//color chooser Dialog Box
			Color color = JColorChooser.showDialog(f,"Font Color",initialcolor);
			//set foreground color of font
			ta.setForeground(color);
		}
		if(e.getSource()==font || e.getSource()==toolbar_font)
		{
			String preview_data="AaBbYyZz";
			JDialog d = new JDialog(f,"Font",true);	//different dialog box for font
			
			d.setLayout(null);
			
			JLabel x=new JLabel("Font:");
			JLabel y=new JLabel("Font-Style:");
			JLabel z=new JLabel("Size:");
			JLabel preview=new JLabel("<html><div style='color:blue;'>"+preview_data+"</div></html>");
			JButton ok=new JButton("OK");
			JButton cancel=new JButton("Cancel");
			
			preview.setHorizontalAlignment(SwingConstants.CENTER);
			preview.setVerticalAlignment(SwingConstants.CENTER);
			
			//comboxes for font-name, font-style and font-sizes
			JComboBox<String> c1 = new JComboBox<>(fontname);	//will use sb variable
			JComboBox<String> c2 = new JComboBox<>(fontsizes);//will use size variable
			JComboBox<String> c3 = new JComboBox<>(fontstyle);//will use styl variable
			

			//adding to JDialog
			d.add(x);
			d.add(y);
			d.add(z);
			d.add(c1);
			d.add(c2);
			d.add(c3);
			d.add(preview);
			preview.setOpaque(true);
			preview.setBackground(Color.WHITE);
			d.add(ok);
			d.add(cancel);
			
			
			
			//setting bounds to the JDialog
			preview.setBorder(border);
			preview.setBounds(30,250,380,203);
			ok.setBounds(220,520,60,30);
			cancel.setBounds(300,520,100,30);
			x.setBounds(30,40,50,15);
			y.setBounds(220,40,80,15);
			z.setBounds(360,40,50,15);
			c1.setBounds(30,60,150,20);
			c3.setBounds(220,60,100,20);
			c2.setBounds(360,60,50,20);
			

			c1.setEditable(true);
			c2.setEditable(true);
			c3.setEditable(true);
			c1.setSelectedItem(""+sb);
			c2.setSelectedItem(""+size);
			c3.setSelectedItem(""+styl1);
			Font f = new Font(""+sb,styl,size);
			preview.setFont(f);
			
			
			//action listeners for comboboxes
			
			//for font family or font name
			c1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e11)
				{
					JComboBox combobox = (JComboBox) e11.getSource();
					Object selected = combobox.getSelectedItem();
					sb.replace(0,sb.length(),""+selected);
					Font f = new Font(""+sb,styl,size);
					preview.setFont(f);
				}
			});
			//action listener for font sizes
			c2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e12)
				{
					JComboBox combobox = (JComboBox) e12.getSource();
					Object selected = combobox.getSelectedItem();
					size = Integer.parseInt(""+selected);
					Font f = new Font(""+sb,styl,size);
					preview.setFont(f);
				}
			});
			//action listener for font styles ie: Bold , Italic etc
			c3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e13)
				{
					JComboBox combobox = (JComboBox) e13.getSource();
					Object selected = combobox.getSelectedItem();
					String op = ""+selected;
					styl1.replace(0,styl1.length(),op);
					if(op.equals("Bold"))
					{
						styl = Font.BOLD;
						Font f = new Font(""+sb,styl,size);
						preview.setFont(f);

					}
					else if(op.equals("Italic"))
					{
						styl = Font.ITALIC;
						Font f = new Font(""+sb,styl,size);
						preview.setFont(f);

					}
					else if(op.equals("Bold Italic"))
					{
						styl = Font.BOLD | Font.ITALIC;
						Font f = new Font(""+sb,styl,size);
						preview.setFont(f);
					}
					else if(op.equals("Upper Case"))
					{
						preview.setText(preview_data.toUpperCase());
						preview.setForeground(Color.BLUE);
					}
					else if(op.equals("Lower Case"))
					{
						preview.setText(preview_data.toLowerCase());
						preview.setForeground(Color.BLUE);
					}
					else
					{
						styl = Font.PLAIN;
						Font f = new Font(""+sb,styl,size);
						preview.setFont(f);
					}
				}
			});
			ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e14)
				{
					//for setting the selected item to combo box
					Font f = new Font(""+sb,styl,size);
					ta.setFont(f);
					Font ff = new Font("Arial",Font.PLAIN,size);
					lines.setFont(ff);
					preview.setFont(f);
					pre_font=sb;
					pre_size=size;
					pre_style=styl;
					if(c3.getSelectedItem()=="Upper Case")
					{
						String data=ta.getText();
						ta.setText(data.toUpperCase());
					}
					if(c3.getSelectedItem()=="Lower Case")
					{
						String data=ta.getText();
						ta.setText(data.toLowerCase());
					}
					d.dispose();
					ta.requestFocus();
				}
			});
			cancel.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e15)
				{
					sb=pre_font;
					styl= pre_style;
					size=pre_size;
					d.dispose();
					ta.requestFocus();
				}
			});
			d.addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e16)
				{
					sb=pre_font;
					styl= pre_style;
					size=pre_size;
					d.dispose();
					ta.requestFocus();
				}
			});
			d.setBackground(Color.gray);
			d.setSize(450,600);
			d.setLocation(300,200);
			d.setVisible(true);
		}
		if(e.getSource()== undo || e.getSource()==undo1 || e.getSource()==toolbar_undo)
		{
			try
			{
				undoManager.undo();
			}catch(CannotRedoException cre)
			{
				cre.printStackTrace();
			}
			updateButtons();
		}
		if(e.getSource()== redo || e.getSource()==redo1 || e.getSource()==toolbar_redo)
		{
			try
			{
				undoManager.redo();
			}catch(CannotRedoException cre)
			{
				cre.printStackTrace();
			}
			updateButtons();
		}
	} //action performed() ended
	/**
		method for saving the existing file with the same name. If user is writing a new file then it
		will call saveas_file() method 
		this method is invoked when user click on File -> Save or press Ctrl + S
	*/
	void save_file()
	{
		String st2 = ""+fileName;
		if(c=='*' && !st2.equals(default_name))
		{
			try
			{
				String str = ta.getText();
				FileWriter fw=new FileWriter(""+filePath);    
				fw.write(str);    
				fw.close();    
			}catch(IOException excpt){}
			c = ' ';
			f.setTitle(c+""+fileName +" - "+ applicationName);
			cancel =0;
		}
		else
		{
			saveas_file();
		}
	}
	/**
		method for saving the newly created file or existing file but with the different name
		it is invoked when user click on File -> Save As or press Ctrl + Shift + S
	*/
	void saveas_file()
	{
		// Create an object of JFileChooser class
		JFileChooser j = new JFileChooser();
		
		j.setDialogTitle("Save As");
		//j.setFileFilter(new FileNameExtensionFilter("text files (*.txt)"));
		// Invoke the showsSaveDialog function to show the save dialog
		int r = j.showSaveDialog(f);
		if (r == JFileChooser.APPROVE_OPTION)
		{

			// Set the label to the path of the selected directory
			File fi = new File(j.getSelectedFile().getAbsolutePath());
			filePath.replace(0,filePath.length(),fi.getAbsolutePath());
			String name_of_file = fi.getName();
			fileName.replace(0,fileName.length(),""+name_of_file);
			c = ' ';
			f.setTitle(c+""+fileName+" - "+ applicationName);
			try
			{
				// Create a file writer
				FileWriter wr = new FileWriter(fi, false);

				// Create buffered writer to write
				BufferedWriter w = new BufferedWriter(wr);

				// Write
				w.write(ta.getText());

				w.flush();
				w.close();
				
			}
			catch (Exception evt)
			{
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
			cancel =0;
		}
		// If the user cancelled the operation
		else
		{
				//JOptionPane.showMessageDialog(f, "the user cancelled the operation");
				cancel = 1;
		}
	}
	/**
		method for opening the file it is invoked when user click on File -> Open or Press
		Ctrl + O
	*/
	void open_file()
	{
		// Create an object of JFileChooser class
		JFileChooser j = new JFileChooser();
		int r = j.showOpenDialog(f);

		// If the user selects a file
		if (r == JFileChooser.APPROVE_OPTION)
		{
			// Set the label to the path of the selected directory
			File fi = new File(j.getSelectedFile().getAbsolutePath());
			filePath.replace(0,filePath.length(),fi.getAbsolutePath());
			String name_of_file = fi.getName();
			fileName.replace(0,fileName.length(),""+name_of_file);
			c = ' ';
			f.setTitle(c+""+fileName+ " - " + applicationName);
			try
			{
				// String
				String s1 = "", sl = "";
				// File reader
				FileReader fr = new FileReader(fi);

				// Buffered reader
				BufferedReader br = new BufferedReader(fr);

				// Initilize sl
				sl = br.readLine();

				// Take the input from the file
				while ((s1 = br.readLine()) != null)
				{
					sl = sl + "\n" + s1;
				}

				// Set the text
				ta.setText(sl);
				cancel =0;
			}
			catch (Exception evt)
			{
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		// If the user cancelled the operation
		else
		{
			//JOptionPane.showMessageDialog(f, "the user cancelled the operation");
			cancel = 1;
		}
	}
	/**
		method for exiting the Edupad application it is invoked when user click on the File -> Exit or 
		press Alt + F4
		and it will be also invoked when user click on the cross button from the title bar of the application.
	*/
	void exit_file()
	{
		String data = ta.getText().trim();
		if(c=='*' && !fileName.equals("Untitled"))	//if yes it means that file is new and is being modified; actually !fileName.equals("Untitled") is for the new file
		{
			int a = JOptionPane.showConfirmDialog(f, "Do you want to save file?","Edupad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);		
			//System.out.println("inside the first if"+fileName.equals("Untitled"));
			if(a==JOptionPane.NO_OPTION)
			{
				f.dispose();
			}
			if(a==JOptionPane.YES_OPTION)
			{
				save_file();
				if(cancel == 0)
				{
					f.dispose();
				}
			}
			if(a==-1)
			{
				JOptionPane.getRootFrame().dispose();
			}
		}
		else if(c=='*' && fileName.equals("Untitled"))	//actually fileName.equals("Untitled") is for the existing file 
		{
			int a = JOptionPane.showConfirmDialog(f, "Do you want to save existing file?","Edupad",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);		
			//System.out.println("inside the second if"+fileName);
			if(a==JOptionPane.NO_OPTION)
			{
				f.dispose();
			}
			if(a==JOptionPane.YES_OPTION)
			{
				save_file();
				if(cancel == 0)
				{
					f.dispose();
				}
			}
			if(a==-1)
			{
				JOptionPane.getRootFrame().dispose();
			}
		}
		else
		{
			//System.out.println("inside the else");
			f.dispose();
		}
	}
	/**
		method for updating the Edit menu's undo and redo buttons it also works for popup menu undo and
		redo option.
		updating means it checks whether they are able to do undo and redo operations
	*/
	void updateButtons() 
	{
		undo.setEnabled(undoManager.canUndo());
		undo1.setEnabled(undoManager.canUndo());
		redo.setEnabled(undoManager.canRedo());
		redo1.setEnabled(undoManager.canRedo());
		toolbar_undo.setEnabled(undoManager.canUndo());
		toolbar_redo.setEnabled(undoManager.canRedo());
	}
	/** 
		method for creating new files when we click on the File -> New or by pressing Ctrl + N
	*/
	void New_file()
	{
		c = ' ';
		ta.setText("");
		fileName.replace(0,fileName.length(),"Untitled");
		f.setTitle(c+""+fileName +" - "+ applicationName);	
	}
	/** 
		method for showing popup context menu when user right click on the text area
	*/
	void showPopup(MouseEvent me)
	{
      if(me.isPopupTrigger())
	  {
		  popup.show(me.getComponent(), me.getX(), me.getY());
	  }
	}
	void zoom(int n)
	{
		Font f = ta.getFont();
		if(n==0)//zoom in 
		{
			if(f.getSize()>=2)
			{
				Font f1 = new Font(f.getFontName(),f.getStyle(),f.getSize()-2);
				ta.setFont(f1);
				lines.setFont(new Font("Arial",Font.PLAIN,f1.getSize()));
			}
		}
		else if(n==2)//zoom out
		{
			if(f.getSize()<=256)
			{
				Font f2 = new Font(f.getFontName(),f.getStyle(),f.getSize()+2);
				ta.setFont(f2);
				lines.setFont(new Font("Arial",Font.PLAIN,f2.getSize()));
			}
		}
		else//defaultzoom
		{
			Font f3 = new Font(f.getFontName(),f.getStyle(),pre_size);
			ta.setFont(f3);
			lines.setFont(new Font("Arial",Font.PLAIN,pre_size));		
		}
	}
	/*public void removeHighlights(JTextComponent textcomp)
	{
		Highlighter hilite=textcomp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();
		for(int i=0;i<hilites.length;i++)
		{
			if(hilites[i].getPainter() instanceof MyHighlightPainter)
			{
				hilite.removeHighlight(hilites[i]);
			}
		}
	}
	public void hightlight(JTextComponent textcomp,String pattern)
	{
		removeHighlights(textcomp);
		try
		{
			Highlighter hilite = textcomp.getHighlighter();
			Document doc= textcomp.getDocument();
			String text=doc.getText(0,doc.getLength());
			int pos=0;
			while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0)
			{
				hilite.addHighlight(pos,pos+pattern.length(),myHighlightPainter);
				pos=pos+pattern.length();
			}
			
		}catch(Exception e17){System.out.println(e17);}
	}*/
	public void show()
	{
		f.setVisible(true);
	}
	/*public static void main(String[] args)
	{
		Notepad obj=new Notepad();
	}*/
}//class Notepad ended
