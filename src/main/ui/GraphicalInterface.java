package ui;

import exceptions.NoSuchItemExistsException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;
import model.Item;
import model.Lists;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class GraphicalInterface implements ActionListener {
    JFrame frame;
    JFrame jframe;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;

    //JPanel panel;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    JLabel label;
    JLabel response;
    JTextField field;

    private Lists lists;

    public GraphicalInterface() {
        lists = new Lists();
        frame = new JFrame("To Do List");


        frame.setSize(1500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        frame.setLayout(gbl);

        response = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(response,gbc);
        setupMain();
    }

    public void setupMain() {
        //frame.repaint();
        button1 = new JButton("Enter");
        button1.setActionCommand("B1");
        button1.addActionListener(this);

        field = new JTextField(10);

        label = new JLabel("Please enter what you would like to do: [1] add a to do list item [2] cross off an item [3] show all items [4] load data [5] save and quit ");
        label.setFont(new Font("Courier", Font.BOLD,20));
        gbc.fill = GridBagConstraints.VERTICAL;

        gbc.gridx = 1;
        gbc.gridy = 0;

        frame.add(label,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        frame.add(field, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(button1,gbc);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("B1")) {
            String b1input = field.getText();
            response.setText(b1input);
            if (b1input.equals("1")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button1);
                frame.repaint();


                System.out.println("1");
                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("What would you like to enter: [1] Personal Task [2] School Task [3] Work Task ?");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button2 = new JButton("Enter");
                button2.setActionCommand("B2");
                button2.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button2, gbc);
            }
            if (b1input.equals("2")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button1);
                frame.repaint();

                jframe = new JFrame();
                jframe.setSize(1500, 400);
                jframe.setLayout(new BorderLayout());

                JTextArea jtextArea = new JTextArea();
                jtextArea.append("Hello World\n");
                // ToDoLst Printer
                int counter = 0;
                for (int i = 0; i < lists.getMasterList().masterListSize();i++) {
                    Item task = lists.getMasterList().getMasterList().get(i);
                    if (task.getStatus() == false) {
                        counter++;
                        jtextArea.append(counter + ". " + task.getName() + "\n");
                    }
                }

                jframe.add(jtextArea,BorderLayout.CENTER );
                jframe.setVisible(true);



                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter number of item you would like to cross off.");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button2 = new JButton("Cross Off");
                button2.setActionCommand("crossOff");
                button2.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button2, gbc);
            }
            if (b1input.equals("3")) {
                System.out.println("hello");
                jframe = new JFrame();
                jframe.setSize(1500, 400);
                jframe.setLayout(new BorderLayout());

//        JButton button = new JButton("HERE IS THE BUTTON");
//        button.setActionCommand("ok");
//        button.addActionListener(this);
//        jFrame.add(button,BorderLayout.PAGE_END);

                JTextArea jtextArea = new JTextArea();
                jtextArea.append("Hello World\n");

                for (int i = 0; i < lists.getMasterList().getMasterList().size(); i++) {
                    Item task = lists.getMasterList().getMasterList().get(i);
                    if (task.getStatus()) {
                        jtextArea.append(task.getName() + " Status: Complete " + task.getType() + "\n");
                    } else {
                        jtextArea.append(task.getName() + " Status: Not Complete " + task.getType() + "\n");
                    }
                }

                jframe.add(jtextArea,BorderLayout.CENTER );
                jframe.setVisible(true);
            }
            if (b1input.equals("4")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button1);
                frame.repaint();


                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter the name of the file?");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button2 = new JButton("Enter");
                button2.setActionCommand("FileEntryLoad");
                button2.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button2, gbc);
            }
            if (b1input.equals("5")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button1);
                frame.repaint();


                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter a file name?");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button2 = new JButton("Enter");
                button2.setActionCommand("FileEntryQuit");
                button2.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button2, gbc);

            }
        }
        if (e.getActionCommand().equals("B2")) {
            String b2input = field.getText();
            response.setText(b2input);

            if (b2input.equals("1")) {

                frame.remove(label);
                frame.remove(field);
                frame.remove(button2);
                frame.repaint();

                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter your personal task");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button3 = new JButton("Enter");
                button3.setActionCommand("Personal");
                button3.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button3, gbc);

                frame.setVisible(true);
            }

            if (b2input.equals("2")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button2);
                frame.repaint();

                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter your school task");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button3 = new JButton("Enter");
                button3.setActionCommand("School");
                button3.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button3, gbc);

                frame.setVisible(true);
            }

            if (b2input.equals("3")) {
                frame.remove(label);
                frame.remove(field);
                frame.remove(button2);
                frame.repaint();

                gbc.gridx = 1;
                gbc.gridy = 0;
                label = new JLabel("Please enter your work task");
                label.setFont(new Font("Courier", Font.BOLD, 20));
                frame.add(label, gbc);

                field = new JTextField(10);
                gbc.gridx = 1;
                gbc.gridy = 2;

                frame.add(field, gbc);

                button3 = new JButton("Enter");
                button3.setActionCommand("Work");
                button3.addActionListener(this);
                gbc.gridx = 1;
                gbc.gridy = 3;
                frame.add(button3, gbc);

                frame.setVisible(true);
            }


        }

        if (e.getActionCommand().equals("Personal") || e.getActionCommand().equals("School") || e.getActionCommand().equals("Work")) {

            String input = field.getText();
            String output = "";
            //response.setText(input);

            int selection = 0;
            switch (e.getActionCommand()) {
                case "Personal":
                    output = "Perfect! You're personal task was added to the list";
                    selection = 1;
                    break;
                case "School":
                    selection = 2;
                    output = "Perfect! You're school task was added to the list";
                    break;
                case "Work":
                    selection = 3;
                    output = "Perfect! You're work task was added to the list";
            }




            try {
                lists.addItem(input,selection);
                response.setText(output);
            } catch (TooManyThingsToDoException ex) {
                response.setText("Sorry! You have to many uncompleted tasks! Task not added!");
            }

            //System.out.println("input");
            frame.remove(label);
            frame.remove(button3);
            frame.remove(field);
            frame.repaint();
            setupMain();
        }

        if (e.getActionCommand().equals("crossOff")) {

            String input = field.getText();
            response.setText(input);
            int selection = Integer.parseInt(input);

            try {
                lists.crossOff(selection);
            } catch (NothingToCrossOffException ex) {
                ex.printStackTrace();
            } catch (NoSuchItemExistsException ex) {
                ex.printStackTrace();
            }

            System.out.println(lists.getMasterList().masterListStatus("Vishnu"));

            frame.remove(label);
            frame.remove(button2);
            frame.remove(field);
            frame.repaint();
            setupMain();
        }

        if (e.getActionCommand().equals("FileEntryLoad")) {
            String input = field.getText();
            response.setText(input);
            try {
                lists.loadData(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            frame.remove(label);
            frame.remove(button2);
            frame.remove(field);
            frame.repaint();
            setupMain();

        }

        if (e.getActionCommand().equals("FileEntryQuit")) {
            String input = field.getText();
            response.setText(input);
            try {
                lists.saveData(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.remove(label);
            frame.remove(button2);
            frame.remove(field);
            frame.repaint();
            setupMain();
        }

    }
}
