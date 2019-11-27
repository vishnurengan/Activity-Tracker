package ui;

import exceptions.NoSuchItemExistsException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;
import model.Item;
import model.Lists;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import javax.sound.sampled.*;
import javax.swing.*;

//Resources :
// https://stackoverflow.com/questions/26290738/how-does-gridwidth-and-gridheight-work-java-guid-gridbaglayout
// https://www.javatpoint.com/java-gridbaglayout
// https://stackoverflow.com/questions/53468606/java-getaudioinputstream-trying-to-read-audio-file-getting-javax-sound-sampled
// http://soundbible.com/1127-Computer-Error.html
// https://stackoverflow.com/questions/22035768/javax-sound-sampled-clip-terminating-before-playing-sound
// http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html

public class Visuals implements ActionListener {

    JFrame frame;
    JFrame jframe;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;


    GridBagLayout gbl;
    GridBagConstraints gbc;
    JLabel label;
    JLabel response;
    JTextField field;
    JTextArea jtextArea;

    final CountDownLatch clipDone = new CountDownLatch(1);

    private Lists lists;

    public Visuals() {
        lists = new Lists();
        frame = new JFrame("To Do List");

        frame.setSize(500, 400);
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

    private void setupMain() {

        label = new JLabel("Please select an option:");
//        gbc.gridx = 1;
//        gbc.gridy = 0;

        frame.add(label, positionZero());

        button1 = new JButton("Add Item");
        setupButton(button1, "AddItem", frame, positionOne());
//        button1.setPreferredSize(new Dimension(130, 20));
//        button1.setActionCommand("AddItem");
//        button1.addActionListener(this);
//        gbc.gridx = 1;
//        gbc.gridy = 1;

//        frame.add(button1, positionOne());

        button2 = new JButton("Cross Off Item");
        setupButton(button2, "CrossOffItem", frame, positionTwo());
//        button2.setPreferredSize(new Dimension(130, 20));
//        button2.setActionCommand("CrossOffItem");
//        button2.addActionListener(this);
//        gbc.gridx = 1;
//        gbc.gridy = 2;

//        frame.add(button2,positionTwo());

        button3 = new JButton("Show All Items");
        setupButton(button3, "ShowAllItems", frame, positionThree());
//        button3.setPreferredSize(new Dimension(130, 20));
//        button3.setActionCommand("ShowAllItems");
//        button3.addActionListener(this);
//        gbc.gridx = 1;
//        gbc.gridy = 3;

//        frame.add(button3,positionThree());

        button4 = new JButton("Load Data");
        setupButton(button4, "LoadData", frame, positionFour());
//        button4.setPreferredSize(new Dimension(130, 20));
//        button4.setActionCommand("LoadData");
//        button4.addActionListener(this);
//        gbc.gridx = 1;
//        gbc.gridy = 4;

//        frame.add(button4,positionFour());

        button5 = new JButton("Save and Quit");
        setupButton(button5, "SaveQuit", frame, positionFive());
//        button5.setPreferredSize(new Dimension(130, 20));
//        button5.setActionCommand("SaveQuit");
//        button5.addActionListener(this);
//        gbc.gridx = 1;
//        gbc.gridy = 5;

//        frame.add(button5,positionFive());
        frame.setVisible(true);


    }

    private GridBagConstraints positionZero() {
        gbc.gridx = 1;
        gbc.gridy = 0;
        return gbc;
    }

    private GridBagConstraints positionOne() {
        gbc.gridx = 1;
        gbc.gridy = 1;
        return gbc;
    }

    private GridBagConstraints positionTwo() {
        gbc.gridx = 1;
        gbc.gridy = 2;
        return gbc;
    }

    private GridBagConstraints positionThree() {
        gbc.gridx = 1;
        gbc.gridy = 3;
        return gbc;
    }

    private GridBagConstraints positionFour() {
        gbc.gridx = 1;
        gbc.gridy = 4;
        return gbc;
    }

    private GridBagConstraints positionFive() {
        gbc.gridx = 1;
        gbc.gridy = 5;
        return gbc;
    }

    private void setupButton(JButton b, String ac, JFrame jf, GridBagConstraints c) {
        b.setPreferredSize(new Dimension(130, 20));
        b.setActionCommand(ac);
        b.addActionListener(this);
        jf.add(b,c);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddItem")) {
            addItem();
            //DELETE
//            mainFrameRemove();
//
//
//            label = new JLabel("What would you like to enter:");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            frame.add(label, positionZero());
//
//            button1 = new JButton("Personal Task");
//            setupButton(button1,"PersonalTask", frame, positionOne());
//            //button1.setPreferredSize(new Dimension(130, 20));
////            button1.setActionCommand("PersonalTask");
////            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 1;
////            frame.add(button1, positionOne());
//
//            button2 = new JButton("School Task");
//            setupButton(button2, "SchoolTask", frame, positionTwo());
//            //button2.setPreferredSize(new Dimension(130, 20));
////            button2.setActionCommand("SchoolTask");
////            button2.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
////            frame.add(button2,positionTwo());
//
//            button3 = new JButton("Work Task");
//            setupButton(button3, "WorkTask", frame, positionThree());
//            //button3.setPreferredSize(new Dimension(130, 20));
////            button3.setActionCommand("WorkTask");
////            button3.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
////            frame.add(button3,positionThree());
//
//
//            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("CrossOffItem")) {
            setupJframeCO();
            //crossOffItem();
            //DELETE
//            mainFrameRemove();
//
//            jframe = new JFrame();
//            jframe.setSize(1500, 400);
//            jframe.setLayout(gbl);
//            gbc.insets = new Insets(5,5,5,5);
//
//            JButton button = new JButton("OK");
//            setupButton(button, "PrinterOK", jframe, positionFour());
////            button.setActionCommand("PrinterOK");
////            button.addActionListener(this);
////
//////            gbc.gridx = 1;
//////            gbc.gridy = 4;
////            jframe.add(button,positionFour());
//
//            JTextArea jtextArea = new JTextArea();
//
//            int counter = 0;
//            for (int i = 0; i < lists.getMasterList().masterListSize();i++) {
//                Item task = lists.getMasterList().getMasterList().get(i);
//                jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
//                if (task.getStatus() == false) {
//                    counter++;
//                    jtextArea.append(String.format("%d. %-20s\n", counter, task.getName()));
//                }
//            }
//
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//            jframe.add(jtextArea,positionTwo());
//
//
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter number of item you would like to cross off.");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Cross Off");
//            setupButton(button1, "crossOff", frame,positionThree());
////            button1.setActionCommand("crossOff");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
//            jframe.setVisible(true);
        }

        if (e.getActionCommand().equals("ShowAllItems")) {
            showAllItems();
            //DELETE
//            jframe = new JFrame();
//            jframe.setSize(1500, 400);
//            jframe.setLayout(gbl);
//            gbc.insets = new Insets(5,5,5,5);
//
//            JButton button = new JButton("OK");
//            setupButton(button, "PrinterOK", jframe, positionFour());
////            button.setActionCommand("PrinterOK");
////            button.addActionListener(this);
////
//////            gbc.gridx = 1;
//////            gbc.gridy = 4;
////            jframe.add(button,positionFour());
//
//            JTextArea jtextArea = new JTextArea();
//            for (int i = 0; i < lists.getMasterList().getMasterList().size(); i++) {
//                Item task = lists.getMasterList().getMasterList().get(i);
//                jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
//                //https://stackoverflow.com/questions/29147709/aligning-text-in-jtextarea-in-java
//
//                jtextArea.append(String.format("%d. %-30s %-30s %-30s", i + 1, task.getName(),
//                        task.getStatus() ? "Status: Complete" : "Status: Not Complete", "Type: " + task.getType()));
//
//                if (i < lists.getMasterList().getMasterList().size() - 1) {
//                    jtextArea.append("\n");
//                }
//            }
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//            jframe.add(jtextArea,positionTwo());
//
//            jframe.setVisible(true);
        }

        if (e.getActionCommand().equals("LoadData")) {
            loadData();

            // DELETE
//            mainFrameRemove();
//
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter the name of the file?");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Enter");
//            setupButton(button1, "FileEntryLoad", frame, positionThree());
////            button1.setActionCommand("FileEntryLoad");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
        }

        actionPerfomedContinueOne(e);
    }

    public void actionPerfomedContinueOne(ActionEvent e) {

        if (e.getActionCommand().equals("SaveQuit")) {
            saveAndQuit();

            // DELETE
//            mainFrameRemove();
//
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter a file name?");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Enter");
//            setupButton(button1, "FileEntryQuit", frame, positionThree());
////            button1.setActionCommand("FileEntryQuit");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("PersonalTask")) {
            personalTask();

            //DELETE
//            addItemFrameRemove();
//
//            frame.repaint();
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter your personal task");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Enter");
//            setupButton(button1, "Personal", frame, positionThree());
////            button1.setActionCommand("Personal");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("SchoolTask")) {
            schoolTask();

            //DELETE
//            addItemFrameRemove();
//
//            frame.repaint();
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter your school task");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Enter");
//            setupButton(button1, "School", frame, positionThree());
////            button1.setActionCommand("School");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("WorkTask")) {
            workTask();

            //DELETE
//            addItemFrameRemove();
//
//            frame.repaint();
//
////            gbc.gridx = 1;
////            gbc.gridy = 0;
//            label = new JLabel("Please enter your work task");
//            label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            frame.add(label, positionZero());
//
//            field = new JTextField(10);
////            gbc.gridx = 1;
////            gbc.gridy = 2;
//
//            frame.add(field, positionTwo());
//
//            button1 = new JButton("Enter");
//            setupButton(button1, "Work", frame, positionThree());
////            button1.setActionCommand("Work");
////            button1.addActionListener(this);
//////            gbc.gridx = 1;
//////            gbc.gridy = 3;
////            frame.add(button1, positionThree());
//
//            frame.setVisible(true);
        }

        actionPerfomedContinueTwo(e);
    }

    public void actionPerfomedContinueTwo(ActionEvent e) {

        if (e.getActionCommand().equals("Personal")
                || e.getActionCommand().equals("School")
                || e.getActionCommand().equals("Work")) {

            addTask(e.getActionCommand());

            // DELETE
//            String input = field.getText();
//            String output = "";
//
//            int selection = 0;
//            switch (e.getActionCommand()) {
//                case "Personal":
//                    output = "Perfect! You're personal task was added to the list!";
//                    selection = 1;
//                    break;
//                case "School":
//                    selection = 2;
//                    output = "Perfect! You're school task was added to the list!";
//                    break;
//                //work
//                default:
//                    selection = 3;
//                    output = "Perfect! You're work task was added to the list!";
//            }
//
//            try {
//                lists.addItem(input,selection);
//                response.setText(output);
//            } catch (TooManyThingsToDoException ex) {
//                response.setText("Sorry! You have to many uncompleted tasks! Task not added!");
//            }
//
//            gotoMainFrame();
        }

        if (e.getActionCommand().equals("crossOff")) {
            crossOff();
            //DELETE
//            String input = field.getText();
//            int selection = Integer.parseInt(input);
//
//            try {
//                lists.crossOff(selection);
//            } catch (NothingToCrossOffException ex) {
//                response.setText("There's Nothing to Cross Off!");
//            } catch (NoSuchItemExistsException ex) {
//                response.setText("Looks like you entered an invalid number!");
//            }
//
//            gotoMainFrame();
        }

        if (e.getActionCommand().equals("PrinterOK")) {
            jframe.dispose();
        }

        if (e.getActionCommand().equals("FileEntryLoad")) {
            fileEntryLoad();
            //DELETE
//            String input = field.getText();
//
//            try {
//                lists.loadData(input);
//            } catch (IOException ex) {
//                response.setText("Looks like that file does not exist");
//            }
//
//            gotoMainFrame();

        }

        if (e.getActionCommand().equals("FileEntryQuit")) {
            fileEntryQuit();
            //DELETE
//            String input = field.getText();
//
//            try {
//                lists.saveData(input);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//            gotoMainFrame();
//            System.exit(0);
        }

    }

    private void mainFrameRemove() {
        frame.remove(label);
        frame.remove(button1);
        frame.remove(button2);
        frame.remove(button3);
        frame.remove(button4);
        frame.remove(button5);
        response.setText("");
        frame.repaint();
    }

    private void addItemFrameRemove() {
        frame.remove(label);
        frame.remove(button1);
        frame.remove(button2);
        frame.remove(button3);
    }

    private void gotoMainFrame() {
        frame.remove(label);
        frame.remove(button1);
        frame.remove(field);
        frame.repaint();
        setupMain();
    }

    private void addItem() {
        mainFrameRemove();


        label = new JLabel("What would you like to enter:");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
//            gbc.gridx = 1;
//            gbc.gridy = 0;
        frame.add(label, positionZero());

        button1 = new JButton("Personal Task");
        setupButton(button1,"PersonalTask", frame, positionOne());
        //button1.setPreferredSize(new Dimension(130, 20));
//            button1.setActionCommand("PersonalTask");
//            button1.addActionListener(this);
//            gbc.gridx = 1;
//            gbc.gridy = 1;
//            frame.add(button1, positionOne());

        button2 = new JButton("School Task");
        setupButton(button2, "SchoolTask", frame, positionTwo());
        //button2.setPreferredSize(new Dimension(130, 20));
//            button2.setActionCommand("SchoolTask");
//            button2.addActionListener(this);
//            gbc.gridx = 1;
//            gbc.gridy = 2;
//            frame.add(button2,positionTwo());

        button3 = new JButton("Work Task");
        setupButton(button3, "WorkTask", frame, positionThree());
        //button3.setPreferredSize(new Dimension(130, 20));
//            button3.setActionCommand("WorkTask");
//            button3.addActionListener(this);
//            gbc.gridx = 1;
//            gbc.gridy = 3;
//            frame.add(button3,positionThree());


        frame.setVisible(true);

    }

    private void crossOffItem() {
        mainFrameRemove();

        label = new JLabel("Please enter number of item you would like to cross off.");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
        frame.add(field, positionTwo());

        button1 = new JButton("Cross Off");
        setupButton(button1, "crossOff", frame,positionThree());

        frame.setVisible(true);
        jframe.setVisible(true);

    }

    private void setupJframeCO() {
        jframeInitilaization();

        int counter = 0;
        for (int i = 0; i < lists.getMasterList().masterListSize();i++) {
            Item task = lists.getMasterList().getMasterList().get(i);
            if (task.getStatus() == false) {
                counter++;
                if (counter == 1) {
                    jtextArea.append(String.format("%d. %-20s", counter, task.getName()));
                } else {
                    jtextArea.append(String.format("\n%d. %-20s", counter, task.getName()));
                }
            }

        }

        if (counter != 0) {
            crossOffItem();
        } else {
            response.setText("There is nothing to cross off!");
        }

    }

    private void showAllItems() {
        jframeInitilaization();
//        jframe = new JFrame();
//        jframe.setSize(1500, 400);
//        jframe.setLayout(gbl);
//        gbc.insets = new Insets(5,5,5,5);

//        JButton button = new JButton("OK");
//        setupButton(button, "PrinterOK", jframe, positionFour());
////            button.setActionCommand("PrinterOK");
////            button.addActionListener(this);
////
//////            gbc.gridx = 1;
//////            gbc.gridy = 4;
////            jframe.add(button,positionFour());
//
//        JTextArea jtextArea = new JTextArea();

        if (lists.getMasterList().masterListSize() == 0) {
            jtextArea.append("There is nothing on your list yet");
        } else {
            for (int i = 0; i < lists.getMasterList().getMasterList().size(); i++) {
                Item task = lists.getMasterList().getMasterList().get(i);
                jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
                //https://stackoverflow.com/questions/29147709/aligning-text-in-jtextarea-in-java

                jtextArea.append(String.format("%d. %-20s %-30s %-20s", i + 1, task.getName(),
                        task.getStatus() ? "Status: Complete" : "Status: Not Complete", "Type: " + task.getType()));

                if (i < lists.getMasterList().getMasterList().size() - 1) {
                    jtextArea.append("\n");
                }
            }
        }
//            gbc.gridx = 1;
//            gbc.gridy = 2;
        jframe.add(jtextArea,positionTwo());

        jframe.setVisible(true);

    }

    private void loadData() {
        mainFrameRemove();


//            gbc.gridx = 1;
//            gbc.gridy = 0;
        label = new JLabel("Please enter the name of the file?");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
//            gbc.gridx = 1;
//            gbc.gridy = 2;

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "FileEntryLoad", frame, positionThree());
//            button1.setActionCommand("FileEntryLoad");
//            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
//            frame.add(button1, positionThree());

        frame.setVisible(true);
    }

    private void saveAndQuit() {
        mainFrameRemove();


//            gbc.gridx = 1;
//            gbc.gridy = 0;
        label = new JLabel("Please enter a file name?");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
//            gbc.gridx = 1;
//            gbc.gridy = 2;

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "FileEntryQuit", frame, positionThree());
//            button1.setActionCommand("FileEntryQuit");
//            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
//            frame.add(button1, positionThree());

        frame.setVisible(true);
    }

    private void personalTask() {
        addItemFrameRemove();

        frame.repaint();

//            gbc.gridx = 1;
//            gbc.gridy = 0;
        label = new JLabel("Please enter your personal task");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
//            gbc.gridx = 1;
//            gbc.gridy = 2;

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "Personal", frame, positionThree());
//            button1.setActionCommand("Personal");
//            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
//            frame.add(button1, positionThree());

        frame.setVisible(true);
    }

    private void schoolTask() {
        addItemFrameRemove();

        frame.repaint();

//            gbc.gridx = 1;
//            gbc.gridy = 0;
        label = new JLabel("Please enter your school task");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
//            gbc.gridx = 1;
//            gbc.gridy = 2;

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "School", frame, positionThree());
//            button1.setActionCommand("School");
//            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
//            frame.add(button1, positionThree());

        frame.setVisible(true);
    }

    private void workTask() {
        addItemFrameRemove();

        frame.repaint();

//            gbc.gridx = 1;
//            gbc.gridy = 0;
        label = new JLabel("Please enter your work task");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);
//            gbc.gridx = 1;
//            gbc.gridy = 2;

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "Work", frame, positionThree());
//            button1.setActionCommand("Work");
//            button1.addActionListener(this);
////            gbc.gridx = 1;
////            gbc.gridy = 3;
//            frame.add(button1, positionThree());

        frame.setVisible(true);
    }

    private void addTask(String ac) {
        String input = field.getText();
        //String output = "";

        int selection = 0;
        if (ac.equals("Personal")) {
//            output = "Perfect! You're personal task was added to the list!";
            selection = 1;
        } else if (ac.equals("School")) {
            selection = 2;
//            output = "Perfect! You're school task was added to the list!";
        } else if (ac.equals("Work")) {
            selection = 3;
//            output = "Perfect! You're work task was added to the list!";
        }


//        switch (ac) {
//            case "Personal":
//                output = "Perfect! You're personal task was added to the list!";
//                selection = 1;
//                break;
//            case "School":
//                selection = 2;
//                output = "Perfect! You're school task was added to the list!";
//                break;
//            //work
//            default:
//                selection = 3;
//                output = "Perfect! You're work task was added to the list!";
//        }

        try {
            lists.addItem(input,selection);
            response.setText("Perfect! Your " + ac.toLowerCase() + " task was added to the list!");
        } catch (TooManyThingsToDoException ex) {
            response.setText("Sorry! You have to many uncompleted tasks! Task not added!");
            playSound("ce.wav");
        }

        gotoMainFrame();
    }

    private void crossOff() {
        String input = field.getText();
        int selection = Integer.parseInt(input);

        try {
            lists.crossOff(selection);
        } catch (NothingToCrossOffException ex) {
            response.setText("There's Nothing to Cross Off!");
            playSound("ce.wav");
        } catch (NoSuchItemExistsException ex) {
            response.setText("Looks like you entered an invalid number!");
            playSound("ce.wav");
        }

        gotoMainFrame();
    }

    private void fileEntryLoad() {
        String input = field.getText();

        try {
            lists.loadData(input);
        } catch (IOException ex) {
            response.setText("Looks like that file does not exist");
            playSound("ce.wav");
        }

        gotoMainFrame();
    }

    private void fileEntryQuit() {
        String input = field.getText();

        try {
            lists.saveData(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        gotoMainFrame();
        System.exit(0);
    }

    private void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sounds/" + soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent e) {
                    if (e.getType() == LineEvent.Type.STOP) {
                        e.getLine().close();
                        clipDone.countDown();
                    }
                }
            });
            clip.start();
            clipDone.await();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }

    private void jframeInitilaization() {
        jframe = new JFrame();
        jframe.setSize(1000, 400);
        jframe.setLayout(gbl);
        gbc.insets = new Insets(5,5,5,5);

        JButton button = new JButton("OK");
        setupButton(button, "PrinterOK", jframe, positionFour());
        jtextArea = new JTextArea();
        jtextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        jframe.add(jtextArea,positionTwo());

    }
}
