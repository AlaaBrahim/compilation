package compilateurnewversion;

import java.util.Stack;

public class parsernew {

    parsernew() {
        ;
    }

    public String[] LRGS = {

            "S ->I",
            "S ->E",
            "I ->i = E",
            "E ->T",
            "E ->T + E",
            "E ->T - E",
            "T ->F",
            "T ->F * T",
            "T ->F / T",
            "F ->( E )",
            "F ->id",
            "F ->- F",
            "F ->+ F",
            "F ->n"
    };

    public String[][] tableSLR = {
            { "Etat", "i", "=", "+", "-", "*", "/", "(", ")", "id", "n", "$", "S", "I", "E", "T", "F", },
            { "0", "s2", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "1", "err", "err",
                    "err", },
            { "1", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "acc", "err", "err", "err",
                    "err", "err", },
            { "2", "err", "s3", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "3", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "4", "5",
                    "6", },
            { "4", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "r2", "err", "err", "err",
                    "err", "err", },
            { "5", "err", "err", "s12", "s13", "err", "err", "err", "r3", "err", "err", "r3", "err", "err", "err",
                    "err", "err", },
            { "6", "err", "err", "r6", "r6", "s14", "s15", "err", "r6", "err", "err", "r6", "err", "err", "err", "err",
                    "err", },
            { "7", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "16", "5",
                    "6", },
            { "8", "err", "err", "r10", "r10", "r10", "r10", "err", "r10", "err", "err", "r10", "err", "err", "err",
                    "err", "err", },
            { "9", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "err", "err",
                    "17", },
            { "10", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "err",
                    "err", "18", },
            { "11", "err", "err", "r13", "r13", "r13", "r13", "err", "r13", "err", "err", "r13", "err", "err", "err",
                    "err", "err", },
            { "12", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "19", "5",
                    "6", },
            { "13", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "20", "5",
                    "6", },
            { "14", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "err", "21",
                    "6", },
            { "15", "err", "err", "s10", "s9", "err", "err", "s7", "err", "s8", "s11", "err", "err", "err", "err", "22",
                    "6", },
            { "16", "err", "err", "err", "err", "err", "err", "err", "s23", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "17", "err", "err", "r11", "r11", "r11", "r11", "err", "r11", "err", "err", "r11", "err", "err", "err",
                    "err", "err", },
            { "18", "err", "err", "r12", "r12", "r12", "r12", "err", "r12", "err", "err", "r12", "err", "err", "err",
                    "err", "err", },
            { "19", "err", "err", "err", "err", "err", "err", "err", "r4", "err", "err", "r4", "err", "err", "err",
                    "err", "err", },
            { "20", "err", "err", "err", "err", "err", "err", "err", "r5", "err", "err", "r5", "err", "err", "err",
                    "err", "err", },
            { "21", "err", "err", "r7", "r7", "err", "err", "err", "r7", "err", "err", "r7", "err", "err", "err", "err",
                    "err", },
            { "22", "err", "err", "r8", "r8", "err", "err", "err", "r8", "err", "err", "r8", "err", "err", "err", "err",
                    "err", },
            { "23", "err", "err", "r9", "r9", "r9", "r9", "err", "r9", "err", "err", "r9", "err", "err", "err", "err",
                    "err", },
    };

    public Stack<String> stackState = new Stack<>();

    public Stack<String> analyse = new Stack<>();

    public Stack<String> stackSymbol = new Stack<>();
    String ch[] = { "i", "=", "n", "$" };

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
                String str = LRGS[Integer.parseInt(Action(s, ch[index]).substring(1))];
                int pos = str.indexOf('>');

                String tabparties[] = str.split("->");

                String Partiegauche = tabparties[0];
                // System.out.println("Partiegauche"+Partiegauche);

                String Partiedroite = tabparties[1];
                // System.out.println("Partiedroite"+Partiedroite);

                String tabtoken[] = Partiedroite.split(" ");

                for (String string : tabtoken) {
                    System.out.println("tabtoken : " + string);
                }

                int taillepile = 2 * tabtoken.length;

                System.out.println("taillepile : " + taillepile);

                for (int i = 0; i < taillepile; i++) {
                    // for (int i = 0; i < Partiedroite.length() * 2; i++) {

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
            else if (Action(s, ch[index]) == "acc") {
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
            else if (Action(s, tt[index]) == "acc") {
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
        for (int i = 1; i < tableSLR.length; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 1; j < tableSLR[0].length; j++)
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

    public static void main(String[] args) {
        parsernew p = new parsernew();
        p.analyzeSLnew();

    }
}
