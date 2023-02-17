package compilateurnewversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class parserIF3 {

    public String[] LRGS = { "S->AB",
            "A->aA",
            "A->b",
            "B->bB",
            "B->a"
    };

    public String[][] tableSLR = {
            { "etat/VT", "a", "b", "$", "S", "A", "B" },
            { "0", "s3", "s4", "err", "1", "2", "err" },
            { "1", "err", "err", "ACC", "err", "err", "err" },
            { "2", "s7", "s6", "err", "err", "err", "5" },
            { "3", "s3", "s4", "err", "err", "8", "err" },
            { "4", "r4", "r4", "err", "err", "err", "err" },
            { "5", "err", "err", "r2", "err", "err", "err" },
            { "6", "s7", "s6", "err", "err", "err", "9" },
            { "7", "err", "err", "r6", "err", "err", "err" },
            { "8", "r3", "r3", "err", "err", "err", "err" },
            { "9", "err", "err", "r5", "err", "err", "err" },
    };

    public Stack<String> stackState = new Stack<>();

    public Stack<String> analyse = new Stack<>();

    public Stack<String> stackSymbol = new Stack<>();
    String ch[] = { "a", "b", "b", "a", "$" };
    public String strInput;

    public String action = "";

    int index = 0;

    public void analyzeSLnew() {

        action = "";
        index = 0;

        analyse.push("0");

        System.out.println("********pile     Entrée   Action			   ");
        this.AfficherSLR();

        while (index < ch.length)

        {

            String s = analyse.peek();

            // String act=Action(s,ch[index]);

            if (Action(s, ch[index]).charAt(0) == 's') {

                analyse.push(ch[index]);
                analyse.push(Action(s, ch[index]).substring(1));

                index++;
                action = "shift";

                AfficherSLR();
            }
            // Réduction
            else if (Action(s, ch[index]).charAt(0) == 'r') {
                //
                String str = LRGS[Integer.parseInt(Action(s, ch[index]).substring(1)) - 1];
                int pos = str.indexOf('>');

                String tabparties[] = str.split("->");

                String Partiegauche = tabparties[0];
                // System.out.println("Partiegauche"+Partiegauche);

                String Partiedroite = tabparties[1];
                // System.out.println("Partiedroite"+Partiedroite);

                String tabtoken[] = Partiedroite.split(" ");
                int taillepile = tabtoken.length + tabtoken.length;

                for (int i = 0; i < taillepile; i++) {

                    analyse.pop();

                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                // String tetesucc = analyse.peek();

                analyse.push(Action(sommetpile, Partiegauche));

                action = "reduce:" + str;
                AfficherSLR();
            }
            // acceptation
            else if (Action(s, ch[index]) == "ACC") {
                System.out.println("analyze SLR successfully");
                break;
            }

            else
            // erreur
            {

                // System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index);
                System.out.println("analyze SLR failled");
                break;
            }

        }

    }

    public void analyzeSLnew(String[] tt) {

        action = "";
        index = 0;

        analyse.push("0");

        System.out.println("********pile     	    Entrée            Action***********");
        this.AfficherSLRnew(tt);

        while (index < tt.length)

        {

            // String s = stackState.peek();

            String s = analyse.peek();

            String act = Action(s, tt[index]);

            if (Action(s, tt[index]).charAt(0) == 's') {

                // stackState.push(Action(s, ch[index]).substring(1));
                // stackSymbol.push(ch[index]);

                analyse.push(tt[index]);
                analyse.push(Action(s, tt[index]).substring(1));

                index++;
                action = "shift ";

                AfficherSLRnew(tt);
            }
            // Réduction
            else if (Action(s, tt[index]).charAt(0) == 'r') {
                //
                String str = LRGS[Integer.parseInt(Action(s, tt[index]).substring(1)) - 1];
                int pos = str.indexOf('>');

                String tabparties[] = str.split("->");

                String Partiegauche = tabparties[0];
                // System.out.println("Partiegauche"+Partiegauche);

                String Partiedroite = tabparties[1];
                // System.out.println("Partiedroite"+Partiedroite);

                String tabtoken[] = Partiedroite.split(" ");
                int taillepile = tabtoken.length + tabtoken.length;

                for (int i = 0; i < taillepile; i++) {

                    analyse.pop();

                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                String tetesucc = analyse.peek();

                analyse.push(Action(sommetpile, Partiegauche));

                action = "reduce:" + str;
                AfficherSLRnew(tt);
            }
            // acceptation
            else if (Action(s, tt[index]) == "ACC") {
                System.out.println("analyze SLR successfully");
                break;
            }

            else
            // erreur
            {

                // System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index);
                System.out.println("analyze SLR failled");
                break;
            }

        }

    }

    public String Action(String s, String a) {
        for (int i = 1; i < 11; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 1; j < 7; j++)
                    if (tableSLR[0][j].equals(a))
                        return tableSLR[i][j];
        return "err";
    }

    public void AfficherSLR() {
        // SLR

        String ss = "-------";
        String ss1 = "-------";
        int taillepile = analyse.size();
        int taillepilediv2 = taillepile / 2;
        for (int i = 0; i < taillepilediv2; i++)
            ss = ss + "-------";
        int tailleinput = ch.length;
        for (int i = 0; i < tailleinput; i++)
            ss1 = ss1 + "-------";

        strInput = "";
        for (int i = index; i < ch.length; i++)
            strInput = strInput + ch[i];

        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput + ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void AfficherSLRnew(String[] tt) {
        // SLR

        String ss = "-------";
        String ss1 = "-------";
        int taillepile = analyse.size();
        int taillepilediv2 = taillepile / 2;
        for (int i = 0; i < taillepilediv2; i++)
            ss = ss + "-------";
        int tailleinput = tt.length;
        for (int i = 0; i < tailleinput; i++)
            ss1 = ss1 + "-------";

        strInput = "";
        for (int i = index; i < tt.length; i++)
            strInput = strInput + tt[i];

        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput + ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void ouput() {

        System.out.println("**********Tableau SLR¨********");

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }

}
