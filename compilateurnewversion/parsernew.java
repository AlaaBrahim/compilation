package compilateurnewversion;

import java.util.Stack;

public class parsernew {

    parsernew() {
        ;
    }

    public String[] LRGS = {
            "P ->LI",
            "LI ->I pv LI",
            "LI -> ",
            "I ->D",
            "I ->lire id",
            "I ->ecrire E",
            "I ->id OPPAff E  ",
            "I ->si E OPPRel E alors LI sinon LI",
            "I ->si E OPPRel E alors LI",
            "I ->tant_que E faire LI",
            "D ->type id E ",
            "E ->T E'",
            "E' ->OPadd T E'",
            "E' -> ",
            "T ->F T'",
            "T' ->OPmult F T'",
            "T' -> ",
            "F ->( E )",
            "F ->nombre ",
            "F ->id ",
            "F ->vrai ",
            "F ->faux",

    };

    public String[][] tableSLR = {
            { "Etat", "pv", "lire", "id", "ecrire", "OPPAff", "si", "OPPRel", "alors", "sinon", "done", "tant_que",
                    "faire", "type", "OPadd", "OPmult", "(", ")", "nombre", "vrai", "faux", "$", "P", "LI", "I", "D",
                    "E", "E'", "T", "T'", "F", },
            { "0", "s3", "s5", "s7", "s6", "err", "s8", "err", "err", "err", "err", "s9", "err", "s10", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "1", "2", "4", "err", "err", "err", "err",
                    "err", },
            { "1", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "acc", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "2", "s3", "s5", "s7", "s6", "err", "s8", "err", "err", "err", "err", "s9", "err", "s10", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "11", "2", "4", "err", "err", "err", "err",
                    "err", },
            { "3", "err", "err", "err", "err", "err", "err", "err", "err", "r2", "r2", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "r2", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "4", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "5", "err", "err", "s13", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "6", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "14", "err", "15",
                    "err", "16", },
            { "7", "err", "err", "err", "err", "s22", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "8", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "23", "err", "15",
                    "err", "16", },
            { "9", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "24", "err", "15",
                    "err", "16", },
            { "10", "err", "err", "s25", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "11", "err", "err", "err", "err", "err", "err", "err", "err", "r1", "r1", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "r1", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "12", "r3", "r3", "r3", "r3", "err", "r3", "err", "err", "err", "err", "r3", "err", "r3", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "13", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "14", "s27", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "15", "r13", "err", "err", "err", "err", "err", "r13", "r13", "err", "err", "err", "r13", "err", "s29",
                    "err", "err", "r13", "err", "err", "err", "err", "err", "err", "err", "err", "err", "28", "err",
                    "err", "err", },
            { "16", "r16", "err", "err", "err", "err", "err", "r16", "r16", "err", "err", "err", "r16", "err", "r16",
                    "s31", "err", "r16", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "30", "err", },
            { "17", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "32", "err", "15",
                    "err", "16", },
            { "18", "r18", "err", "err", "err", "err", "err", "r18", "r18", "err", "err", "err", "r18", "err", "r18",
                    "r18", "err", "r18", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "19", "r19", "err", "err", "err", "err", "err", "r19", "r19", "err", "err", "err", "r19", "err", "r19",
                    "r19", "err", "r19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "20", "r20", "err", "err", "err", "err", "err", "r20", "r20", "err", "err", "err", "r20", "err", "r20",
                    "r20", "err", "r20", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "21", "r21", "err", "err", "err", "err", "err", "r21", "r21", "err", "err", "err", "r21", "err", "r21",
                    "r21", "err", "r21", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "22", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "33", "err", "15",
                    "err", "16", },
            { "23", "err", "err", "err", "err", "err", "err", "s34", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "24", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s35", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "25", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "36", "err", "15",
                    "err", "16", },
            { "26", "r4", "r4", "r4", "r4", "err", "r4", "err", "err", "err", "err", "r4", "err", "r4", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "27", "r5", "r5", "r5", "r5", "err", "r5", "err", "err", "err", "err", "r5", "err", "r5", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "28", "r11", "err", "err", "err", "err", "err", "r11", "r11", "err", "err", "err", "r11", "err", "err",
                    "err", "err", "r11", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "29", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "err", "err", "37",
                    "err", "16", },
            { "30", "r14", "err", "err", "err", "err", "err", "r14", "r14", "err", "err", "err", "r14", "err", "r14",
                    "err", "err", "r14", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "31", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "38", },
            { "32", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "s39", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "33", "s40", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "34", "err", "err", "s19", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "s17", "err", "s18", "s20", "s21", "err", "err", "err", "err", "err", "41", "err", "15",
                    "err", "16", },
            { "35", "s3", "s5", "s7", "s6", "err", "s8", "err", "err", "err", "err", "s9", "err", "s10", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "42", "2", "4", "err", "err", "err", "err",
                    "err", },
            { "36", "r10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "37", "r13", "err", "err", "err", "err", "err", "r13", "r13", "err", "err", "err", "r13", "err", "s29",
                    "err", "err", "r13", "err", "err", "err", "err", "err", "err", "err", "err", "err", "43", "err",
                    "err", "err", },
            { "38", "r16", "err", "err", "err", "err", "err", "r16", "r16", "err", "err", "err", "r16", "err", "r16",
                    "s31", "err", "r16", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "44", "err", },
            { "39", "r17", "err", "err", "err", "err", "err", "r17", "r17", "err", "err", "err", "r17", "err", "r17",
                    "r17", "err", "r17", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "40", "r6", "r6", "r6", "r6", "err", "r6", "err", "err", "err", "err", "r6", "err", "r6", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "41", "err", "err", "err", "err", "err", "err", "err", "s45", "err", "err", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "42", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s46", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "43", "r12", "err", "err", "err", "err", "err", "r12", "r12", "err", "err", "err", "r12", "err", "err",
                    "err", "err", "r12", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "44", "r15", "err", "err", "err", "err", "err", "r15", "r15", "err", "err", "err", "r15", "err", "r15",
                    "err", "err", "r15", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "45", "s3", "s5", "s7", "s6", "err", "s8", "err", "err", "err", "err", "s9", "err", "s10", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "47", "2", "4", "err", "err", "err", "err",
                    "err", },
            { "46", "r9", "r9", "r9", "r9", "err", "r9", "err", "err", "err", "err", "r9", "err", "r9", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "47", "err", "err", "err", "err", "err", "err", "err", "err", "s48", "s49", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "48", "s3", "s5", "s7", "s6", "err", "s8", "err", "err", "err", "err", "s9", "err", "s10", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "50", "2", "4", "err", "err", "err", "err",
                    "err", },
            { "49", "r8", "r8", "r8", "r8", "err", "r8", "err", "err", "err", "err", "r8", "err", "r8", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
            { "50", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s51", "err", "err", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", "err", },
            { "51", "r7", "r7", "r7", "r7", "err", "r7", "err", "err", "err", "err", "r7", "err", "r7", "err", "err",
                    "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                    "err", },
    };

    public Stack<String> stackState = new Stack<>();

    public Stack<String> analyse = new Stack<>();

    public Stack<String> stackSymbol = new Stack<>();
    String ch[] = { "si", "id", "OPPRel", "id", "alors", "id", "OPPAff", "nombre", "pv", "pv", "$" };

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

                int taillepile = 2 * tabtoken.length;

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

        while (index < tt.length) {

            if (tt[index] != null) {

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
                    String str = LRGS[Integer.parseInt(Action(s, tt[index]).substring(1))];
                    int pos = str.indexOf('>');

                    String tabparties[] = str.split("->");

                    String Partiegauche = tabparties[0];
                    // System.out.println("Partiegauche"+Partiegauche);

                    String Partiedroite = tabparties[1];
                    // System.out.println("Partiedroite"+Partiedroite);

                    String tabtoken[] = Partiedroite.split(" ");

                    int taillepile = 2 * tabtoken.length;

                    for (int i = 0; i < taillepile; i++) {
                        // for (int i = 0; i < Partiedroite.length() * 2; i++) {

                        analyse.pop();

                    }
                    String sommetpile = analyse.peek();
                    analyse.push(Partiegauche);
                    // String tetesucc = analyse.peek();
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

            } else
                index++;
        }

    }

    public String Action(String s, String a) {
        a = a.trim();
        for (int i = 1; i < tableSLR.length; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 1; j < tableSLR[0].length; j++)
                    if (tableSLR[0][j].equals(a))
                        return tableSLR[i][j];

        return "err";
    }

    public void AfficherSLR() {
        // SLR
        strInput = "";
        for (int i = index; i < ch.length; i++)
            strInput = strInput + ch[i];

        System.out.printf("%-50.50s", analyse);
        System.out.printf("%-30.30s", strInput);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void AfficherSLRnew(String[] tt) {
        // SLR

        strInput = "";
        for (int i = index; i < tt.length; i++) {
            if (tt[i] != null)
                strInput = strInput + tt[i] + " ";
        }

        System.out.printf("%-50.50s", analyse);
        System.out.printf("%-30.30s", strInput);
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
