/*
this is tic tac toe game in java by Sushil Jangid
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe {
    JFrame f1, f2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    JButton reset;
    JButton start, exit, x, o;
    Label result;
    int count;
    int[] p1won = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] p2won = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] checked = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    TicTacToe() {
        f1 = new JFrame("SushilJangid Games");
        f2 = new JFrame("TicTacToe");
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();
        b7 = new JButton();
        b8 = new JButton();
        b9 = new JButton();
        reset = new JButton("Reset");
        start = new JButton("Start");
        exit = new JButton("Exit");
        x = new JButton("Choose X ");
        o = new JButton("Choose O");
        result = new Label();
        //Container = JFrame.getContentPane();
        Font f = new Font("Agency FB", Font.BOLD, 40);

//**************** Frame 1 ******************//

        f1.setSize(600, 500);
        f1.setLocation(80, 80);
        f1.setLayout(null);
        f1.setVisible(true);
        //container1.setBackground(Color.CYAN);
        f1.setResizable(false);
        //f1.setDefaultClosingOperation(WindowsConstants.DO_NOTHING_ON_CLOSE);

        f1.add(start);
        f1.add(x);
        f1.add(o);
        f1.add(exit);


        start.setBounds(100, 100, 200, 100);
        x.setBounds(50, 200, 200, 100);
        o.setBounds(250, 200, 200, 100);
        exit.setBounds(100, 300, 200, 100);
        start.setBackground(Color.ORANGE);
        start.setFont(f);
        x.setBackground(Color.GRAY);
        x.setForeground(Color.WHITE);
        x.setFont(f);
        o.setBackground(Color.WHITE);
        x.setForeground(Color.BLACK);
        o.setFont(f);
        exit.setBackground(Color.RED);
        exit.setFont(f);
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                count = 0;
            }
        });

        o.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                count = 1;
            }
        });


//******************* Frame 2 **************************//

        f2.setLocation(100, 100);
        f2.setSize(600, 500);
        f2.setLayout(null);
        f2.setResizable(false);
        f2.setVisible(false);
        f2.add(b1);
        f2.add(b2);
        f2.add(b3);
        f2.add(b4);
        f2.add(b5);
        f2.add(b6);
        f2.add(b7);
        f2.add(b8);
        f2.add(b9);
        f2.add(reset);
        f2.add(result);


        b1.setFont(f);
        b2.setFont(f);
        b3.setFont(f);
        b4.setFont(f);
        b5.setFont(f);
        b6.setFont(f);
        b7.setFont(f);
        b8.setFont(f);
        b9.setFont(f);

        //g.drawLine(10,160,310,160);
		/*drawLine();
		drawLine();
		drawLine();*/


        b1.setBounds(50, 50, 100, 100);
        b2.setBounds(170, 50, 100, 100);
        b3.setBounds(290, 50, 100, 100);
        b4.setBounds(50, 170, 100, 100);
        b5.setBounds(170, 170, 100, 100);
        b6.setBounds(290, 170, 100, 100);
        b7.setBounds(50, 290, 100, 100);
        b8.setBounds(170, 290, 100, 100);
        b9.setBounds(290, 290, 100, 100);
        //reset.setBounds(170,400,100,100);
        //result.setBounds(10,10,100,100);


        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                if (checked[0] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b1.setText("X");
                        checked[0] = 1;
                        p1won[0] = 1;
                        b1.setBackground(Color.PINK);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b1.setText("O");
                        checked[0] = 1;
                        p2won[0] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b1.setBackground(Color.PINK);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
                if (checked[1] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b2.setText("X");
                        checked[1] = 1;
                        p1won[1] = 1;
                        b2.setBackground(Color.PINK);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b2.setText("O");
                        checked[1] = 1;
                        p2won[1] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b2.setBackground(Color.PINK);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e3) {
                if (checked[2] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b3.setText("X");
                        checked[2] = 1;
                        p1won[2] = 1;
                        b3.setBackground(Color.PINK);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b3.setText("O");
                        checked[2] = 1;
                        p2won[2] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b3.setBackground(Color.PINK);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e4) {
                if (checked[3] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b4.setText("X");
                        checked[3] = 1;
                        p1won[3] = 1;
                        b4.setBackground(Color.ORANGE);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b4.setText("O");
                        checked[3] = 1;
                        p2won[3] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b4.setBackground(Color.ORANGE);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e5) {
                if (checked[4] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b5.setText("X");
                        checked[4] = 1;
                        p1won[4] = 1;
                        b5.setBackground(Color.ORANGE);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b5.setText("O");
                        checked[4] = 1;
                        p2won[4] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b5.setBackground(Color.ORANGE);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e6) {
                if (checked[5] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b6.setText("X");
                        checked[5] = 1;
                        p1won[5] = 1;
                        b6.setBackground(Color.ORANGE);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b6.setText("O");
                        checked[5] = 1;
                        p2won[5] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b6.setBackground(Color.ORANGE);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e7) {
                if (checked[6] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b7.setText("X");
                        checked[6] = 1;
                        p1won[6] = 1;
                        b7.setBackground(Color.GREEN);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b7.setText("O");
                        checked[6] = 1;
                        p2won[6] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b7.setBackground(Color.GREEN);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e8) {
                if (checked[7] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b8.setText("X");
                        checked[7] = 1;
                        p1won[7] = 1;

                        int result1 = p1win();
                        int result2 = p2win();
                        b8.setBackground(Color.GREEN);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b8.setText("O");
                        checked[7] = 1;
                        p2won[7] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b8.setBackground(Color.GREEN);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e9) {
                if (checked[8] == 0) {
                    if (count % 2 == 0) {
                        count++;
                        b9.setText("X");
                        checked[8] = 1;
                        p1won[8] = 1;
                        b9.setBackground(Color.GREEN);

                        int result1 = p1win();
                        int result2 = p2win();

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    } else {
                        count++;
                        b9.setText("O");
                        checked[8] = 1;
                        p2won[8] = 1;
                        int result1 = p1win();
                        int result2 = p2win();
                        b9.setBackground(Color.GREEN);

                        if (result1 == 1) {
                            JOptionPane.showMessageDialog(f2, "X won");
                        } else if (result2 == 1) {
                            JOptionPane.showMessageDialog(f2, "O won");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(f2, "This is already checked");
                }
            }
        });

		/*reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e10)
			{
				f2.repaint();
			}
		});*/

        f2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e11) {
                System.exit(0);
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e12) {
                f1.setVisible(false);
                f2.setVisible(true);
            }
        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e13) {
                System.exit(0);
            }
        });
    }

    public int p1win() {
        if (p1won[0] == 1 && p1won[1] == 1 && p1won[2] == 1) {
            return 1;
        }
        if (p1won[3] == 1 && p1won[4] == 1 && p1won[5] == 1) {
            return 1;
        }
        if (p1won[6] == 1 && p1won[7] == 1 && p1won[8] == 1) {
            return 1;
        }
        if (p1won[0] == 1 && p1won[3] == 1 && p1won[6] == 1) {
            return 1;
        }
        if (p1won[1] == 1 && p1won[4] == 1 && p1won[7] == 1) {
            return 1;
        }
        if (p1won[2] == 1 && p1won[5] == 1 && p1won[8] == 1) {
            return 1;
        }
        if (p1won[0] == 1 && p1won[4] == 1 && p1won[8] == 1) {
            return 1;
        }
        if (p1won[2] == 1 && p1won[4] == 1 && p1won[6] == 1) {
            return 1;
        }
        return 0;
    }

    public int p2win() {
        if (p2won[0] == 1 && p2won[1] == 1 && p2won[2] == 1) {
            return 1;
        }
        if (p2won[3] == 1 && p2won[4] == 1 && p2won[5] == 1) {
            return 1;
        }
        if (p2won[6] == 1 && p2won[7] == 1 && p2won[8] == 1) {
            return 1;
        }
        if (p2won[0] == 1 && p2won[3] == 1 && p2won[6] == 1) {
            return 1;
        }
        if (p2won[1] == 1 && p2won[4] == 1 && p2won[7] == 1) {
            return 1;
        }
        if (p2won[2] == 1 && p2won[5] == 1 && p2won[8] == 1) {
            return 1;
        }
        if (p2won[0] == 1 && p2won[4] == 1 && p2won[8] == 1) {
            return 1;
        }
        if (p2won[2] == 1 && p2won[4] == 1 && p2won[6] == 1) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}