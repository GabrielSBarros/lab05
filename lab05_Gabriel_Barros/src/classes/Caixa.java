package classes;
/**
 * Representação do caixa do sistema, que armazena o dinheiro atual e a taxa que deve ser retirada de cada aposta perdida no sistema
 * @author Gabriel Barros
 *
 */
public class Caixa {
	private int dinheiro;
	private double taxa;
	
	/**
	 * Constrói um caixa a partir do seu dinheiro inicial e a taxa que deve ser retirada de cada aposta perdida no sistema
	 * @param dinheiro
	 * @param taxa
	 */
	public Caixa(int dinheiro, double taxa) {
		if(dinheiro < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if(taxa < 0) {
			throw new IllegalArgumentException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
		this.dinheiro = dinheiro;
		this.taxa = taxa;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	
	public void adicionarDinheiro(int dinheiro) {
		if(dinheiro < 0) {
			throw new IllegalArgumentException("Erro ao adicionar dinheiro: dinheiro n pode ser negativo");
		}
		this.dinheiro += dinheiro;
	}
	
	
}
