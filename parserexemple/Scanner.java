package parserexemple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import parserexemple.Categorie;

/*La ArrayListclasse est un tableau redimensionnable , qui peut être trouvé dans le java.utilpackage.

La différence entre un tableau intégré et un tableau ArrayListen Java,
est que la taille d'un tableau ne peut pas être modifiée (si vous voulez ajouter ou supprimer des éléments dans / d'un tableau,
 vous devez en créer un nouveau).
Alors que des éléments peuvent être ajoutés et supprimés d'un ArrayListfichier quand vous le souhaitez.
*/

public class Scanner {
    private ArrayList<Character> fluxCaracteres;
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;

    public Scanner() {
        this("");
    }

    public Scanner(String nomFich) {
        BufferedReader f = null;
        int car = 0;
        fluxCaracteres = new ArrayList<Character>();
        indiceCourant = 0;
        eof = false;
        try {
            f = new BufferedReader(new FileReader(nomFich));
        } catch (IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f = new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            // fluxCaracteres.add((char) car);
            while ((car = f.read()) != -1)
                fluxCaracteres.add((char) car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // for (int i = 0; i < fluxCaracteres.size(); i++)
        // System.out.println(lexemeSuivant());
    }

    public ArrayList<UniteLexicale> getUnitesLexicales() {
        ArrayList<UniteLexicale> listeUnitesLexicales = new ArrayList<UniteLexicale>();

        for (int i = 0; i < fluxCaracteres.size(); i++) {
            listeUnitesLexicales.add(lexemeSuivant());
        }
        return listeUnitesLexicales;
    }

    public void caractereSuivant() {
        if (indiceCourant < fluxCaracteres.size())
            caractereCourant = fluxCaracteres.get(indiceCourant++);
        else
            eof = true;
    }

    public void reculer() {
        if (indiceCourant > 0)
            indiceCourant--;
    }

    public UniteLexicale lexemeSuivant() {
        caractereSuivant();

        while (eof || Character.isWhitespace(caractereCourant)) {
            if (eof)
                return new UniteLexicale(Categorie.EOF, "0");
            caractereSuivant();
        }

        if (Character.isLetter(caractereCourant))
            return getID();

        if (Character.isDigit(caractereCourant))
            return getNombre();

        if (caractereCourant == ':')
            return getOPPAff();

        if (caractereCourant == '@')
            return new UniteLexicale(Categorie.OPType, "@");

        if (caractereCourant == ';')
            return new UniteLexicale(Categorie.PV, ";");

        if (caractereCourant == '<' || caractereCourant == '>' || caractereCourant == '=')
            return getOPPRel();

        if (caractereCourant == '%' || caractereCourant == '*' || caractereCourant == '+' || caractereCourant == '-'
                || caractereCourant == '/')
            return getOP();

        return null;
    }

    public UniteLexicale getOP() {
        switch (caractereCourant) {
            case '%':
                return new UniteLexicale(Categorie.OP, "mod");
            case '*':
                return new UniteLexicale(Categorie.OP, "mult");
            case '+':
                return new UniteLexicale(Categorie.OP, "add");
            case '-':
                return new UniteLexicale(Categorie.OP, "sub");
            case '/':
                return new UniteLexicale(Categorie.OP, "div");
            default:
                return null;
        }
    }

    public UniteLexicale getID() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    sb.append(caractereCourant);
                    break;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isLetterOrDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    return new UniteLexicale(Categorie.ID, sb.toString());
                case 3:
                    return new UniteLexicale(Categorie.ID, sb.toString());
            }
        }
    }

    public UniteLexicale getNombre() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    sb.append(caractereCourant);
                    break;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    return new UniteLexicale(Categorie.NOMBRE, sb.toString());
                case 3:
                    return new UniteLexicale(Categorie.NOMBRE, sb.toString());
            }
        }

    }

    public UniteLexicale getOPPAff() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == ':') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;

                    }
                    break;

                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;

                    }
                    break;

                case 2:
                    if (eof)
                        etat = 3;
                    else
                        etat = 4;
                    break;
                case 3:

                    return new UniteLexicale(Categorie.OPPAff, sb.toString());
                case 4:
                    reculer();
                    return new UniteLexicale(Categorie.OPPAff, sb.toString());

            }

        }
    }

    public UniteLexicale getOPPRel() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;

                    } else if (caractereCourant == '>') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    } else if (caractereCourant == '<') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    }
                    break;

                case 1:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPPRel, "EGA");

                    } else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "EGA");
                    }

                case 2:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;

                    } else
                        etat = 5;
                    break;

                case 3:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 6;

                    } else if (caractereCourant == '>') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 7;

                    } else
                        etat = 8;
                    break;
                case 4:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "PGE");

                    else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "PGE");
                    }

                case 5:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "PGQ");
                    else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "PGQ");
                    }

                case 6:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "PPE");
                    else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "PPE");
                    }
                case 7:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "DIF");
                    else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "DIF");
                    }
                case 8:
                    if (eof)
                        return new UniteLexicale(Categorie.OPPRel, "PPQ");
                    else {
                        reculer();
                        return new UniteLexicale(Categorie.OPPRel, "PPQ");
                    }

            }

        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return fluxCaracteres.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner("test.txt");
        System.out.println(s);
    }
}
