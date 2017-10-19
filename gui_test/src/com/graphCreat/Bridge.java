package com.graphCreat;

import java.util.Random;
import java.util.Stack;
/**
 * @author admin
 *t
 */
public class Bridge extends CreatPng {
  /**
   * Fg.
   */
  private int firstword, secondword;
    /**
     * g.
     */
    private boolean[] p = new boolean[MAXN];
    /**
     * @param word1 word1
     * @param word2 word1
     * @return r
     */
    private static final int BRINUM = 1000;
    /**
     * @param w1 r
     */
    public void setFirstword(final int w1) {
      this.firstword = w1;
    }
    /**
     * @return f
     */
    public int getFirstword() {
      return firstword;
    }
    /**
     * @param w2 f
     */
    public void setSecondword(final int w2) {
      this.secondword = w2;
    }
    /**
     * @return f
     */
    public int getSecondword() {
      return secondword;
    }
    /**
     * @param word1 d
     * @param word2 d
     * @return d
     */
    public String queryBridgeWords(final String word1, final String word2) {
        int t1 = -1, t2 = -1;
//        int[] bri = new int[10];
        int[] bri = new int[BRINUM];
        int brinum = 0;
        String tempstr = "";
        for (int i = 0; i < word_num; i++) {
            p[i] = false;
        }
        for (int i = 0; i < word_num; i++) {
            if (word1.equals(word_list[i])) {    //找到第一个词
                t1 = i;
                firstword = i;
            }
            if (word2.equals(word_list[i])) {
                t2 = i;
                secondword = i;
            }
        }
        if (t1 == -1 || t2 == -1) {
            if (t1 == -1) {
                tempstr += "No word1 in Graph!";
            }
            if (t2 == -1) {
                tempstr += "No word2 in Graph!";
            }
            return tempstr;
        } else {
            for (int i = 0; i < word_num; i++) {
                if (w[t1][i] > 0 && w[i][t2] > 0) {
                    bri[brinum] = i;
                    brinum++;
                }
            }
            if (brinum == 0) {
                tempstr +=
                    "No Bridge words from word1 to word2!";
            } else if (brinum == 1) {
                tempstr = tempstr
                    + "The Bridge word from word1 to word2 is: "
                    + word_list[bri[0]];
                p[bri[0]] = true;
            } else {
                 tempstr +=
                     "The Bridge words from"
                     + "word1 to word2 are: ";
                for (int i = 0; i < brinum - 1; i++) {
                    tempstr = tempstr
                        + word_list[bri[i]] + ",";
                    p[bri[i]] = true;
                }
                tempstr = tempstr + "and "
                + word_list[bri[brinum - 1]];
                p[bri[brinum - 1]] = true;
            }
            return tempstr;
        }
    }
    /**
     * @param inputText in
     * @return r
     */
    public String generateNewText(final String inputText) {
        String[] usertxt;
        usertxt = inputText.split("\\s+");
        String tempstr = "";
        for (int j = 0; j < usertxt.length - 1; j++) {
            int t1 = -1, t2 = -1;
            int[] bri = new int[BRINUM];
            int brinum = 0;
            for (int i = 0; i < word_num; i++) {
        if (usertxt[j].equals(word_list[i])) {        //找到第一个词
                  t1 = i;
        }
              if (usertxt[j + 1].equals(word_list[i])) {    //找到第二个词
                  t2 = i;
              }
            }
            if (t1 == -1 | t2 == -1) {    //不存在单词继续循环
                tempstr += usertxt[j];
                tempstr += " ";
            } else {
                for (int i = 0; i < word_num; i++) {
                    if (w[t1][i] > 0 && w[i][t2] > 0) {
                        bri[brinum] = i;
                        brinum++;
                    }
            }
                if (brinum == 0) {
                    tempstr += usertxt[j];
                    tempstr += " ";
                } else {
                    Random random = new Random();
                    tempstr += usertxt[j];
                    tempstr += " ";
                    tempstr +=
                     word_list[bri[random.nextInt(brinum)]];
                    tempstr += " ";
                }
        }
    }
        tempstr += usertxt[usertxt.length - 1];
        return tempstr;
    }
    /**
     * pp.
     */
    public void path() {
        for (int i = 0; i < word_num; i++) {
            for (int j = 0; j < word_num; j++) {
                if (w[i][j] == 0) {
                    A[i][j] =    MAXN;
                } else {
                    A[i][j] = 1;
                }
                Path[i][j] = -1;
            }
        }
        for (int k = 0; k < word_num; k++) {
          for (int i = 0; i < word_num; i++) {
             for (int j = 0; j < word_num; j++) {
                 if (A[i][j] > (A[i][k] + A[k][j])) {
                      A[i][j] = A[i][k] + A[k][j];
                      Path[i][j] = k;
                 }
             }
          }
        }
    }
    /**
     * @param word1 w
     * @param word2 w
     * @return w
     */
    public String calcShortestPath(final String word1, final String word2) {
        String tempstr = "";
        path();
        int t1 = -1, t2 = -1;
        for (int i = 0; i < word_num; i++) {
            p[i] = false;
        }
        for (int i = 0; i < word_num; i++) {
            if (word1.equals(word_list[i])) {        //找到第一个词
                t1 = i;
                firstword = i;
                p[i] = true;
            }
            if (word2.equals(word_list[i])) {
                t2 = i;
                p[i] = true;
                secondword = i;
            }
        }
        Stack<String> sk = new Stack<String>();
        if (t2 != -1 && t1 != -1) {
            sk.push(word2);
            if (t1 != t2) {
                if (A[t1][t2] == MAXN) {
                    return "Out of reach ";
                } else {
                    int k = t2;
                      k = Path[t1][k];
                    while (k != -1) {
                       sk.push(word_list[k]);
                       p[k] = true;
                       k = Path[t1][k];
                   }
                }
            }
            sk.push(word1);
        }
        while (!sk.empty()) {
            tempstr = tempstr + sk.pop() + " ";
        }
        return tempstr;
    }
    /**
     * @param word 1
     * @return e
     */
    public String calcShortestPath(final String word) {
        path();
        String tempstr = "";
        int t1 = -1;
        for (int i = 0; i < word_num; i++) {
            if (word.equals(word_list[i])) {        //找到第一个词
                t1 = i;
            }
        }
        Stack<String> sk = new Stack<String>();
        if (t1 != -1) {
          for (int i = 0; i < word_num; i++) {
              if (i == t1) {
                  continue;
              } else {
                if (A[t1][i] == MAXN) {
                  tempstr  = tempstr + word
                      + " out of reach "
                      + word_list[i] + "\n";
                } else {
                  sk.push(word_list[i]);
                  int k = i;
                    k = Path[t1][k];
                    while (k != -1) {
               sk.push(word_list[k]);
               k = Path[t1][k];
            }
                    sk.push(word);
                    while (!sk.empty()) {
                      tempstr = tempstr + sk.pop() + " ";
                      }
                    tempstr += "\n";
                }
              }
          }
        }
        return tempstr;
    }
    /**
     * @return ee
     */
    public String randomWalk() {
        for (int i = 0; i < word_num; i++) {
            for (int j = 0; j < word_num; j++) {
                if (w[i][j] == 0) {
                    A[i][j] =    MAXN;
                } else {
                    A[i][j] = 1;
                }
            }
        }
        String tmpstr = "";
        int t1;
        Random random = new Random();
        t1 = random.nextInt(word_num);
        int edgeNum = 0;    //存储边数
        int[] edge = new int[word_num];    //存储边
        tmpstr = tmpstr + word_list[t1] + " ";
        int tmpedge;
        while (true) {
            edgeNum = 0;
            for (int i = 0; i < word_num; i++) {
                if (A[t1][i] == 1) {
                    edge[edgeNum] = i; //在边数组内存入邻接顶点编号
                    edgeNum++;
                }
            }
            if (edgeNum == 0) {
                break;
            } else {
                tmpedge = edge[random.nextInt(edgeNum)];
                A[t1][tmpedge] = MAXN;
                t1 = tmpedge;
                tmpstr = tmpstr + word_list[t1] + " ";
            }
        }
        return tmpstr;
    }
}

