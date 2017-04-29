package game.ia.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.ia.algoritmos.BuscaProfundidade;
import game.ia.models.Peca;
import game.ia.models.TabuleiroJogo;

public class BuscaProfundidadeTest {
	private Integer passos;
	private TabuleiroJogo tabuleiroInicial;
	private List<TabuleiroJogo> listaSolucoes;
	private BuscaProfundidade buscaProfundidade;
	
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
	
//	@Before
	public void setUp() {
		tabuleiroInicial = criaTabuleiroTeste();
		
		// determina a profundidade maxima em 50 niveis
		buscaProfundidade = new BuscaProfundidade(50);
		
		listaSolucoes = new LinkedList<TabuleiroJogo>();
	}
	
	@Test
	public void test() {
		setUp();
		
		System.out.println("Tabuleiro Inicial:");
		// imprimi o tabuleiro inicial
		tabuleiroInicial.printMatriz();
		
		buscaProfundidade.primeraPesquisaProfundidade(tabuleiroInicial);
		listaSolucoes.addAll(buscaProfundidade.getMovimentos());
		
		passos = buscaProfundidade.getPassos();
		
		//
		System.out.println("\n\nSolução(Passos: " + passos + "):");
		for(int i = listaSolucoes.size() - 1; i >= 0; i--) {
			listaSolucoes.get(i).printMatriz();
			System.out.println("\n");
		}
		
		assertEquals(new Integer(14), passos);
		if(!listaSolucoes.get(0).solucao()) {
			fail("Não encontrou uma solução!");
		}
	}

}
