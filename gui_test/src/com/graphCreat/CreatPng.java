package com.graphCreat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gui.message_dialog;

/**
 * @author admin d
 *
 */
public class CreatPng extends StringSplit {
    /**
     * cr.
     */
    public CreatPng() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param str s
     * @param strpic sp
     */
    public void printOut(final String str, final String strpic) {
        try {
            File fileOut = new File(str);
            if (!fileOut.exists()) {
                fileOut.createNewFile();
            }
            FileWriter fw = new FileWriter(fileOut);
            BufferedWriter outstream = new BufferedWriter(fw);
            String s = "digraph ha{ ";
            outstream.write(s);
            for (int i = 0; i < word_num; i++) {
                s = word_list[i] + "; ";
                outstream.write(s);
            }
            for (int i = 0; i < word_num; i++) {
                for (int j = 0; j < word_num; j++) {
                    if (w[i][j] > 0) {
                        s = word_list[i] + " -> "
                    + word_list[j] + "[label = \"" + w[i][j] + "\"];";
                        outstream.write(s);
                    }
                }
            }
            outstream.write("}");
            outstream.close();
            File directory = new File("");
            String path = directory.getCanonicalPath();
            Runtime run = Runtime.getRuntime();
            run.exec("dot -Tpng " + path + "\\" + str + " -o " + strpic);
        } catch (IOException e) {
            message_dialog message = new message_dialog();
            message.showdialog("I/O error occurred",
                "error", "Error", message_dialog.CONFIRM);
        }
    }
}
