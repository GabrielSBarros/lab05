package classes;

/**
 * Representação de um Seguro de apostas baseado em uma taxa do valor apostado
 * @author gabri
 *
 */
public class SeguroTaxa extends Seguro{
	private double taxa;
	
	/**
	 * Constroi um Seguro por Taxa a partir da taxa assegurada
	 * @param taxa
	 */
	public SeguroTaxa(double taxa) {
		super("taxa");
		if(taxa <= 0) {
			throw new IllegalArgumentException("Erro ao criar seguro por taxa: taxa nao pode ser menor ou igual a zero");
		}
		if(taxa > 1) {
			throw new IllegalArgumentException("Erro ao criar seguro por taxa: taxa nao pode ser maior que 100%");
		}
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	/**
	 * Retorna uma representacao String de um seguro por taxa:
	 * TAXA% 
	 */
	@Override
	public String toString() {
		return (taxa * 100) + "%";
	}

	/**
	 * Retorna o valor da aposta que esta assegurado a partir da taxa definida pelo seguro
	 */
	@Override
	public int getValor(int valorAposta) {
		return (int) (valorAposta * taxa);
	}
}
