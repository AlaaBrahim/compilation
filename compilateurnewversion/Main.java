package compilateurnewversion;

import java.util.ArrayList;
import parserexemple.*;

public class Main {
	public static void main(String[] args) {

		Scanner anaLex = new Scanner("a");
		ArrayList<UniteLexicale> listeUniteLexicales = anaLex.getUnitesLexicales();

		System.out.println("***********************Analyse lexical*************************");
		int taille = listeUniteLexicales.size() + 1;
		String tab[] = new String[taille];
		String temp;
		for (int i = 0; i < taille - 1; i++)
		// if (!tab[i].equals(ss))
		{
			temp = listeUniteLexicales.get(i).getCategorie().toString();
			if (!temp.equals("eof"))
				tab[i] = temp;
		}

		// for (int i = 0; i < taille; i++)
		// if (tab[i] != null)
		// System.out.println(tab[i]);

		tab[taille - 1] = "$";

		anaLex.printUnitesLexicales();

		System.out.println("***********************Analyse Syntaxique*************************");

		parsernew test22 = new parsernew();

		test22.analyzeSLnew(tab);

	}
}
