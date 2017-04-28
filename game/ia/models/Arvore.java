package game.ia.models;

import java.util.List;

public class Arvore {
	// raiz da arvore
	private TabuleiroJogo raiz;
	
	// nos da arvore
	private List<TabuleiroJogo> nos;

	public TabuleiroJogo getRaiz() {
		return raiz;
	}

	public void setRaiz(TabuleiroJogo raiz) {
		this.raiz = raiz;
	}

	public List<TabuleiroJogo> getNos() {
		return nos;
	}

	public void setNos(List<TabuleiroJogo> nos) {
		this.nos = nos;
	}
	
	public Arvore() {
		
	}
}
