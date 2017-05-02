package game.ia.algoritmos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import game.ia.models.TabuleiroJogo;

public class BuscaLargura {
	
	public TabuleiroJogo encontraMovimentos(List<TabuleiroJogo> listaTabuleiros, List<TabuleiroJogo> tabuleirosVerificados, List<TabuleiroJogo> listaSolucao) {
		//
		boolean flag = false;
		List<TabuleiroJogo> novaLista = new LinkedList<TabuleiroJogo>();
		Map<TabuleiroJogo, LinkedList<TabuleiroJogo>> hashSucessores = new HashMap<TabuleiroJogo, LinkedList<TabuleiroJogo>>();
		
		for(TabuleiroJogo tabuleiroJogo: listaTabuleiros) {
			if(tabuleiroJogo.solucao()) {
				listaSolucao.add(tabuleiroJogo);
				
				return tabuleiroJogo;
			}
			
			//
			hashSucessores.put(tabuleiroJogo, (LinkedList<TabuleiroJogo>) tabuleiroJogo.sucessoresTabuleiroJogo());
			for(TabuleiroJogo tabuleiro: tabuleiroJogo.sucessoresTabuleiroJogo()) {
				for(int i = tabuleirosVerificados.size() - 1; i >= 0 && !flag; i--) {
					if(tabuleiro.equals(tabuleirosVerificados.get(i))) {
						flag = true;
					}
				}
				
				if(!flag) {
					novaLista.add(tabuleiro);
				}
				
				flag = false;
			}
		}
		
		tabuleirosVerificados.addAll(listaTabuleiros);
		
		if(!novaLista.isEmpty()) {
			// recolhe lixo
			System.gc();
			
			TabuleiroJogo jogo = encontraMovimentos(novaLista, tabuleirosVerificados, listaSolucao);
			for(TabuleiroJogo tabuleiro: listaTabuleiros) {
				for(TabuleiroJogo tabuleiro1: hashSucessores.get(tabuleiro)) {
					if(jogo.equals(tabuleiro1)) {
						listaSolucao.add(tabuleiro);
						
						return tabuleiro;
					}
				}
			}
		}
		
		return null;
	}
	
}
