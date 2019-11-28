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
// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
// https://stackoverflow.com/questions/53468606/java-getaudioinputstream-trying-to-read-audio-file-getting-javax-sound-sampled
// http://soundbible.com/1127-Computer-Error.html
// https://stackoverflow.com/questions/22035768/javax-sound-sampled-clip-terminating-before-playing-sound
// http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
// followed examples provided on edX for P11 (especially for actionListener implmentation)
// https://www.youtube.com/watch?v=mH1TltI61yU

public class Visuals implements ActionListener {

    private JFrame frame;
    private JFrame jframe;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;


    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JLabel label;
    private JLabel response;
    private JTextField field;
    private JTextArea jtextArea;

    private final CountDownLatch clipDone = new CountDownLatch(1);

    private Lists lists;

    // EFFECTS: instantiates new lists, sets up frame
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


    }

    // MODIFIES: this, Item
    // EFFECTS: sets up main menu with buttons and action listeners
    public void setupMain() {

        label = new JLabel("Please select an option:");

        frame.add(label, positionZero());

        button1 = new JButton("Add Item");
        setupButton(button1, "AddItem", frame, positionOne());

        button2 = new JButton("Cross Off Item");
        setupButton(button2, "CrossOffItem", frame, positionTwo());

        button3 = new JButton("Show All Items");
        setupButton(button3, "ShowAllItems", frame, positionThree());

        button4 = new JButton("Load Data");
        setupButton(button4, "LoadData", frame, positionFour());

        button5 = new JButton("Save and Quit");
        setupButton(button5, "SaveQuit", frame, positionFive());

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
        }

        if (e.getActionCommand().equals("CrossOffItem")) {
            setupJframeCO();
        }

        if (e.getActionCommand().equals("ShowAllItems")) {
            showAllItems();
        }

        if (e.getActionCommand().equals("LoadData")) {
            loadData();
        }

        actionPerfomedContinueOne(e);
    }

    private void actionPerfomedContinueOne(ActionEvent e) {

        if (e.getActionCommand().equals("SaveQuit")) {
            saveAndQuit();
        }

        if (e.getActionCommand().equals("PersonalTask")) {
            personalTask();
        }

        if (e.getActionCommand().equals("SchoolTask")) {
            schoolTask();
        }

        if (e.getActionCommand().equals("WorkTask")) {
            workTask();
        }

        actionPerfomedContinueTwo(e);
    }

    private void actionPerfomedContinueTwo(ActionEvent e) {

        if (e.getActionCommand().equals("Personal")
                || e.getActionCommand().equals("School")
                || e.getActionCommand().equals("Work")) {

            addTask(e.getActionCommand());
        }

        if (e.getActionCommand().equals("crossOff")) {
            crossOff();
        }

        if (e.getActionCommand().equals("PrinterOK")) {
            jframe.dispose();
        }

        if (e.getActionCommand().equals("FileEntryLoad")) {
            fileEntryLoad();
        }

        if (e.getActionCommand().equals("FileEntryQuit")) {
            fileEntryQuit();
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

        frame.add(label, positionZero());

        button1 = new JButton("Personal Task");
        setupButton(button1,"PersonalTask", frame, positionOne());

        button2 = new JButton("School Task");
        setupButton(button2, "SchoolTask", frame, positionTwo());

        button3 = new JButton("Work Task");
        setupButton(button3, "WorkTask", frame, positionThree());

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
        jframe.add(jtextArea,positionTwo());

        jframe.setVisible(true);

    }

    private void loadData() {
        mainFrameRemove();


        label = new JLabel("Please enter the name of the file?");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "FileEntryLoad", frame, positionThree());

        frame.setVisible(true);
    }

    private void saveAndQuit() {
        mainFrameRemove();


        label = new JLabel("Please enter a file name?");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "FileEntryQuit", frame, positionThree());

        frame.setVisible(true);
    }

    private void personalTask() {
        addItemFrameRemove();

        frame.repaint();

        label = new JLabel("Please enter your personal task.");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "Personal", frame, positionThree());

        frame.setVisible(true);
    }

    private void schoolTask() {
        addItemFrameRemove();

        frame.repaint();

        label = new JLabel("Please enter your school task.");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "School", frame, positionThree());

        frame.setVisible(true);
    }

    private void workTask() {
        addItemFrameRemove();

        frame.repaint();

        label = new JLabel("Please enter your work task.");
        label.setFont(new Font("monospaced", Font.PLAIN, 14));
        frame.add(label, positionZero());

        field = new JTextField(10);

        frame.add(field, positionTwo());

        button1 = new JButton("Enter");
        setupButton(button1, "Work", frame, positionThree());

        frame.setVisible(true);
    }

    private void addTask(String ac) {
        String input = field.getText();

        int selection = 0;
        if (ac.equals("Personal")) {
            selection = 1;
        } else if (ac.equals("School")) {
            selection = 2;
        } else if (ac.equals("Work")) {
            selection = 3;
        }


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
            response.setText("Perfect! Your task has been marked as completed.");
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
            response.setText("File Successfully Loaded!");
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

    // SEE REFERENCES ABOVE
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
