package game.ia.algoritmos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import game.ia.models.TabuleiroJogo;

public class BuscaLargura {
	
	/**
	 * Resolve o conjunto e retorna uma lista com os movimentos feitos para alcançar a conclusão
	 * 
	 * @param listaTabuleiros
	 * @param tabuleiroVerificados
	 * @param listaSolucoes
	 * 
	 * @return
	 */
	public TabuleiroJogo encontraMovimentos(List<TabuleiroJogo> listaTabuleiros, List<TabuleiroJogo> tabuleiroVerificados, List<TabuleiroJogo> listaSolucoes) {
		boolean verifica = false;
		List<TabuleiroJogo> novaLista = new LinkedList<TabuleiroJogo>();
		Map<TabuleiroJogo, LinkedList<TabuleiroJogo>> hashSucessores = new HashMap<TabuleiroJogo, LinkedList<TabuleiroJogo>>();
		
		for(TabuleiroJogo tabuleiro: listaTabuleiros) {
			if(tabuleiro.solucao()) {
				listaSolucoes.add(tabuleiro);
				
				return tabuleiro;
			}
			
			// obtem os sucessores do jogo
			hashSucessores.put(tabuleiro, (LinkedList<TabuleiroJogo>) tabuleiro.sucessoresTabuleiroJogo());
			for(TabuleiroJogo tabuleiro1: tabuleiro.sucessoresTabuleiroJogo()) {	
				for(int i = tabuleiroVerificados.size() - 1; i >= 0 && !verifica; i--) {
					if(tabuleiro1.equals(tabuleiroVerificados.get(i))) {
						verifica = true;
					}
				}
				
				if(!verifica) {
					novaLista.add(tabuleiro1);
					//System.out.println(tabuleiro1.toString() + "\n");
				}
				
				verifica = false;
			}
		}
			
		tabuleiroVerificados.addAll(listaTabuleiros);
			
		if(novaLista.isEmpty()) {
			// chama o coletor de lixo
			System.gc();
				
			TabuleiroJogo jogo = encontraMovimentos(novaLista, tabuleiroVerificados, listaSolucoes);
			for(TabuleiroJogo tabuleiro1: listaTabuleiros) {
				for(TabuleiroJogo tabuleiro2: hashSucessores.get(tabuleiro1)) {
					if(jogo.equals(tabuleiro2)) {
						listaSolucoes.add(tabuleiro1);
							
						return tabuleiro1;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Resolve o problema
	 * 
	 * @param listaTabuleiros
	 * @param tabuleirosVerificados
	 * @param passos
	 * @return
	 */
	public TabuleiroJogo encontraMovimentosFora(List<TabuleiroJogo> listaTabuleiros, List<TabuleiroJogo> tabuleirosVerificados, Integer passos) {
		
		boolean verifica = false;
		List<TabuleiroJogo> novaLista = new LinkedList<TabuleiroJogo>();
		
		for(TabuleiroJogo tabuleiro: listaTabuleiros) {
			if(tabuleiro.solucao()) {
				return tabuleiro;
			}
			
			// obtem os sucessores
			for(TabuleiroJogo tabuleiro1: tabuleiro.sucessoresTabuleiroJogo()) {
				for(int i = tabuleirosVerificados.size() - 1; i >= 0 && !verifica; i--) {
					if(tabuleiro1.equals(tabuleirosVerificados.get(i))) {
						verifica = true;
					}
				}
				
				if(!verifica) {
					novaLista.add(tabuleiro1);
				}
				
				verifica = false;
			}
		}
		
		tabuleirosVerificados.addAll(listaTabuleiros);
		if(!novaLista.isEmpty()) {
			// chama coletor de lixo
			System.gc();
			
			listaTabuleiros.clear();
			
			return encontraMovimentosFora(novaLista, tabuleirosVerificados, passos + 1);
		} else 
			return null;
	}
	
}
