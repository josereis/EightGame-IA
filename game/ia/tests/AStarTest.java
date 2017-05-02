package game.ia.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.ia.algoritmos.AStar;
import game.ia.models.Peca;
import game.ia.models.TabuleiroJogo;

public class AStarTest {

	private AStar aStar;
	private TabuleiroJogo tabuleiroInicial;
	
	/**
	 * Cria o tabuleiro para teste
	 * @return
	 */
	public TabuleiroJogo criaTabuleiroTeste() {
		TabuleiroJogo temporario = new TabuleiroJogo();
		
		// criando as peças do jogo
		Peca peca1 = new Peca(1, 0, 0);
		Peca peca2 = new Peca(2, 0, 1);
		Peca peca3 = new Peca(3, 0, 2);
		Peca peca4 = new Peca(4, 1, 1);
		Peca peca5 = new Peca(5, 1, 0);
		Peca peca6 = new Peca(6, 1, 2);
		Peca peca7 = new Peca(7, 2, 2);
		Peca peca8 = new Peca(8, 2, 1);
		Peca peca9 = new Peca(null, 2, 0);
		
		// adicionando peças no tabuleiro
		temporario.getPecas().add(peca1);
		temporario.getPecas().add(peca2);
		temporario.getPecas().add(peca3);
		temporario.getPecas().add(peca4);
		temporario.getPecas().add(peca5);
		temporario.getPecas().add(peca6);
		temporario.getPecas().add(peca7);
		temporario.getPecas().add(peca8);
		temporario.getPecas().add(peca9);
		
		return temporario;
	}
	
	@Before
	public void setUp() {
		aStar = new AStar();
		tabuleiroInicial = criaTabuleiroTeste();
	}
	
	@Test
	public void testAStar() {
		System.out.println("Tabuleiro Inicial (A*):");
		tabuleiroInicial.printMatriz();
		System.out.println("\n");
		
		aStar.pesquisarEstrela(tabuleiroInicial);
		System.out.println("Solução (passos: " + aStar.getPassos() + ") ->");
		for(int i = aStar.getListaSolucao().size() - 1; i >= 0; i--) {
			aStar.getListaSolucao().get(i).printMatriz();
			System.out.println("\n");
		}
	}

}
