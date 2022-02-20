package panels;

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

    public ConsolePanel() {
        super(new MigLayout(""));

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

    private void execute(String text){
        if (text.equals("clear")) {
            this.textArea.setText("");
        }

        if (text.equals("ls")) {
            File file = new File(".");
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
