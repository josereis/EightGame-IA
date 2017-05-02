package game.ia.algoritmos;

import game.ia.models.Peca;
import game.ia.models.TabuleiroJogo;

public class Heuristica {
	
	public static Integer manhattan(TabuleiroJogo tabuleiroJogo) {
		Integer manhattan = 0;
		
		for (Peca peca : tabuleiroJogo.getPecas()) {
			Integer temp = 0;

			if (peca.getNumero() == null) {
				temp = Math.abs(peca.getLinha() - 2);
				temp += Math.abs(peca.getColuna() - 2);
			} else if (peca.getNumero() == 1) {
				temp = peca.getLinha();
				temp += peca.getColuna();
			} else if (peca.getNumero() == 2) {
				temp = peca.getLinha();
				temp += Math.abs(peca.getColuna() - 1);
			} else if (peca.getNumero() == 3) {
				temp = peca.getLinha();
				temp += Math.abs(peca.getColuna() - 2);
			} else if (peca.getNumero() == 4) {
				temp = Math.abs(peca.getLinha() - 1);
				temp += peca.getColuna();
			} else if (peca.getNumero() == 5) {
				temp = Math.abs(peca.getLinha() - 1);
				temp += Math.abs(peca.getColuna() - 1);
			} else if (peca.getNumero() == 6) {
				temp = Math.abs(peca.getLinha() - 1);
				temp += Math.abs(peca.getColuna() - 2);
			} else if (peca.getNumero() == 7) {
				temp = Math.abs(peca.getLinha() - 2);
				temp += peca.getColuna();
			} else if (peca.getNumero() == 8) {
				temp = Math.abs(peca.getLinha() - 2);
				temp += Math.abs(peca.getColuna() - 1);
			}

			manhattan += temp;
		}

		return manhattan;
	}
	
}
