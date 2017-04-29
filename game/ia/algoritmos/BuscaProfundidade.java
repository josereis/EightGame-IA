package game.ia.algoritmos;

import java.util.LinkedList;
import java.util.List;

import game.ia.models.TabuleiroJogo;

public class BuscaProfundidade {
	private Integer passos;
	
	private Integer profundidadeMax;
	
	private List<TabuleiroJogo> movimentos;

	public Integer getPassos() {
		return passos;
	}

	public void setPassos(Integer passos) {
		this.passos = passos;
	}

	public Integer getProfundidadeMax() {
		return profundidadeMax;
	}

	public void setProfundidadeMax(Integer profundidadeMax) {
		this.profundidadeMax = profundidadeMax;
	}

	public List<TabuleiroJogo> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<TabuleiroJogo> movimentos) {
		this.movimentos = movimentos;
	}
	
	/**
	 * 
	 * @param tabuleiro
	 * @param profundidade
	 * @return
	 */
	public TabuleiroJogo chamadaRecusiva(TabuleiroJogo tabuleiro, Integer profundidade) {
		List<TabuleiroJogo> novaLista;
		
		if(tabuleiro.solucao()) {
			movimentos.add(tabuleiro);
			
			return tabuleiro;
		} else if(profundidade != profundidadeMax) {
			novaLista = new LinkedList<TabuleiroJogo>();
			
			novaLista = tabuleiro.sucessoresTabuleiroJogo();
			for(TabuleiroJogo t: novaLista) {
				TabuleiroJogo result = chamadaRecusiva(t, profundidade + 1);
				
				if(result != null) {
					if(result.solucao()) {
						movimentos.add(t);
						passos++;
						
						return result;
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param tabuleiro
	 * @return
	 */

	public TabuleiroJogo primeraPesquisaProfundidade(TabuleiroJogo tabuleiro) {
		passos = 0;
		profundidadeMax = 0;
		movimentos = new LinkedList<TabuleiroJogo>();
		
		TabuleiroJogo result;
		do {
			movimentos.clear();
			result = chamadaRecusiva(tabuleiro, 0);
			
			profundidadeMax++;
		} while(result == null || !result.solucao());
		
		return result;
	}
	
	/**
	 * 
	 * @param profundidade
	 */
	public BuscaProfundidade(Integer profundidade) {
		this.passos = 0;
		this.profundidadeMax = profundidade;
		this.movimentos = new LinkedList<TabuleiroJogo>();
	}
	
}
