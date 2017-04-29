package game.ia.models;

public class Peca {
	private Integer linha; // REPRESENTA A POSICAO DA LINHA DO NUMERO NO
							// TABULEIRO

	private Integer coluna; // REPRESENTA A POSICAO DA COLUNA DO NUMERO NO
							// TABULEIRO

	private Integer numero; // NUMERO DA PECA/ELEMENTO (CASO SEU VALOR SEJA ZERO
							// ENTÃO ESTE É O ELEMENTO VAZIO

	private Integer posicao; // REPRESENTA A POSIÇÃO DO NUMERO NO TABULEIRO

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Integer getPosicao() {
		return posicao;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void setPosicao(int linha, int coluna) {
		if (linha == 0 && coluna == 0) {
			this.posicao = 1;
		} else if (linha == 0 && coluna == 1) {
			this.posicao = 2;
		} else if (linha == 0 && coluna == 2) {
			this.posicao = 3;
		} else if (linha == 1 && coluna == 0) {
			this.posicao = 4;
		} else if (linha == 1 && coluna == 1) {
			this.posicao = 5;
		} else if (linha == 1 && coluna == 2) {
			this.posicao = 6;
		} else if (linha == 2 && coluna == 0) {
			this.posicao = 7;
		} else if (linha == 2 && coluna == 1) {
			this.posicao = 8;
		} else if (linha == 2 && coluna == 2) {
			this.posicao = 9;
		}
	}

	/**
	 * verifica se a peca se encontra na posicao correta
	 * 
	 * @return true, se estiver correto
	 */
	public boolean posicaoCorreta() {
		if (this.numero != null && this.numero == this.posicao) {
			return true;
		} else if (this.numero == null && this.posicao == 9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coluna == null) ? 0 : coluna.hashCode());
		result = prime * result + ((linha == null) ? 0 : linha.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((posicao == null) ? 0 : posicao.hashCode());

		return result;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		//
		Peca outro = (Peca) obj;
		if(coluna == null) {
			if(outro.getColuna() != null) {
				return false;
			}
		} else if(!coluna.equals(outro.getColuna())) {
			return false;
		}
		
		if(linha == null) {
			if(outro.getLinha() != null) {
				return false;
			}
		} else if(!linha.equals(outro.getLinha())) {
			return false;
		}
		
		if(numero == null) {
			if(outro.getNumero() != null)
				return false;
		} else if(!numero.equals(outro.getNumero())) {
			return false;
		}
		
		if(posicao == null) {
			if (outro.getPosicao() != null)
				return false;
		} else if(!posicao.equals(outro.getPosicao())) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.numero + " [" + this.linha + ", " + this.coluna + "] - Position: " + this.posicao;
	}

	/**
	 * 
	 * @param peca
	 */
	public Peca(Peca peca) {
		this.posicao = peca.getPosicao();
		this.numero = peca.getNumero();
		this.coluna = peca.getColuna();
		this.linha = peca.getLinha();
	}

	/**
	 * 
	 * @param numero
	 * @param linha
	 * @param coluna
	 */
	public Peca(Integer numero, Integer linha, Integer coluna) {
		this.numero = numero;
		this.coluna = coluna;
		this.linha = linha;
		setPosicao(linha, coluna);
	}

}
