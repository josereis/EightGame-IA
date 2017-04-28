package game.ia.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

public class TabuleiroJogo {
	// posicao x
	private int posicaoX;
	
	// posica y
	private int posicaoY;

	// relata o nivel que este nó se encontra
	private Integer nivel;
	
	// representa a peca vazia
	private Peca pecavazia;
	
	// lista de pecas
	private List<Peca> pecas;
	
	// representa o pai deste node
	private TabuleiroJogo pai;
	
	// representa os sucessores na arvore de decisão
	private List<TabuleiroJogo> sucessores = new LinkedList<TabuleiroJogo>(); 
	
	// serve para determinar se o no ainda esta aberto
	private boolean estaAbero;

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Peca getPecavazia() {
		return pecavazia;
	}

	public void setPecavazia(Peca pecavazia) {
		this.pecavazia = pecavazia;
	}

	public List<Peca> getPecas() {
		return pecas;
	}

	public void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public TabuleiroJogo getPai() {
		return pai;
	}

	public void setPai(TabuleiroJogo pai) {
		this.pai = pai;
	}

	public List<TabuleiroJogo> getSucessores() {
		return sucessores;
	}

	public void setSucessores(List<TabuleiroJogo> sucessores) {
		this.sucessores = sucessores;
	}

	public boolean isEstaAbero() {
		return estaAbero;
	}

	public void setEstaAbero(boolean estaAbero) {
		this.estaAbero = estaAbero;
	}

	public TabuleiroJogo() {
		this.pecas = new LinkedList<Peca>();

		Collections.sort(this.pecas, new Comparator<Peca>() {

			@Override
			public int compare(Peca p1, Peca p2) {
				if (p1.getPosicao() > p2.getPosicao()) {
					return 1;
				} else if (p1.getPosicao() < p2.getPosicao()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	/**
	 * Solucao:
	 * 
	 * 1 2 3
	 * 4 5 6
	 * 7 8 0
	 * 
	 * @return true, se todas as pecas estiverem na sua posica correta
	 */
	public boolean solucao() {
		for (Peca peca : pecas) {
			if (!peca.posicaoCorreta()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Imprimi o tabuleiro do jogo
	 */
	public void printMatriz() {
		int i = 0;

		List<Peca> ordem = this.pecas;
		ordem = this.getOrdemPecaPosicao(ordem);

		for (Peca peca : ordem) {
			if (i == 3) {
				System.out.println("");
				i = 0;
			}

			if (i == 0) {
				System.out.print("\t");
			}

			if (peca.getNumero() != null && peca.getNumero() != 0) {
				System.out.print(String.valueOf(peca.getNumero()) + " ");
			} else {
				System.out.print("N ");
			}

			i++;
		}
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;

		result = prime * result + ((pecas == null) ? 0 : pecas.hashCode());
		result = prime * result + ((pecavazia == null) ? 0 : pecavazia.hashCode());

		return result;

	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		TabuleiroJogo outro = (TabuleiroJogo) obj;
		if (pecas == null) {
			if (outro.getPecas() != null) {
				return false;
			}
		} else if (!pecas.equals(outro.getPecas())) {
			return false;
		}

		if (pecavazia == null) {
			if (outro.getPecavazia() != null) {
				return false;
			}
		} else if (!pecavazia.equals(outro.getPecavazia())) {
			return false;
		}

		return true;
	}

	/*
	 * retorna uma lista com as possiveis pecas a serem movidas
	 */
	public List<Peca> pecasQuePodemSerMovidas() {
		List<Peca> pecasQuePodemSerMovidas = new LinkedList<Peca>();

		if (pecavazia.getLinha() - 1 >= 0) {
			pecasQuePodemSerMovidas.add(pecas.get(((pecavazia.getLinha() - 1) * 3) + pecavazia.getColuna()));
		}

		if ((pecavazia.getLinha() + 1) * 3 < pecas.size()) {
			pecasQuePodemSerMovidas.add(pecas.get(((pecavazia.getLinha() + 1) * 3) + pecavazia.getColuna()));
		}

		if (pecavazia.getColuna() - 1 >= 0) {
			pecasQuePodemSerMovidas.add(pecas.get((pecavazia.getLinha() * 3) + (pecavazia.getColuna() - 1)));
		}

		if (pecavazia.getColuna() + 1 < 3) {
			pecasQuePodemSerMovidas.add(pecas.get((pecavazia.getLinha() * 3) + (pecavazia.getColuna() + 1)));
		}

		return pecasQuePodemSerMovidas;
	}

	public void moverPeca(Peca peca) {
		// verifica se o numero esta proximo a parte vazia
		if ((peca.getLinha() == pecavazia.getLinha()
				&& (peca.getColuna() == pecavazia.getColuna() - 1 || peca.getColuna() == pecavazia.getColuna() + 1))
				|| ((peca.getLinha() == pecavazia.getLinha() - 1 || peca.getLinha() == pecavazia.getLinha() + 1)
						&& peca.getColuna() == pecavazia.getColuna())) {
			// atualiza as posicoes das pecas no tabuleiro
			atualizaPosicaoPeca(peca);

		}
	}

	/**
	 * Atualiza a posição da peça
	 * 
	 * @param peca
	 */
	public void atualizaPosicaoPeca(Peca peca) {
		Integer linha = pecavazia.getLinha(), coluna = pecavazia.getColuna();

		pecas.remove((peca.getLinha() * 3) + peca.getColuna());
		pecas.add((peca.getLinha() * 3) + peca.getColuna(), pecavazia);

		pecas.remove((linha * 3) + coluna);
		pecas.add((linha * 3) + coluna, peca);

		// atualiza a posição no tabuleiro do jogo
		pecavazia.setLinha(peca.getLinha());
		pecavazia.setColuna(peca.getColuna());
		pecavazia.setPosicao(pecavazia.getLinha(), pecavazia.getColuna());
		peca.setLinha(linha);
		peca.setColuna(coluna);
		peca.setPosicao(peca.getLinha(), peca.getColuna());
	}

	/**
	 * Retorna uma lista onde cada compartimento representa um possivel
	 * seguimento apos mover as pecas que podem ser movidas para a posicao em
	 * branco
	 * 
	 * @return
	 */
	public List<TabuleiroJogo> sucessoresTabuleiroJogo() {
		List<Peca> pecas;
		try {
			pecas = new LinkedList<Peca>();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Heap sem memoria suficiente!\n" + e);

			return null;
		}

		List<TabuleiroJogo> sucessores;
		try {
			sucessores = new LinkedList<TabuleiroJogo>();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Heap sem memoria suficiente!\n" + e);

			return null;
		}

		TabuleiroJogo tabuleiro;
		try {
			tabuleiro = new TabuleiroJogo(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Heap sem memoria suficiente!\n" + e);

			return null;
		}

		// obtem as pecas que podem sem movidas
		pecas = tabuleiro.pecasQuePodemSerMovidas();

		for (int i = 0; i < pecas.size(); i++) {
			// move a peca respectiva
			tabuleiro.moverPeca(pecas.get(i));
			
			// insere o tabuleiro na list
			sucessores.add(tabuleiro);
			
			// cria outra instancia deste tabuleiro
			try {
				tabuleiro = new TabuleiroJogo(this);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Heap sem memoria suficiente!\n" + e);
				
				return null;
			}
			
			// adquire novamente as pecas que podem ser movidas
			try {
				pecas = new LinkedList<Peca>();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Heap sem memoria suficiente!\n" + e);
				
				return null;
			}
			
			pecas = tabuleiro.pecasQuePodemSerMovidas();
		}
		
		return sucessores;

	}
	
	/**
	 * Verifica se pode mover a peca
	 */
	public boolean verificaPecaMover(Peca peca) {
		// verifica se esta proximo a parte vazia
		if ((peca.getLinha() == pecavazia.getLinha() && (peca.getColuna() == pecavazia.getColuna() - 1 || peca.getColuna() == pecavazia.getColuna() + 1)) || ((peca.getLinha() == pecavazia.getLinha() - 1 || peca.getLinha() == pecavazia.getLinha() + 1) && peca.getColuna() == pecavazia.getColuna())) {
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		int i = 0;
		List<Peca> ordem = this.pecas;
		ordem = this.getOrdemPecaPosicao(ordem);
		
		for(Peca peca: ordem) {
			if(i == 3) {
				buffer.append("\n");
				i = 0;
			}
			
			if(peca.getNumero() != null && peca.getNumero() != 0) {
				buffer.append(String.valueOf(peca.getNumero()) + " ");
			} else {
				buffer.append("N ");
			}
			
			i++;
		}
		
		return buffer.toString();
	}

	/**
	 * @param pecas
	 * @return
	 */
	public List<Peca> getOrdemPecaPosicao(List<Peca> pecas) {
		Collections.sort(pecas, new Comparator<Peca>() {

			@Override
			public int compare(Peca p1, Peca p2) {
				if (p1.getPosicao() > p2.getPosicao()) {
					return 1;
				} else if (p1.getPosicao() < p2.getPosicao()) {
					return -1;
				} else {
					return 0;
				}
			}

		});
		return pecas;
	}

	/**
	 * 
	 * @param pecas
	 * @param pecaVazia
	 * @param nivel
	 * @param pai
	 */
	public TabuleiroJogo(List<Peca> pecas, Peca pecaVazia, Integer nivel, TabuleiroJogo pai) {
		this.pai = pai;
		this.nivel = nivel;
		this.pecas = pecas;
		this.estaAbero = true;
		this.pecavazia = pecaVazia;

		Collections.sort(this.pecas, new Comparator<Peca>() {

			@Override
			public int compare(Peca p1, Peca p2) {
				if (p1.getPosicao() > p2.getPosicao()) {
					return 1;
				} else if (p1.getPosicao() < p2.getPosicao()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

	}

	public TabuleiroJogo(TabuleiroJogo tabuleiro) {
		if (tabuleiro.getPecas() != null) {
			// inicializa o tabuleiro do jogo
			this.pecas = new LinkedList<Peca>(); 

			for (Peca peca : tabuleiro.getPecas()) {
				if (peca.getNumero() != null) {
					Peca novaPeca = new Peca(peca);
					pecas.add(novaPeca);
				} else {
					Peca novaPeca = new Peca(peca);
					this.pecas.add(novaPeca);
					this.pecavazia = novaPeca;
				}
			}
		}
	}

}
