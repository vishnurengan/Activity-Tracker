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

public class Visuals implements ActionListener {

    JFrame frame;
    JFrame jframe;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton buttont;

    GridBagLayout gbl;
    GridBagConstraints gbc;
    JLabel label;
    JLabel response;
    JTextField field;

    private Lists lists;

    public Visuals() {
        lists = new Lists();
        frame = new JFrame("To Do List");

        frame.setSize(1500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);

        frame.setLayout(gbl);

        response = new JLabel("");
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(response,gbc);

        setupMain();

    }

    public void setupMain() {

        label = new JLabel("Please select an option:");
        gbc.gridx = 1;
        gbc.gridy = 0;

        frame.add(label,gbc);

        button1 = new JButton("Add Item");
        button1.setPreferredSize(new Dimension(130, 20));
        button1.setActionCommand("AddItem");
        button1.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;

        frame.add(button1,gbc);

        button2 = new JButton("Cross Off Item");
        button2.setPreferredSize(new Dimension(130, 20));
        button2.setActionCommand("CrossOffItem");
        button2.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;

        frame.add(button2,gbc);

        button3 = new JButton("Show All Items");
        button3.setPreferredSize(new Dimension(130, 20));
        button3.setActionCommand("ShowAllItems");
        button3.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;

        frame.add(button3,gbc);

        button4 = new JButton("Load Data");
        button4.setPreferredSize(new Dimension(130, 20));
        button4.setActionCommand("LoadData");
        button4.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 4;

        frame.add(button4,gbc);

        button5 = new JButton("Save and Quit");
        button5.setPreferredSize(new Dimension(130, 20));
        button5.setActionCommand("SaveQuit");
        button5.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 5;

        frame.add(button5,gbc);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddItem")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);
            frame.remove(button4);
            frame.remove(button5);
            response.setText("");
            frame.repaint();


            label = new JLabel("What would you like to enter:");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            gbc.gridx = 1;
            gbc.gridy = 0;
            frame.add(label, gbc);

            button1 = new JButton("Personal Task");
            button1.setPreferredSize(new Dimension(130, 20));
            button1.setActionCommand("PersonalTask");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            frame.add(button1,gbc);

            button2 = new JButton("School Task");
            button2.setPreferredSize(new Dimension(130, 20));
            button2.setActionCommand("SchoolTask");
            button2.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 2;
            frame.add(button2,gbc);

            button3 = new JButton("Work Task");
            button3.setPreferredSize(new Dimension(130, 20));
            button3.setActionCommand("WorkTask");
            button3.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button3,gbc);


            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("CrossOffItem")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);
            frame.remove(button4);
            frame.remove(button5);
            response.setText("");
            frame.repaint();

            jframe = new JFrame();
            jframe.setSize(1500, 400);
            jframe.setLayout(gbl);
            gbc.insets = new Insets(5,5,5,5);

            JButton button = new JButton("OK");
            button.setActionCommand("PrinterOK");
            button.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 4;
            jframe.add(button,gbc);

            JTextArea jtextArea = new JTextArea();

            int counter = 0;
            for (int i = 0; i < lists.getMasterList().masterListSize();i++) {
                Item task = lists.getMasterList().getMasterList().get(i);
                jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
                if (task.getStatus() == false) {
                    counter++;
                    jtextArea.append(String.format("%d. %-20s\n", counter, task.getName()));
                }
            }

            gbc.gridx = 1;
            gbc.gridy = 2;
            jframe.add(jtextArea,gbc);



            gbc.gridx = 1;
            gbc.gridy = 0;
            label = new JLabel("Please enter number of item you would like to cross off.");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            frame.add(label, gbc);

            field = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;

            frame.add(field, gbc);

            button1 = new JButton("Cross Off");
            button1.setActionCommand("crossOff");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button1, gbc);

            frame.setVisible(true);
            jframe.setVisible(true);
        }

        if (e.getActionCommand().equals("ShowAllItems")) {
            jframe = new JFrame();
            jframe.setSize(1500, 400);
            jframe.setLayout(gbl);
            gbc.insets = new Insets(5,5,5,5);

            JButton button = new JButton("OK");
            button.setActionCommand("PrinterOK");
            button.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 4;
            jframe.add(button,gbc);

            JTextArea jtextArea = new JTextArea();
            for (int i = 0; i < lists.getMasterList().getMasterList().size(); i++) {
                Item task = lists.getMasterList().getMasterList().get(i);
                jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
                //https://stackoverflow.com/questions/29147709/aligning-text-in-jtextarea-in-java

                jtextArea.append(String.format("%d. %-30s %-30s %-30s", i + 1, task.getName(),
                        task.getStatus() ? "Status: Complete" : "Status: Not Complete", "Type: " + task.getType()));

                if (i < lists.getMasterList().getMasterList().size() - 1) {
                    jtextArea.append("\n");
                }
            }
            gbc.gridx = 1;
            gbc.gridy = 2;
            jframe.add(jtextArea,gbc);

            jframe.setVisible(true);
        }

        if (e.getActionCommand().equals("LoadData")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);
            frame.remove(button4);
            frame.remove(button5);
            response.setText("");
            frame.repaint();


            gbc.gridx = 1;
            gbc.gridy = 0;
            label = new JLabel("Please enter the name of the file?");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            frame.add(label, gbc);

            field = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;

            frame.add(field, gbc);

            button1 = new JButton("Enter");
            button1.setActionCommand("FileEntryLoad");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button1, gbc);

            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("PersonalTask")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);

            frame.repaint();

            gbc.gridx = 1;
            gbc.gridy = 0;
            label = new JLabel("Please enter your personal task");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            frame.add(label, gbc);

            field = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;

            frame.add(field, gbc);

            button1 = new JButton("Enter");
            button1.setActionCommand("Personal");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button1, gbc);

            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("SchoolTask")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);

            frame.repaint();

            gbc.gridx = 1;
            gbc.gridy = 0;
            label = new JLabel("Please enter your school task");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            frame.add(label, gbc);

            field = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;

            frame.add(field, gbc);

            button1 = new JButton("Enter");
            button1.setActionCommand("School");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button1, gbc);

            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("WorkTask")) {
            frame.remove(label);
            frame.remove(button1);
            frame.remove(button2);
            frame.remove(button3);

            frame.repaint();

            gbc.gridx = 1;
            gbc.gridy = 0;
            label = new JLabel("Please enter your work task");
            label.setFont(new Font("monospaced", Font.PLAIN, 14));
            frame.add(label, gbc);

            field = new JTextField(10);
            gbc.gridx = 1;
            gbc.gridy = 2;

            frame.add(field, gbc);

            button1 = new JButton("Enter");
            button1.setActionCommand("Work");
            button1.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            frame.add(button1, gbc);

            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("Personal") || e.getActionCommand().equals("School") || e.getActionCommand().equals("Work")) {

            String input = field.getText();
            String output = "";

            int selection = 0;
            switch (e.getActionCommand()) {
                case "Personal":
                    output = "Perfect! You're personal task was added to the list!";
                    selection = 1;
                    break;
                case "School":
                    selection = 2;
                    output = "Perfect! You're school task was added to the list!";
                    break;
                //work
                default:
                    selection = 3;
                    output = "Perfect! You're work task was added to the list!";
            }

            try {
                lists.addItem(input,selection);
                response.setText(output);
            } catch (TooManyThingsToDoException ex) {
                response.setText("Sorry! You have to many uncompleted tasks! Task not added!");
            }

            frame.remove(label);
            frame.remove(button1);
            frame.remove(field);
            frame.repaint();
            setupMain();
        }

        if (e.getActionCommand().equals("crossOff")) {
            String input = field.getText();
            int selection = Integer.parseInt(input);

            try {
                lists.crossOff(selection);
            } catch (NothingToCrossOffException ex) {
                response.setText("There's Nothing to Cross Off!");
            } catch (NoSuchItemExistsException ex) {
                response.setText("Looks like you entered an invalid number!");
            }

            frame.remove(label);
            frame.remove(button1);
            frame.remove(field);
            frame.repaint();
            setupMain();
        }

        if (e.getActionCommand().equals("PrinterOK")) {
            jframe.dispose();
        }

        if (e.getActionCommand().equals("FileEntryLoad")) {
            String input = field.getText();

            try {
                lists.loadData(input);
            } catch (IOException ex) {
                response.setText("Looks like that file does not exist");
            }

            frame.remove(label);
            frame.remove(button1);
            frame.remove(field);
            frame.repaint();
            setupMain();

        }



    }
}
