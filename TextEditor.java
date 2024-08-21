import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TextEditor implements ActionListener {
    // Declaring property of frame
      JFrame frame;
      JMenuBar menuBar;
      JMenu file, edit;
      JMenuItem newFile, openFile, save;
      JMenuItem cut, copy, paste,selectAll,close;
      JTextArea textArea;

    TextEditor(){
            // Initalise the frame
            frame = new JFrame();

            menuBar = new JMenuBar();

            textArea = new JTextArea();

            file = new JMenu("File");
            edit = new JMenu("Edit");

            newFile = new JMenuItem("New Window");
            openFile = new JMenuItem("Open File");
            save = new JMenuItem("Save");
            // add action listener
             newFile.addActionListener(this);
             openFile.addActionListener(this);
             save.addActionListener(this);

            cut = new JMenuItem("Cut");
            copy = new JMenuItem("Copy");
            paste = new JMenuItem("Paste");
            selectAll = new JMenuItem("Select All");
            close = new JMenuItem("Close");

            // add action listener to edit menu item
           cut.addActionListener(this);
           copy.addActionListener(this);
           paste.addActionListener(this);
           selectAll.addActionListener(this);
           close.addActionListener(this);


            // add these menu item to file menu

             file.add(newFile);
             file.add(openFile);
             file.add(save);
                 // add file in menu
            menuBar.add(file);
              // add item at edit menu
             edit.add(cut);
             edit.add(copy);
             edit.add(paste);
             edit.add(selectAll);
             edit.add(close);

            // add menus to menu bar

            menuBar.add(edit);
            // set menu bar in frame
            frame.setJMenuBar(menuBar);

            frame.add(textArea);
            // Create Content Pane
//                JPanel  panel = new JPanel();
//                panel.setBorder(new EmptyBorder(5,5,5,5));
//                panel.setLayout(new BorderLayout(0,0));

                // add text area to panel

//                 panel.add(textArea, BorderLayout.CENTER);

             // create Scroll pane
//                JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

             // add scroll pane to panel

//                panel.add(scrollPane);

//                frame.add(panel);
            // set dimension
            frame.setBounds(0,0,400,400);
            frame.setTitle("Text Editor(RD)");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
   public void actionPerformed(ActionEvent actionEvent) {


            if(actionEvent.getSource() == cut){
                // perform cur operation
                textArea.cut();
            }
            if(actionEvent.getSource() == copy){
                // perform copy operation
                textArea.copy();
            }

            if(actionEvent.getSource() == paste){
                // perform paste operatiom
                textArea.paste();
            }
            if(actionEvent.getSource() == selectAll){
                // perform select operation
                textArea.selectAll();
            }
            if(actionEvent.getSource() == close){
                System.exit(0);
            }

            if(actionEvent.getSource() == openFile){
                JFileChooser fileChooser = new JFileChooser("C:"); // directory
                int chooseOption = fileChooser.showOpenDialog(null);
                if(chooseOption == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    String filePath =file.getPath();

                    try{
                        FileReader fileReader = new FileReader(filePath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String intermediate = "", output ="";

                        while((intermediate=bufferedReader.readLine())!=null) {
                            output += intermediate+"\n";
                        }
                        textArea.setText(output);
                    }
                    catch(FileNotFoundException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }
                    catch(IOException filenotFoundException){
                        filenotFoundException.printStackTrace();

                    }
                }

            }

            if(actionEvent.getSource() == save){
                   // Initialise file picker
                JFileChooser fileChooser = new JFileChooser("C:");
                   // choose the option
                int chooseOption = fileChooser.showSaveDialog(null); // shows as an approved option

                // check if clicked on save button
                if(chooseOption == JFileChooser.APPROVE_OPTION){
                    // create a new file with chosen directory path and file name
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                            // Initalise the file object
                        FileWriter fileWriter = new FileWriter(file);
                        // Initilise buffer writer
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        // write content area to the file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch(IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }

            if(actionEvent.getSource() == newFile){
                TextEditor newtextEditor = new TextEditor();
            }
    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        TextEditor textEditor = new TextEditor();
    }
}