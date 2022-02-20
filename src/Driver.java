import main.Core;
import main.SimGui;

import javax.swing.*;

public class Driver {


    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");


        Core core = new Core();
        SimGui sim = new SimGui(core);
    }

}
