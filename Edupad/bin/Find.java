/**
    * class Find shows a find dialog which gives find,find Next,Replace,Replace All and Cancel options
    * this class has the functionality to find and replace the text in the Notepad class's text area
*/
package notepad;    //main package 

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Find extends JDialog implements ActionListener
{
        private static final long serialVersionUID = 1L;
        int startIndex=0;
        int select_start=-1;
        JLabel lab1, lab2;
        JTextField textF, textR;
        JButton findBtn, findNext, replace, replaceAll, cancel;
        private JTextArea txt;
        /**
            constructor of Find class
            @param text
            here component is text area which is send by the Notepad class while action listener for Find is invoked
        */
        public Find(JTextArea text)
        {
                setModal(true);
                ImageIcon icon = new ImageIcon(this.getClass().getResource("resources/icon.png"));
                setIconImage(icon.getImage());
                this.txt = text;

                lab1 = new JLabel("Find:");
                lab2 = new JLabel("Replace:");
                textF = new JTextField(30);
                textR = new JTextField(30);
                findBtn = new JButton("Find");
                findNext = new JButton("Find Next");
                replace = new JButton("Replace");
                replaceAll = new JButton("Replace All");
                cancel = new JButton("Cancel");

                // Set Layout NULL
                setLayout(null);

                // Set the width and height of the label
                int labWidth = 80;
                int labHeight = 30;

                // Adding labels
                lab1.setBounds(10,10, labWidth, labHeight);
                add(lab1);
                textF.setBounds(10+labWidth, 10, 120, 30);
                add(textF);
                lab2.setBounds(10, 10+labHeight+10, labWidth, labHeight);
                add(lab2);
                textR.setBounds(10+labWidth, 10+labHeight+10, 120, 30);
                add(textR);

                // Adding buttons
                int bw=115;//button width
                int bh=30;//button height
                findBtn.setBounds(225, 6, bw, bh);
                add(findBtn);
                findBtn.addActionListener(this);

                findNext.setBounds(225, bh+5, bw, bh);
                add(findNext);
                findNext.addActionListener(this);

                replace.setBounds(225, 2*bh+5, bw, bh);
                add(replace);
                replace.addActionListener(this);

                replaceAll.setBounds(225, 3*bh+5, bw, bh);
                add(replaceAll);
                replaceAll.addActionListener(this);

                cancel.setBounds(225, 4*bh+5, bw, bh);
                add(cancel);
                cancel.addActionListener(this);


                // Set the width and height of the window
                int width = 360;
                int height = 210;

                addWindowListener(new WindowAdapter()
                {
                        public void windowClosing(WindowEvent e16)
                        {
                            dispose();
                        }
                });
                // Set size window
                setSize(width,height);

                // center the frame on the frame
                //setLocationRelativeTo(txt);
                setLocation(200,150);
                setVisible(true);
        }
        /**
            method void find(void);
            find() method finds the text written in the Find textfield in the text area of Notepad class
            it is different from the findNext() method because it only search the first occurence of the word
        */
        public void find()
        {
                select_start = txt.getText().toLowerCase().indexOf(textF.getText().toLowerCase());
                if(select_start == -1)
                {
                    startIndex = 0;
                    JOptionPane.showMessageDialog(null, "Could not find \"" + textF.getText() + "\"!");
                    return;
                }
                if(select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase()))
                {
                    startIndex = 0;
                }
                int select_end = select_start + textF.getText().length();
                txt.select(select_start, select_end);
        }
        /**
            method void findNext(void);
            findNext() method is used for finding the text into the textarea of Notepad class
            it is different form the find() method because findNext() method searches all occurence of 
            the word/text searched.
        */
        public void findNext()
        {
                String selection = txt.getSelectedText();
                try
                {
                    selection.equals("");
                }
                catch(NullPointerException e)
                {
                    selection = textF.getText();
                    try
                    {
                        selection.equals("");
                    }
                    catch(NullPointerException e2)
                    {
                        selection = JOptionPane.showInputDialog("Find:");
                        textF.setText(selection);
                    }
                }
                try
                {
                    int select_start = txt.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
                    int select_end = select_start+selection.length();
                    txt.select(select_start, select_end);
                    startIndex = select_end+1;

                    if(select_start == txt.getText().toLowerCase().lastIndexOf(selection.toLowerCase()))
                    {
                        startIndex = 0;
                    }
                }
                catch(NullPointerException e)
                {}
        }
        /**
            method void replace(void);
            replace() method is for replaceing the searched word but replaces only the one highlighted word
        */
        public void replace()
        {
                try
                {
                    find();
                    if (select_start != -1)
                        txt.replaceSelection(textR.getText());
                }
                catch(NullPointerException e)
                {
                    System.out.print("Null Pointer Exception: "+e);
                }
        }
        /**
            method void replaceAll(void);
            replaceAll() method is for replaceing the searched word it replaces all found words
        */
        public void replaceAll()
        {
                txt.setText(txt.getText().toLowerCase().replaceAll(textF.getText().toLowerCase(), textR.getText()));
        }
        /**
            method void actionPerformed(ActionEvent e);
            @param  e
            this method listenes the action event generated by the buttons (findBtn,findNext,replace,replaceAll,cancel)
        */
        public void actionPerformed(ActionEvent e)
        {
                if(e.getSource() == findBtn)
                {
                   find();
                }
                else if(e.getSource() == findNext)
                {
                   findNext();
                }
                else if(e.getSource() == replace)
                {
                    replace();
                }
                else if(e.getSource() == replaceAll)
                {
                   replaceAll();
                }
                else if(e.getSource() == cancel)
                {
                    this.dispose();
                    txt.requestFocus();
                }
        }
}