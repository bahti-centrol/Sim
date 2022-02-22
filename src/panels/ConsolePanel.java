package panels;

import main.SimGui;
import main.Travis;
import net.miginfocom.swing.MigLayout;
import utils.Console;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

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


    private void execute(String text){
        if (text.equals("clear")) {
            this.textArea.setText("");
        }

        if (text.equals("die")) {
            System.exit(0);
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
