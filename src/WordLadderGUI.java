import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordLadderGUI implements ActionListener{
    JFrame frame = new JFrame();

    JButton ucsButton;
    JButton gbfsButton;
    JButton astarButton;

    JTextField startWordEntry;
    JTextField endWordEntry;

    WordLadderGUI() {
        // Start Word Panel Setup ================================================================================================================
        JPanel startWordPanel = new JPanel();
        startWordPanel.setLayout(null);
        // startWordPanel.setBackground(Color.red);
        startWordPanel.setBounds(0, 0, 300, 100);

        JLabel startWordLabel = new JLabel();
        startWordLabel.setText("Masukkan Start Word: ");
        startWordLabel.setFont(new Font("Bebas", Font.PLAIN,20));
        startWordLabel.setForeground(Color.black);
        startWordLabel.setBounds(0,0, 300,50);
        startWordLabel.setHorizontalTextPosition(JLabel.CENTER);
        startWordLabel.setHorizontalAlignment(JLabel.CENTER);

        startWordEntry = new JTextField();
        startWordEntry.setBounds(50,50,200,40);
        startWordEntry.setHorizontalAlignment(JTextField.CENTER);
        startWordEntry.setFont(new Font("Bebas", Font.PLAIN,15));

        startWordPanel.add(startWordLabel);
        startWordPanel.add(startWordEntry);

        // Start Word Panel Setup ================================================================================================================

        // End Word Panel Setup ==================================================================================================================
        JPanel endWordPanel = new JPanel();
        endWordPanel.setLayout(null);
        // endWordPanel.setBackground(Color.blue);
        endWordPanel.setBounds(0, 100, 300, 100);        

        JLabel endWordLabel = new JLabel();
        endWordLabel.setText("Masukkan End Word: ");
        endWordLabel.setFont(new Font("Bebas", Font.PLAIN,20));
        endWordLabel.setForeground(Color.black);
        endWordLabel.setBounds(0,0, 300,50);
        endWordLabel.setHorizontalTextPosition(JLabel.CENTER);
        endWordLabel.setHorizontalAlignment(JLabel.CENTER);

        endWordEntry = new JTextField();
        endWordEntry.setBounds(50,50,200,40);
        endWordEntry.setHorizontalAlignment(JTextField.CENTER);
        endWordEntry.setFont(new Font("Bebas", Font.PLAIN,15));

        endWordPanel.add(endWordLabel);
        endWordPanel.add(endWordEntry);

        // End Word Panel Setup ==================================================================================================================

        // Solve Panel Setup ==================================================================================================================
        JPanel solvePanel = new JPanel();
        // solvePanel.setBackground(Color.green);
        solvePanel.setBounds(0, 200, 300, 80);

        ucsButton = new JButton();
        ucsButton.setSize(100,30);
        ucsButton.setText("UCS");
        ucsButton.setAlignmentX(JButton.CENTER);
        ucsButton.addActionListener(this);

        gbfsButton = new JButton();
        gbfsButton.setSize(100,30);
        gbfsButton.setText("GBFS");
        gbfsButton.setAlignmentX(JButton.CENTER);
        gbfsButton.addActionListener(this);
        
        astarButton = new JButton();
        astarButton.setSize(100,30);
        astarButton.setText("A*");
        astarButton.setAlignmentX(JButton.CENTER);
        astarButton.addActionListener(this);

        solvePanel.add(ucsButton);
        solvePanel.add(gbfsButton);
        solvePanel.add(astarButton);

        // Solution Panel Setup ==================================================================================================================

        // Frame setup
        frame.setTitle("Word Ladder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(300,280);
        frame.setVisible(true);

        frame.add(startWordPanel);
        frame.add(endWordPanel);
        frame.add(solvePanel);

        ImageIcon image = new ImageIcon("logo.png"); // if not found, ignore
        frame.setIconImage(image.getImage());

        // frame.getContentPane().setBackground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ucsButton) {
            System.out.println("UCS");

            String startWord = startWordEntry.getText().toUpperCase();
            String endWord = endWordEntry.getText().toUpperCase();

            if (startWord.length() == endWord.length() && Dictionary.isInDictionary(startWord) && Dictionary.isInDictionary(endWord)) {
                Pair<ArrayList<String>, Integer> solusi = new Pair<>(new ArrayList<>(), 0);

                try {
                    solusi = Algorithm.UCS(startWord, endWord);
                }
                catch (NoSolutionException ex) {
                    System.out.println(ex.getMessage());
                }
                
                frame.dispose();
                new SolutionFrame(solusi.getKey(), 1, solusi.getValue());
            }
        }
        else if (e.getSource()==gbfsButton) {
            System.out.println("GBFS");

            String startWord = startWordEntry.getText().toUpperCase();
            String endWord = endWordEntry.getText().toUpperCase();

            if (startWord.length() == endWord.length() && Dictionary.isInDictionary(startWord) && Dictionary.isInDictionary(endWord)) {
                Pair<ArrayList<String>, Integer> solusi = new Pair<>(new ArrayList<>(), 0);

                try {
                    solusi = Algorithm.GBFS(startWord, endWord);
                }
                catch (NoSolutionException ex) {
                    System.out.println(ex.getMessage());
                }

                frame.dispose();
                new SolutionFrame(solusi.getKey(), 1, solusi.getValue());
            }
        }
        else if (e.getSource()==astarButton) {
            System.out.println("A*");

            String startWord = startWordEntry.getText().toUpperCase();
            String endWord = endWordEntry.getText().toUpperCase();

            if (startWord.length() == endWord.length() && Dictionary.isInDictionary(startWord) && Dictionary.isInDictionary(endWord)) {
                Pair<ArrayList<String>, Integer> solusi = new Pair<>(new ArrayList<>(), 0);

                try {
                    solusi = Algorithm.AStar(startWord, endWord);
                }
                catch (NoSolutionException ex) {
                    System.out.println(ex.getMessage());
                }

                frame.dispose();
                new SolutionFrame(solusi.getKey(), 1, solusi.getValue());
            }
        }
    }
}

class SolutionFrame {
    private JFrame frame = new JFrame();
    Border border = BorderFactory.createLineBorder(Color.black,2);

    public SolutionFrame(ArrayList<String> path, Integer time, Integer numChecked) {
        // Info Panel Setup ==================================================================================================================
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(0, 0, 360, 120);
        // infoPanel.setBackground(Color.red);

        JLabel timeLabel = new JLabel();
        timeLabel.setText("Time: " + time + "ms");
        timeLabel.setFont(new Font("Bebas", Font.PLAIN, 16));
        timeLabel.setForeground(Color.black);
        timeLabel.setBounds(5, 5, 360, 30);

        JLabel numCheckedLabel = new JLabel();
        numCheckedLabel.setText("Jumlah node yang dikunjungi: " + numChecked);
        numCheckedLabel.setFont(new Font("Bebas", Font.PLAIN, 16));
        numCheckedLabel.setForeground(Color.black);
        numCheckedLabel.setBounds(5, 45, 360, 30);

        JLabel numStepsLabel = new JLabel();
        if (path.size() > 0) {
            numStepsLabel.setText("Steps: " + (path.size()-1));
        } else { // not found
            numStepsLabel.setText("Steps: solusi tidak ditemukan");
        }
        numStepsLabel.setFont(new Font("Bebas", Font.PLAIN, 16));
        numStepsLabel.setForeground(Color.black);
        numStepsLabel.setBounds(5, 85, 360, 30);

        infoPanel.add(timeLabel);
        infoPanel.add(numCheckedLabel);
        infoPanel.add(numStepsLabel);

        // Info Panel Setup ==================================================================================================================

        // Steps Panel Setup ==================================================================================================================
        JPanel stepsPanel = new JPanel();
        stepsPanel.setBounds(0, 120, 360, 30*path.size());
        stepsPanel.setLayout(null);
        // stepsPanel.setBackground(Color.blue);

        if (path.size() > 0) {
            String prevStep = "";
            String finalStep = path.get(path.size()-1);
            for (int i = 0; i < path.size(); i++) {
                String currentStep = path.get(i);

                JPanel currentStepPanel = new JPanel();
                currentStepPanel.setBounds(0, 30*i, 360, 30);
                currentStepPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
                // currentStepPanel.setBackground(Color.orange);

                Integer strLength = currentStep.length();
                for (int j=0; j<strLength; j++) { // create box for each char
                    JLabel currentStepLabel = new JLabel();
                    currentStepLabel.setText(""+currentStep.charAt(j));
                    currentStepLabel.setFont(new Font("Bebas", Font.PLAIN, 15));
                    currentStepLabel.setForeground(Color.black);
                    currentStepLabel.setVerticalAlignment(JLabel.CENTER);
                    currentStepLabel.setHorizontalAlignment(JLabel.CENTER);
                    currentStepLabel.setPreferredSize(new Dimension(17,20));
                    // currentStepLabel.setBorder(border);
                    currentStepLabel.setOpaque(true);

                    if (currentStep.charAt(j) == finalStep.charAt(j)) {
                        currentStepLabel.setBackground(Color.green);
                    }
                    else {
                        currentStepLabel.setBackground(Color.lightGray);
                    }

                    if (i > 0 && currentStep.charAt(j) != prevStep.charAt(j)) {
                        currentStepLabel.setBorder(border);
                    }
                    
                    currentStepPanel.add(currentStepLabel);
                }

                stepsPanel.add(currentStepPanel);

                prevStep = currentStep;
            }
        }
        
        // Steps Panel Setup ==================================================================================================================

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(360, 180+(30*path.size()));
        frame.setVisible(true);

        frame.add(infoPanel);
        frame.add(stepsPanel);
    }
}