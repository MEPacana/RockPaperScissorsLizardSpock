import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Paper;
import java.util.*;
/**
 * Created by nmenego on 10/24/16.
 * Modified by Mike 10/25/16
 *
 */
public class Game extends Frame {
    private Label yourChoice;
    private Label buffer1;
    private Label buffer2;
    private Label buffer3;
    private Label buffer4;
    private Label cscoredisp;
    private Label pscoredisp;
    private Dialog message;
    private CheckboxGroup choiceCheckbox;
    Checkbox rock;
    Checkbox paper;
    Checkbox scissors;
    Checkbox spock;
    Checkbox lizard;
    private TextArea resultDisp;
    private TextField txtPlayerScore;
    private TextField txtComputerScore;
    private Button RockPaperScissorsLizardSpock;
    private int temp = 0;

    public Game() {
        setLayout(new FlowLayout());
        // "super" Frame (a Container) sets its layout to FlowLayout, which arranges
        // the components from left-to-right, and flow to next row from top-to-bottom.

        yourChoice = new Label("Your Choice: ");
        buffer1 = new Label("                            ");
        buffer2 = new Label("                            ");
        buffer3 = new Label("                 ");
        buffer4 = new Label("              ");
        cscoredisp = new Label("Computer Score:");
        pscoredisp = new Label("Player Score:");
        choiceCheckbox = new CheckboxGroup();
        message = new Dialog(new Frame("Game Over"),"Game Over",true);
        resultDisp = new TextArea("", 6, 50);
        txtPlayerScore = new TextField("0",30);
        txtComputerScore = new TextField("0",30);
        RockPaperScissorsLizardSpock = new Button("RockPaperScissorsLizardSpock!");
        rock = new Checkbox("Rock", choiceCheckbox, true);
        paper = new Checkbox("Paper", choiceCheckbox, false);
        scissors = new Checkbox("Scissors", choiceCheckbox, false);
        spock = new Checkbox("Spock", choiceCheckbox, false);
        lizard = new Checkbox("Lizard", choiceCheckbox, false);
        // anonymous class
        RockPaperScissorsLizardSpock.addActionListener(new MyActionListener());

        // add them to container
        add(yourChoice);
        add(rock);
        add(paper);
        add(scissors);
        add(lizard);
        add(spock);
        add(resultDisp);
        add(buffer1);
        add(RockPaperScissorsLizardSpock);
        add(buffer2);
        add(buffer3);
        add(pscoredisp);
        add(txtPlayerScore);
        add(buffer4);
        add(cscoredisp);
        add(txtComputerScore);


        setTitle("Game");
        setSize(490, 500);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }


    // inner class
    class MyActionListener implements ActionListener {
        //@Override
        String ans= new String();
        Checkbox temp= new Checkbox();
        int playerscore = 0,computerscore = 0;
        private boolean flag =true;
        public void actionPerformed(ActionEvent e) {
            resultDisp.setText("");
            ans = "";
            Random rand = new Random();
            int win = 2;
            int comChoice = rand.nextInt(5) + 1;
            temp = choiceCheckbox.getSelectedCheckbox();

            if (temp.equals(rock)) {
                ans += "Result:\nPlayer chose ROCK\n" + getComString(comChoice);
                if (comChoice == 4 || comChoice == 3) {
                    win = 1;
                } else if (comChoice == 2) {
                    win = 3;
                }
            } else if (temp.equals(paper)) {
                ans += "Result:\nPlayer chose PAPER\n" + getComString(comChoice);
                if (comChoice == 1 || comChoice == 5) {
                    win = 1;
                } else if (comChoice == 2) {
                    win = 3;
                }
            } else if (temp.equals(spock)) {
                ans += "Result:\nPlayer chose SPOCK\n" + getComString(comChoice);
                if (comChoice == 1 || comChoice == 3) {
                    win = 1;
                } else if (comChoice == 5) {
                    win = 3;
                }
            } else if (temp.equals(lizard)) {
                ans += "Result:\nPlayer chose LIZARD\n" + getComString(comChoice);
                if (comChoice == 2 || comChoice == 5) {
                    win = 1;
                } else if (comChoice == 4) {
                    win = 3;
                }
            } else if (temp.equals(scissors)) {
                ans += "Result\nPlayer chose SCISSORS\n" + getComString(comChoice);
                if (comChoice == 2 || comChoice == 4) {
                    win = 1;
                } else if (comChoice == 3) {
                    win = 3;
                }
            }
            if (win == 1) {
                ans += ("\n\nYOU WON THIS ROUND\n");
                playerscore++;
            } else if (win == 2) {
                ans += ("\n\nYOU LOST THIS ROUND\n");
                computerscore++;
            } else {
                ans += ("\n\nIT IS A TIE!\n");
            }

            resultDisp.setText(ans);
            txtPlayerScore.setText(Integer.toString(playerscore));
            txtComputerScore.setText(Integer.toString(computerscore));
            if (computerscore >= 5 || playerscore >= 5) {
                String strwinner = new String();
                if (computerscore == 5) {
                    strwinner = "COMPUTER WINS!";
                } else if (playerscore == 5){
                    strwinner = "PLAYER WINS!";
                }
                computerscore = playerscore = 0;
                Button cont = new Button("Continue");
                Button exit = new Button("Exit");
                message.setLayout(new FlowLayout());
                Label winner = new Label();
                winner.setText(strwinner);
                message.add(winner);
                message.add(cont);
                message.add(exit);
                    flag = false;

                cont.addActionListener(new MyActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        message.setVisible(false);
                    }
                });
                exit.addActionListener(new MyActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        message.setVisible(false);
                    }
                });
                message.setSize(300, 200);
                message.setVisible(true);
                addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                message.remove(winner);
                message.remove(cont);
                message.remove(exit);
                txtPlayerScore.setText(Integer.toString(playerscore));
                txtComputerScore.setText(Integer.toString(computerscore));
            }
        }
    }


    private String getComString(int comChoice){
        String ans= new String();
        if (comChoice == 1){
            ans= "Computer chose ROCK\n";
        }
        else if (comChoice == 2){
            ans= "Computer chose PAPER\n";
        }
        else if(comChoice == 3){
            ans= "Computer chose SCISSORS\n";
        }
        else if(comChoice == 4){
            ans= "Computer chose LIZARD\n";
        }
        else if(comChoice == 5){
            ans= "Computer chose SPOCK\n";
        }
        return ans;
    }

    public static void main(String[] args) {
        new  Game();
    }
}
