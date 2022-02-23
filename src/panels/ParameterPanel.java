package panels;

import main.SimGui;
import net.miginfocom.swing.MigLayout;
import main.Core;
import utils.Settings;
import utils.Console;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class ParameterPanel extends JPanel implements ActionListener, FocusListener, MouseListener, ChangeListener {

    private Font largeFont = new Font("Serif", Font.BOLD, 18);
    private Font mediumFont = new Font("Serif", Font.PLAIN, 16);

    private JTextField daysField;
    private JTextField secsField;
    private JTextField ticksField;

    private JTextField compXScootersField;
    private JTextField compXQualityField;
    private JSlider compXScootersSlider;
    private JSlider compXQualitySlider;

    private JTextField compYScootersField;
    private JTextField compYQualityField;
    private JSlider compYScootersSlider;
    private JSlider compYQualitySlider;

    private JTextField manhattanRateField;
    private JSlider manhattanRateSlider;

    private JTextField queensRateField;
    private JSlider queensRateSlider;

    private JTextField jonesBeachRateField;
    private JSlider jonesBeachRateSlider;

    private JTextField stonyBrookRateField;
    private JSlider stonyBrookRateSlider;

    private JCheckBox renderBox;
    private JCheckBox consoleBox;

    private JButton primaryButton;
    private JButton secondaryButton;

    private Core core;

    private SimGui simGui;

    public ParameterPanel(Core core, SimGui simGui) {
        super(new MigLayout("ins 5"));

        this.core = core;
        this.simGui = simGui;

        add(getSimulationParameterPanel(), "span, pushx, growx");
        insertLine();
        add(getCompanyXParameterPanel(), "span, pushx, growx");
        insertLine();
        add(getCompanyYParameterPanel(), "span, pushx, growx");
        insertLine();
        add(getSpawnRateParameterPanel(), "span, pushx, growx");
        insertLine();
        add(getAdvancedSettingsPanel(), "span, pushx, growx");
        insertLine();
        add(getButtonPanel(), "pushy, growy, growx, pushx");

        addMouseListener(this);

        core.setButtons(primaryButton, secondaryButton);

        System.out.println(getBackground());
    }

    private void insertLine() {
        add(new JSeparator(SwingConstants.HORIZONTAL), "span, growx");
    }

    private JPanel getButtonPanel() {
        JPanel panel = new JPanel(new MigLayout("", "", "[][]push[]"));
        panel.add(new JPanel(), "grow, pushx, wrap");

        primaryButton = new JButton("Start Simulation");
        primaryButton.setFont(largeFont);
        primaryButton.addActionListener(this);

        secondaryButton = new JButton("Restart");
        secondaryButton.setFont(largeFont);
        secondaryButton.addActionListener(this);

        panel.add(secondaryButton, "dock south, gapbottom 15, gapleft 15, gapright 15, height 40");
        panel.add(primaryButton, "dock south, gapbottom 15, gapleft 15, gapright 15, height 40");

        return panel;
    }


    private JPanel getAdvancedSettingsPanel() {

        JPanel panel = new JPanel(new MigLayout(""));

        renderBox = new JCheckBox();
        renderBox.setFont(mediumFont);
        renderBox.setSelected(true);
        renderBox.addActionListener(this);

        consoleBox = new JCheckBox();
        consoleBox.setFont(mediumFont);
        consoleBox.setSelected(false);
        consoleBox.addActionListener(this);

        panel.add(getLargeLabel("Advanced Settings:"), "span, left, gapbottom 10");
        panel.add(getMediumLabel("Render Simulation: "), "cell 0 1, width 35");
        panel.add(renderBox, "cell 1 1, gapright 5");
        panel.add(getMediumLabel("Show Console: "), "cell 0 2, width 35");
        panel.add(consoleBox, "cell 1 2, gapright 5");

        return panel;
    }


    private JPanel getSpawnRateParameterPanel() {

        JPanel panel = new JPanel(new MigLayout(""));

        manhattanRateField = getTextField(Settings.MANHATTAN_SPAWN_RATE);
        manhattanRateSlider = getSlider(0, 100, (int)(Settings.MANHATTAN_SPAWN_RATE*100));

        queensRateField = getTextField(Settings.QUEENS_SPAWN_RATE);
        queensRateSlider = getSlider(0, 100, (int)(Settings.QUEENS_SPAWN_RATE*100));

        jonesBeachRateField = getTextField(Settings.JONES_BEACH_SPAWN_RATE);
        jonesBeachRateSlider = getSlider(0, 100, (int)(Settings.JONES_BEACH_SPAWN_RATE *100));

        stonyBrookRateField = getTextField(Settings.STONY_BROOK_SPAWN_RATE);
        stonyBrookRateSlider = getSlider(0, 100, (int)(Settings.STONY_BROOK_SPAWN_RATE *100));

        panel.add(getLargeLabel("Location Spawn Rate:"), "span, left, gapbottom 10");

        panel.add(getMediumLabel("Manhattan:"), "cell 0 1, gapright 5, gapbottom 10");
        panel.add(manhattanRateField, "cell 1 1, width 35");
        panel.add(manhattanRateSlider, "cell 2 1, growx, pushx");

        panel.add(getMediumLabel("Queens:"), "cell 0 2, gapright 5, gapbottom 10");
        panel.add(queensRateField, "cell 1 2, width 35");
        panel.add(queensRateSlider, "cell 2 2, growx");

        panel.add(getMediumLabel("Jones Beach:"), "cell 0 3, gapright 5, gapbottom 10");
        panel.add(jonesBeachRateField, "cell 1 3, width 35");
        panel.add(jonesBeachRateSlider, "cell 2 3, growx");

        panel.add(getMediumLabel("Stony Brook:"), "cell 0 4, gapright 5, gapbottom 10");
        panel.add(stonyBrookRateField, "cell 1 4, width 35");
        panel.add(stonyBrookRateSlider, "cell 2 4, growx");

        return panel;
    }

    private JPanel getCompanyYParameterPanel() {

        JPanel panel = new JPanel(new MigLayout(""));

        compYScootersField = getTextField(Settings.COMP_Y_INITIAL);
        compYScootersSlider = getSlider(0, Settings.SCOOTER_MAX, Settings.COMP_Y_INITIAL);

        compYQualityField = getTextField(Settings.COMP_Y_QUALITY);
        compYQualitySlider = getSlider(0, 100, (int)(Settings.COMP_Y_QUALITY*100));

        panel.add(getLargeLabel("Scooter Company Red:"), "span, left, gapbottom 10");

        panel.add(getMediumLabel("Scooters:"), "cell 0 1, gapright 5, gapbottom 10");
        panel.add(compYScootersField, "cell 1 1, width 35");
        panel.add(compYScootersSlider, "cell 2 1, growx, pushx");

        panel.add(getMediumLabel("Quality:"), "cell 0 2, gapright 5");
        panel.add(compYQualityField, "cell 1 2, width 35");
        panel.add(compYQualitySlider, "cell 2 2, growx");

        return panel;
    }

    private JPanel getCompanyXParameterPanel() {
        JPanel panel = new JPanel(new MigLayout(""));

        compXScootersField = getTextField(Settings.COMP_X_INITIAL);
        compXScootersSlider = getSlider(0, Settings.SCOOTER_MAX, Settings.COMP_X_INITIAL);

        compXQualityField = getTextField(Settings.COMP_X_QUALITY);
        compXQualitySlider = getSlider(0, 100, (int)(Settings.COMP_X_QUALITY*100));

        panel.add(getLargeLabel("Scooter Company Blue:"), "span, left, gapbottom 10");

        panel.add(getMediumLabel("Scooters:"), "cell 0 1, gapright 5, gapbottom 10");
        panel.add(compXScootersField, "cell 1 1, width 35");
        panel.add(compXScootersSlider, "cell 2 1, growx, pushx");

        panel.add(getMediumLabel("Quality:"), "cell 0 2, gapright 5");
        panel.add(compXQualityField, "cell 1 2, width 35");
        panel.add(compXQualitySlider, "cell 2 2, growx");

        return panel;
    }

    private JPanel getSimulationParameterPanel() {
        JPanel panel = new JPanel(new MigLayout("ins 5"));

        daysField = getTextField(Settings.NUM_OF_DAYS);
        secsField = getTextField(Settings.SECS_PER_DAY);
        ticksField = getTextField(Settings.TICKS_PER_HOUR);

        panel.add(getLargeLabel("Simulation Parameters:"), "span, left, gapbottom 10");

        panel.add(getMediumLabel("Simulated Days:"), "cell 0 1, gapright 5, gapbottom 10");
        panel.add(daysField, "cell 1 1, width 35");
        panel.add(getMediumLabel("days"), "cell 2 1, growx, pushx");

        panel.add(getMediumLabel("Time per day:"), "cell 0 2, gapright 5, gapbottom 10");
        panel.add(secsField, "cell 1 2, width 35");
        panel.add(getMediumLabel("secs/day"), "cell 2 2, growx, pushx");

        panel.add(getMediumLabel("Number of events:"), "cell 0 3, gapright 5, gapbottom 10");
        panel.add(ticksField, "cell 1 3, width 35");
        panel.add(getMediumLabel("events/hour"), "cell 2 3, growx, pushx");

        return panel;
    }

    private JSlider getSlider(int min, int max, int start) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, start);
        slider.addChangeListener(this);
        return slider;
    }

    private JTextField getTextField(Object amount) {
        JTextField field = new JTextField(String.valueOf(amount));
        field.addFocusListener(this);
        field.setFont(mediumFont);
        return field;
    }

    private JLabel getMediumLabel(String s) {
        JLabel title = new JLabel(s);
        title.setFont(mediumFont);
        return title;
    }

    private JLabel getLargeLabel(String s) {
        JLabel title = new JLabel(s);
        title.setFont(largeFont);
        return title;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            this.core.onButton((JButton)e.getSource());
        }

        if (e.getSource() instanceof JCheckBox) {
            JCheckBox box = (JCheckBox) e.getSource();
            this.simGui.swap(box.isSelected());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object source = e.getSource();

        if (source.equals(daysField)) {
            String data = daysField.getText();
            try {
                int days = Integer.parseInt(data);
                if (days <= 0 || days >= 15) {
                    daysField.setText(String.valueOf(Settings.NUM_OF_DAYS));
                    Console.print("Error: days field should be between 1 and 15. data="+data+"");
                }else{
                    Settings.NUM_OF_DAYS = days;
                    Console.print("Updated simulate days to: "+days+" days");
                }
            } catch (Exception ex) {
                daysField.setText(String.valueOf(Settings.NUM_OF_DAYS));
                Console.print("Error: unable to parse days field value data="+data);
            }
        }

        if (source.equals(secsField)) {
            String data = secsField.getText();
            try {
                int seconds = Integer.parseInt(data);
                if (seconds < 5 || seconds > 30) {
                    secsField.setText(String.valueOf(Settings.SECS_PER_DAY));
                    Console.print("Error: seconds field should be between 5 and 30. data="+data+"");
                }else{
                    Settings.SECS_PER_DAY = seconds;
                    Console.print("Updated simulate seconds per day to: "+seconds+" seconds");
                }
            } catch (Exception ex) {
                secsField.setText(String.valueOf(Settings.SECS_PER_DAY));
                Console.print("Error: unable to parse seconds field value data="+data);
            }
        }

        if (source.equals(ticksField)) {
            String data = ticksField.getText();
            try {
                int ticks = Integer.parseInt(data);
                if (ticks < 1 || ticks > 48) {
                    ticksField.setText(String.valueOf(Settings.TICKS_PER_HOUR));
                    Console.print("Error: events field should be between 1 and 48. data="+data+"");
                }else{
                    Settings.TICKS_PER_HOUR = ticks;
                    Console.print("Updated simulate events per hour to: "+ticks+" events");
                }
            } catch (Exception ex) {
                ticksField.setText(String.valueOf(Settings.TICKS_PER_HOUR));
                Console.print("Error: unable to parse events field value data="+data);
            }
        }

        if (source.equals(compXScootersField)) {
            String data = compXScootersField.getText();
            try {
                int scooters = Integer.parseInt(data);

                if (scooters < 0 || scooters > Settings.SCOOTER_MAX) {
                    compXScootersField.setText(String.valueOf(Settings.COMP_X_INITIAL));
                    Console.print("Error: scooter stock should be between 0 and "+ Settings.SCOOTER_MAX+". data="+data+"");
                } else {
                    Settings.COMP_X_INITIAL = scooters;
                    compXScootersSlider.setValue(scooters);
                    Console.print("Updated company X's initial supply to: "+scooters+" scooters");
                }
            } catch (Exception ex) {
                compXScootersField.setText(String.valueOf(Settings.COMP_X_INITIAL));
                Console.print("Error: unable to parse company X scooter field value data="+data);
            }
        }

        if (source.equals(compYScootersField)) {
            String data = compYScootersField.getText();
            try {
                int scooters = Integer.parseInt(data);

                if (scooters < 0 || scooters > Settings.SCOOTER_MAX) {
                    compYScootersField.setText(String.valueOf(Settings.COMP_Y_INITIAL));
                    Console.print("Error: scooter stock should be between 0 and "+ Settings.SCOOTER_MAX+". data="+data+"");
                } else {
                    Settings.COMP_X_INITIAL = scooters;
                    compYScootersSlider.setValue(scooters);
                    Console.print("Updated company Y's initial supply to: "+scooters+" scooters");
                }
            } catch (Exception ex) {
                compYScootersField.setText(String.valueOf(Settings.COMP_Y_INITIAL));
                Console.print("Error: unable to parse company Y scooter field value data="+data);
            }
        }

        if (source.equals(compXQualityField)) {
            String data = compXQualityField.getText();
            try {
                double quality = Double.parseDouble(data);

                if (quality < 0 || quality > 1.0) {
                    compXQualityField.setText(String.valueOf(Settings.COMP_X_QUALITY));
                    Console.print("Error: scooter quality should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.COMP_X_QUALITY = quality;
                    compXQualitySlider.setValue((int)(quality*100));
                    Console.print("Updated company X's initial supply to: "+quality+" scooters");
                }
            } catch (Exception ex) {
                compXQualityField.setText(String.valueOf(Settings.COMP_X_QUALITY));
                Console.print("Error: unable to parse company X scooter field value data="+data);
            }
        }

        if (source.equals(compYQualityField)) {
            String data = compYQualityField.getText();
            try {
                double quality = Double.parseDouble(data);

                if (quality < 0 || quality > 1.0) {
                    compYQualityField.setText(String.valueOf(Settings.COMP_Y_QUALITY));
                    Console.print("Error: scooter quality should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.COMP_Y_QUALITY = quality;
                    compYQualitySlider.setValue((int)(quality*100));
                    Console.print("Updated company Y's initial supply to: "+quality+" scooters");
                }
            } catch (Exception ex) {
                compYQualityField.setText(String.valueOf(Settings.COMP_Y_QUALITY));
                Console.print("Error: unable to parse company Y scooter field value data="+data);
            }
        }

        if (source.equals(manhattanRateField)) {
            String data = manhattanRateField.getText();
            try {
                double rate = Double.parseDouble(data);

                if (rate < 0 || rate > 1.0) {
                    manhattanRateField.setText(String.valueOf(Settings.MANHATTAN_SPAWN_RATE));
                    Console.print("Error: spawn rate should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.MANHATTAN_SPAWN_RATE = rate;
                    manhattanRateSlider.setValue((int)(rate*100));
                    Console.print("Updated manhattan's spawn rate to: "+rate);
                }
            } catch (Exception ex) {
                manhattanRateField.setText(String.valueOf(Settings.MANHATTAN_SPAWN_RATE));
                Console.print("Error: unable to parse manhattan's spawn rate. data="+data);
            }
        }


        if (source.equals(queensRateField)) {
            String data = queensRateField.getText();
            try {
                double rate = Double.parseDouble(data);

                if (rate < 0 || rate > 1.0) {
                    queensRateField.setText(String.valueOf(Settings.QUEENS_SPAWN_RATE));
                    Console.print("Error: spawn rate should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.MANHATTAN_SPAWN_RATE = rate;
                    queensRateSlider.setValue((int)(rate*100));
                    Console.print("Updated queens's spawn rate to: "+rate);
                }
            } catch (Exception ex) {
                queensRateField.setText(String.valueOf(Settings.QUEENS_SPAWN_RATE));
                Console.print("Error: unable to parse queens's spawn rate. data="+data);
            }
        }


        if (source.equals(jonesBeachRateField)) {
            String data = jonesBeachRateField.getText();
            try {
                double rate = Double.parseDouble(data);

                if (rate < 0 || rate > 1.0) {
                    jonesBeachRateField.setText(String.valueOf(Settings.JONES_BEACH_SPAWN_RATE));
                    Console.print("Error: spawn rate should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.JONES_BEACH_SPAWN_RATE = rate;
                    jonesBeachRateSlider.setValue((int)(rate*100));
                    Console.print("Updated jones beach's spawn rate to: "+rate);
                }
            } catch (Exception ex) {
                jonesBeachRateField.setText(String.valueOf(Settings.JONES_BEACH_SPAWN_RATE));
                Console.print("Error: unable to parse jones beach's spawn rate. data="+data);
            }
        }


        if (source.equals(stonyBrookRateField)) {
            String data = stonyBrookRateField.getText();
            try {
                double rate = Double.parseDouble(data);

                if (rate < 0 || rate > 1.0) {
                    stonyBrookRateField.setText(String.valueOf(Settings.STONY_BROOK_SPAWN_RATE));
                    Console.print("Error: spawn rate should be between 0 and 1.0. data="+data+"");
                } else {
                    Settings.STONY_BROOK_SPAWN_RATE = rate;
                    stonyBrookRateSlider.setValue((int)(rate*100));
                    Console.print("Updated stony brook's spawn rate to: "+rate);
                }
            } catch (Exception ex) {
                stonyBrookRateField.setText(String.valueOf(Settings.STONY_BROOK_SPAWN_RATE));
                Console.print("Error: unable to parse stony brook's spawn rate. data="+data);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.grabFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    int i = 0;
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();

        if (slider.equals(compXScootersSlider)) {
            Settings.COMP_X_INITIAL = slider.getValue();
            compXScootersField.setText(String.valueOf(slider.getValue()));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated company X's initial supply to: "+slider.getValue()+" scooters");
            }
        }

        if (slider.equals(compXQualitySlider)) {
            double quality = slider.getValue()/100.0;
            Settings.COMP_X_QUALITY = quality;
            compXQualityField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated company X's quality to: "+(quality));
            }
        }

        if (slider.equals(compYScootersSlider)) {
            Settings.COMP_Y_INITIAL = slider.getValue();
            compYScootersField.setText(String.valueOf(slider.getValue()));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated company Y's initial supply to: "+slider.getValue()+" scooters");
            }
        }

        if (slider.equals(compYQualitySlider)) {
            double quality = slider.getValue()/100.0;
            Settings.COMP_Y_QUALITY = quality;
            compYQualityField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated company Y's quality to: "+(quality));
            }
        }

        if (slider.equals(manhattanRateSlider)) {
            double quality = slider.getValue()/100.0;
            Settings.MANHATTAN_SPAWN_RATE = quality;
            manhattanRateField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated manhattan's spawn rate to: "+(quality));
            }
        }

        if (slider.equals(queensRateSlider)) {
            double quality = slider.getValue()/100.0;
            Settings.QUEENS_SPAWN_RATE = quality;
            queensRateField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated queens's spawn rate to: "+(quality));
            }
        }

        if (slider.equals(jonesBeachRateSlider)) {
            double quality = slider.getValue()/100.0;
            Settings.JONES_BEACH_SPAWN_RATE = quality;
            jonesBeachRateField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated jones beach's spawn rate to: "+(quality));
            }
        }

        if (slider.equals(stonyBrookRateSlider)) {
            double quality = slider.getValue()/100.0;
            Settings.STONY_BROOK_SPAWN_RATE = quality;
            stonyBrookRateField.setText(String.valueOf(quality));
            if (!slider.getValueIsAdjusting()) {
                Console.print("Updated stony brook's spawn rate to: "+(quality));
            }
        }

    }

}
