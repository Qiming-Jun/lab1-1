package com.graphCreat;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;

import gui.message_dialog;

/**
 * @author admin d
 *
 */
public class TextAnalyze extends Bridge {
    /**
     * is.
     */
    private boolean isCORRECT = true;
    /**
     * @param isCorrect d
     */
    public void setCORRECT(final boolean isCorrect) {
      this.isCORRECT = isCorrect;
    }
    /**
     * @return f
     */
    public boolean getCORRECT() {
      return isCORRECT;
    }
    /**
     * @param name n
     */
    public TextAnalyze(final String name) {
        try {
            File fileIn = new File(name);
            FileInputStream inStream;
            inStream = new FileInputStream(fileIn);
            boolean isSpace = true;
            int type;
            int total = 0;
            while ((type = inStream.read()) != -1) {
                if (++total >= MAXN) {
                  isCORRECT = false;
                }
                char chr = (char) type;
                initialText = initialText + chr;
                if (chr >= 'a' && chr <= 'z'
                    || chr >= 'A' && chr <= 'Z') {
                    if (chr >= 'A' && chr <= 'Z') {
                      type += (int) ('a' - 'A');
                    }
                    str = str + (char) type;
                    isSpace = true;
                } else {
                    if (isSpace) {
                        str = str + (char) ('a' - 'A');
                        isSpace = false;
                    }
                }
            }
            inStream.close();
            if (isCORRECT) {
                IOtext =  str.split(" ");
                split();
                make_gragh();
                printOut("graph.dot", "graph.png");
            }
        } catch (IOException e) {
            message_dialog message = new message_dialog();
            message.showdialog("I/O error occurred",
                "error", "Error", message_dialog.CONFIRM);
        }
    }
}
