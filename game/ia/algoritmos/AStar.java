package game.ia.algoritmos;

import java.util.LinkedList;
import java.util.List;

import game.ia.models.TabuleiroJogo;

public class AStar {
	private Integer passos;
	private List<NoAStar> listaAbertos;
	private List<NoAStar> listaFeichados;
	private List<TabuleiroJogo> listaSolucao;
	
	public Integer getPassos() {
		return passos;
	}

	public void setPassos(Integer passos) {
		this.passos = passos;
	}

	public List<NoAStar> getListaAbertos() {
		return listaAbertos;
	}

	public void setListaAbertos(List<NoAStar> listaAbertos) {
		this.listaAbertos = listaAbertos;
	}

	public List<NoAStar> getListaFeichados() {
		return listaFeichados;
	}

	public void setListaFeichados(List<NoAStar> listaFeichados) {
		this.listaFeichados = listaFeichados;
	}

	public List<TabuleiroJogo> getListaSolucao() {
		return listaSolucao;
	}

	public void setListaSolucao(List<TabuleiroJogo> listaSolucao) {
		this.listaSolucao = listaSolucao;
	}

	/**
	 * construtor padrão
	 */
	public AStar() {
		passos = 0;
		listaAbertos = new LinkedList<NoAStar>();
		listaFeichados = new LinkedList<NoAStar>();
		listaSolucao = new LinkedList<TabuleiroJogo>();
	}
	
	public TabuleiroJogo sucessorMenorCusto(TabuleiroJogo tabuleiro) {
		TabuleiroJogo min = tabuleiro;
		
		Integer heuristica = Heuristica.manhattan(tabuleiro);
		
		for(TabuleiroJogo tab: tabuleiro.sucessoresTabuleiroJogo()) {
			if(Heuristica.manhattan(tab) < heuristica)
				min = tabuleiro;
		}
		
		return min;
	}
	
	public NoAStar minListaAbertos() {
		NoAStar min = null;
		
		for(NoAStar no: listaAbertos) {
			if(min == null) {
				min = no;
			} else {
				if(min.getCusto() > no.getCusto()) {
					min = no;
				}
			}
		}
		
		return min;
	}
	
	public NoAStar pesquisaTabuleiroListaAbertos(TabuleiroJogo tabuleiroJogo) {
		for(NoAStar n: listaAbertos) {
			if(n.getTabuleiroJogo().equals(tabuleiroJogo)) {
				return n;
			}
		}
		
		return null;
	}
	
	public NoAStar pesquisaTabuleiraListaFeichados(TabuleiroJogo tabuleiroJogo) {
		for(NoAStar n: listaFeichados) {
			if(n.getTabuleiroJogo().equals(tabuleiroJogo)){
				return n;
			}
		}
		
		return null;
	}
	
	public void pesquisarEstrela(TabuleiroJogo tabuleiroJogo) {
		NoAStar noAtual = null;
		NoAStar noFinal = null;
		NoAStar noSalvo = null;
		NoAStar noInicial = new NoAStar(tabuleiroJogo, null, 0, Heuristica.manhattan(tabuleiroJogo));
		
		listaAbertos.add(noInicial);
		while(!listaAbertos.isEmpty()) {
			noAtual = minListaAbertos();
			
			if(noAtual.getTabuleiroJogo().solucao()) 
				break;
			
			listaFeichados.add(noAtual);
			listaAbertos.remove(noAtual);
			
			for(TabuleiroJogo tabuleiro: noAtual.getTabuleiroJogo().sucessoresTabuleiroJogo()) {
				Integer heuristica = Heuristica.manhattan(tabuleiro);
				//
				if(noAtual.getAnterior() != null) {
					//
					if(!noAtual.getAnterior().getTabuleiroJogo().equals(tabuleiro)) {
						//
						if((noSalvo = pesquisaTabuleiraListaFeichados(tabuleiro))!= null) {
							//
							if(heuristica + noAtual.getNumeroMovimentos() < noSalvo.getCusto() + noSalvo.getNumeroMovimentos()) {
								listaFeichados.remove(noSalvo);
								
								if(pesquisaTabuleiroListaAbertos(tabuleiro) == null) {
									noFinal = new NoAStar(tabuleiro, noAtual, noAtual.getNumeroMovimentos() + 1, heuristica);
									listaAbertos.add(noFinal);
								}
							}
						} else if((noSalvo = pesquisaTabuleiroListaAbertos(tabuleiro)) != null) {
							//
							if(heuristica + noAtual.getNumeroMovimentos() < noSalvo.getCusto() + noSalvo.getNumeroMovimentos()) {
								listaAbertos.remove(noSalvo);
								
								noFinal = new NoAStar(tabuleiro, noAtual, noAtual.getNumeroMovimentos() + 1, heuristica);
								listaAbertos.add(noFinal);
							}
						} else {
							noFinal = new NoAStar(tabuleiro, noAtual, noAtual.getNumeroMovimentos() + 1, heuristica);
							
							listaAbertos.add(noFinal);
						}
					}
					
				} else {
					noFinal = new NoAStar(tabuleiro, noAtual, noAtual.getNumeroMovimentos() + 1, heuristica);
					
					listaAbertos.add(noFinal);
				}
			}
		}
		
		if(noAtual.getTabuleiroJogo().solucao()) {
			NoAStar temp = noAtual;
			
			passos = noAtual.getNumeroMovimentos();
			while(temp.getAnterior() != null) {
				listaSolucao.add(temp.getTabuleiroJogo());
				
				temp = temp.getAnterior();
			}
		}
	}
}
