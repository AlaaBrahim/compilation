package compilateurnewversion;

import java.util.Stack;

public class parsernew {

        parsernew() {
                ;
        }

        public String[] LRGS = {
                        "P ->LI",
                        "LI ->I LI",
                        "LI ->",
                        "I ->D pv",
                        "I ->lire id pv",
                        "I ->ecrire E pv",
                        "I ->id oppaff E pv",
                        "I ->si E opprel E alors LI sinon LI fsi",
                        "I ->si E opprel E alors LI fsi",
                        "I ->tantque E opprel E faire LI ftantque",
                        "D ->type id E",
                        "E ->T E'",
                        "E' ->opadd T E'",
                        "E' ->",
                        "T ->F T'",
                        "T' ->opmult F T'",
                        "T' ->",
                        "F ->( E )",
                        "F ->nombre ",
                        "F ->id",
                        "F ->vrai ",
                        "F ->faux"

        };

        public String[][] tableSLR = {
                        { "Etat", "pv", "lire", "id", "ecrire", "oppaff", "si", "opprel", "alors", "sinon", "fsi",
                                        "tantque", "faire", "ftantque", "type", "opadd", "opmult", "(", ")", "nombre",
                                        "vrai", "faux", "$", "P", "LI", "I", "D", "E", "E'", "T", "T'", "F", },
                        { "0", "err", "s4", "s6", "s5", "err", "s7", "err", "err", "r2", "r2", "s8", "err", "r2", "s9",
                                        "err", "err", "err", "err", "err", "err", "err", "r2", "err", "1", "2", "3",
                                        "err", "err", "err", "err", "err", },
                        { "1", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "acc", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "2", "err", "s4", "s6", "s5", "err", "s7", "err", "err", "r2", "r2", "s8", "err", "r2", "s9",
                                        "err", "err", "err", "err", "err", "err", "err", "r2", "err", "10", "2", "3",
                                        "err", "err", "err", "err", "err", },
                        { "3", "s11", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "4", "err", "err", "s12", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "5", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "13", "err", "14", "err", "15", },
                        { "6", "err", "err", "err", "err", "s21", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "7", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "22", "err", "14", "err", "15", },
                        { "8", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "23", "err", "14", "err", "15", },
                        { "9", "err", "err", "s24", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "10", "err", "err", "err", "err", "err", "err", "err", "err", "r1", "r1", "err", "err", "r1",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "r1", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", },
                        { "11", "err", "r3", "r3", "r3", "err", "r3", "err", "err", "r3", "r3", "r3", "err", "r3", "r3",
                                        "err", "err", "err", "err", "err", "err", "err", "r3", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "12", "s25", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "13", "s26", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "14", "r13", "err", "err", "err", "err", "err", "r13", "r13", "err", "err", "err", "r13",
                                        "err", "err", "s28", "err", "err", "r13", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "27", "err", "err", "err", },
                        { "15", "r16", "err", "err", "err", "err", "err", "r16", "r16", "err", "err", "err", "r16",
                                        "err", "err", "r16", "s30", "err", "r16", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "29", "err", },
                        { "16", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "31", "err", "14", "err", "15", },
                        { "17", "r18", "err", "err", "err", "err", "err", "r18", "r18", "err", "err", "err", "r18",
                                        "err", "err", "r18", "r18", "err", "r18", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "18", "r19", "err", "err", "err", "err", "err", "r19", "r19", "err", "err", "err", "r19",
                                        "err", "err", "r19", "r19", "err", "r19", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "19", "r20", "err", "err", "err", "err", "err", "r20", "r20", "err", "err", "err", "r20",
                                        "err", "err", "r20", "r20", "err", "r20", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "20", "r21", "err", "err", "err", "err", "err", "r21", "r21", "err", "err", "err", "r21",
                                        "err", "err", "r21", "r21", "err", "r21", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "21", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "32", "err", "14", "err", "15", },
                        { "22", "err", "err", "err", "err", "err", "err", "s33", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "23", "err", "err", "err", "err", "err", "err", "s34", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "24", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "35", "err", "14", "err", "15", },
                        { "25", "err", "r4", "r4", "r4", "err", "r4", "err", "err", "r4", "r4", "r4", "err", "r4", "r4",
                                        "err", "err", "err", "err", "err", "err", "err", "r4", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "26", "err", "r5", "r5", "r5", "err", "r5", "err", "err", "r5", "r5", "r5", "err", "r5", "r5",
                                        "err", "err", "err", "err", "err", "err", "err", "r5", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "27", "r11", "err", "err", "err", "err", "err", "r11", "r11", "err", "err", "err", "r11",
                                        "err", "err", "err", "err", "err", "r11", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "28", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "err", "err", "36", "err", "15", },
                        { "29", "r14", "err", "err", "err", "err", "err", "r14", "r14", "err", "err", "err", "r14",
                                        "err", "err", "r14", "err", "err", "r14", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "30", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "37", },
                        { "31", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "s38", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "32", "s39", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "33", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "40", "err", "14", "err", "15", },
                        { "34", "err", "err", "s18", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "s16", "err", "s17", "s19", "s20", "err", "err",
                                        "err", "err", "err", "41", "err", "14", "err", "15", },
                        { "35", "r10", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "36", "r13", "err", "err", "err", "err", "err", "r13", "r13", "err", "err", "err", "r13",
                                        "err", "err", "s28", "err", "err", "r13", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "42", "err", "err", "err", },
                        { "37", "r16", "err", "err", "err", "err", "err", "r16", "r16", "err", "err", "err", "r16",
                                        "err", "err", "r16", "s30", "err", "r16", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "43", "err", },
                        { "38", "r17", "err", "err", "err", "err", "err", "r17", "r17", "err", "err", "err", "r17",
                                        "err", "err", "r17", "r17", "err", "r17", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "39", "err", "r6", "r6", "r6", "err", "r6", "err", "err", "r6", "r6", "r6", "err", "r6", "r6",
                                        "err", "err", "err", "err", "err", "err", "err", "r6", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "40", "err", "err", "err", "err", "err", "err", "err", "s44", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "41", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s45",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "42", "r12", "err", "err", "err", "err", "err", "r12", "r12", "err", "err", "err", "r12",
                                        "err", "err", "err", "err", "err", "r12", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "43", "r15", "err", "err", "err", "err", "err", "r15", "r15", "err", "err", "err", "r15",
                                        "err", "err", "r15", "err", "err", "r15", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "44", "err", "s4", "s6", "s5", "err", "s7", "err", "err", "r2", "r2", "s8", "err", "r2", "s9",
                                        "err", "err", "err", "err", "err", "err", "err", "r2", "err", "46", "2", "3",
                                        "err", "err", "err", "err", "err", },
                        { "45", "err", "s4", "s6", "s5", "err", "s7", "err", "err", "r2", "r2", "s8", "err", "r2", "s9",
                                        "err", "err", "err", "err", "err", "err", "err", "r2", "err", "47", "2", "3",
                                        "err", "err", "err", "err", "err", },
                        { "46", "err", "err", "err", "err", "err", "err", "err", "err", "s48", "s49", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "47", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "s50", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "48", "err", "s4", "s6", "s5", "err", "s7", "err", "err", "r2", "r2", "s8", "err", "r2", "s9",
                                        "err", "err", "err", "err", "err", "err", "err", "r2", "err", "51", "2", "3",
                                        "err", "err", "err", "err", "err", },
                        { "49", "err", "r8", "r8", "r8", "err", "r8", "err", "err", "r8", "r8", "r8", "err", "r8", "r8",
                                        "err", "err", "err", "err", "err", "err", "err", "r8", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "50", "err", "r9", "r9", "r9", "err", "r9", "err", "err", "r9", "r9", "r9", "err", "r9", "r9",
                                        "err", "err", "err", "err", "err", "err", "err", "r9", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
                        { "51", "err", "err", "err", "err", "err", "err", "err", "err", "err", "s52", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", "err", "err", },
                        { "52", "err", "r7", "r7", "r7", "err", "r7", "err", "err", "r7", "r7", "r7", "err", "r7", "r7",
                                        "err", "err", "err", "err", "err", "err", "err", "r7", "err", "err", "err",
                                        "err", "err", "err", "err", "err", "err", },
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
                                        String Partiedroite;
                                        String tabtoken[];
                                        int taillepile;

                                        try {
                                                Partiedroite = tabparties[1];
                                                tabtoken = Partiedroite.split(" ");
                                                taillepile = 2 * tabtoken.length;
                                        } catch (Exception e) {
                                                Partiedroite = "";
                                                taillepile = 0;

                                        }
                                        // System.out.println("Partiedroite"+Partiedroite);

                                        // Partiedroite = Partiedroite.trim();

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

                System.out.printf("%-700.700s ", analyse);
                System.out.printf("%-80.80s ", strInput);
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
                // System.out.println("Input" + strInput);

                System.out.printf("%-120.120s | ", analyse);
                System.out.printf("%-60.60s | ", strInput);
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
