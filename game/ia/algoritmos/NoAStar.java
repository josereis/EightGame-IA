package game.ia.algoritmos;

import game.ia.models.TabuleiroJogo;

public class NoAStar {
	private Integer custo;
	private NoAStar anterior;
	private Integer numeroMovimentos;
	private TabuleiroJogo tabuleiroJogo;
	
	public Integer getCusto() {
		return custo;
	}
	
	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	public NoAStar getAnterior() {
		return anterior;
	}

	public void setAnterior(NoAStar anterior) {
		this.anterior = anterior;
	}

	public Integer getNumeroMovimentos() {
		return numeroMovimentos;
	}

	public void setNumeroMovimentos(Integer numeroMovimentos) {
		this.numeroMovimentos = numeroMovimentos;
	}

	public TabuleiroJogo getTabuleiroJogo() {
		return tabuleiroJogo;
	}

	public void setTabuleiroJogo(TabuleiroJogo tabuleiroJogo) {
		this.tabuleiroJogo = tabuleiroJogo;
	}

	/**
	 * 
	 * @param tabuleiroJogo
	 * @param anterior
	 * @param numeroMovimentos
	 * @param custo
	 */
	public NoAStar(TabuleiroJogo tabuleiroJogo, NoAStar anterior, Integer numeroMovimentos, Integer custo) {
		super();
		
		this.custo = custo;
		this.anterior = anterior;
		this.tabuleiroJogo = tabuleiroJogo;
		this.numeroMovimentos = numeroMovimentos;
	}
	
}
