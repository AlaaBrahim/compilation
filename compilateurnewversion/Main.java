package compilateurnewversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import parserexemple.*;

public class Main {
	public static void main(String[] args) {

		Scanner anaLex = new Scanner("a");
		ArrayList<UniteLexicale> listeUniteLexicales = anaLex.getUnitesLexicales();

		/*
		 * System.out.println("remplissage du tableaullll");
		 * for( int i=0; i<anaLex.fluxCaracteres.size();i++)
		 * 
		 * System.out.println(" " +
		 * anaLex.fluxCaracteres.get(i));
		 */
		System.out.println("***********************Analyse lexical*************************");
		int taille = listeUniteLexicales.size();
		String tab[] = new String[taille];
		String ss = " ";
		for (int i = 0; i < taille; i++)
			// if (!tab[i].equals(ss))
			tab[i] = listeUniteLexicales.get(i).getCategorie().toString();

		for (int i = 0; i < taille; i++)
			System.out.println(tab[i]);

		System.out.println("***********************Analyse Syntaxique*************************");

		parsernew test22 = new parsernew();

		test22.analyzeSLnew(tab);

	}
}
