package utils;

import main.Core;

import javax.swing.*;

public class Console {

    private static JTextArea console;
    private static Core core;

    public static void setCore(Core c) {
        core = c;
    }

    public static void setConsole(JTextArea c) {
        console = c;
    }

    public static void print(Object ...objs) {
        if (core.getState() == Core.CoreState.RUNNING) {
            //System.out.print("E"+Settings.EVENT_COUNTER+": ");
            console.append("E"+Settings.EVENT_COUNTER+": ");
        }
        for (Object m : objs) {
            //System.out.print(m);
            console.append(m.toString());
        }
        //System.out.println();
        console.append("\n");
    }

}
