package panels;

import main.SimGui;
import main.Travis;
import net.miginfocom.swing.MigLayout;
import utils.Console;
import utils.Customer;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ConsolePanel extends JPanel implements KeyListener {

    private JTextArea textArea;

    private StringBuilder builder;

    private SimGui parent;
    public ConsolePanel(SimGui parent) {
        super(new MigLayout(""));

        this.parent = parent;

        builder = new StringBuilder();

        textArea = new JTextArea();
        textArea.setFont(new Font("Lucida Console", Font.PLAIN, 12));
        textArea.setEditable(true);
        textArea.setBackground(new Color(244, 242, 232));
        textArea.addKeyListener(this);

        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setAutoscrolls(true);

        add(scroll, "growx, pushy, pushx, growy");

        Console.setConsole(textArea);
    }

    private void doTravis() {
        final JFrame stormi = Travis.getTravis();
        final ConsolePanel self = this;

        new Thread() {
            public void run() {
                self.parent.frame.setVisible(false);
                stormi.setVisible(true);

                try {
                    Thread.sleep(150);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                stormi.setVisible(false);
                stormi.dispose();
                self.parent.frame.setVisible(true);
            }
        }.start();
    }


    private void saveFile(String fileName) {
        Console.print("Attempting to save data to ", fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/" + fileName)));
            List<Customer> customers = this.parent.core.data.getArchives();

            writer.write("Customer_ID,Preference Rate Towards Company_X,Rating Given,Company Chosen,Starting Location,Ending Location,Started Time,Ending Time,Unavailable Scooter,BREAKDOWN,Breakdown Time\n");
            for (Customer customer : customers) {
                writer.write(customer.csv() + "\n");
            }

            writer.close();
            Console.print("Finished saving file to ", fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String text){
        if (text.equals("clear")) {
            this.textArea.setText("");
        }

        if (text.equals("die")) {
            System.exit(0);
        }

        if (text.startsWith("save ")) {
            String fileName = text.substring(5).trim();
            this.saveFile(fileName);
        }

        if (text.equals("travis")) {
            doTravis();
        }

        if (text.startsWith("ls")) {
            File file = new File("./" + text.substring(2).trim());
            for (File f : file.listFiles()) {
                Console.print("> ",f.getName());
            }
        }


    }

    private void process() {
        String text = builder.toString().trim();
        execute(text);

        builder = new StringBuilder();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            process();
        }else{
            builder.append(e.getKeyChar());
        }
    }


}
