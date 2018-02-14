package classes;

/**
 * Representação de um Seguro de apostas baseado em um valor fixo.
 * @author gabri
 *
 */
public class SeguroValor extends Seguro {
	private int valor;
	
	/**
	 * Constroi um seguro por valor a partir do valor assegurado
	 * @param valor
	 */
	public SeguroValor(int valor) {
		super("valor");
		this.valor = valor;
	}
	
	public int getValor(int valorAposta) {
		return valor;
	}
	
	/**
	 * Retorna uma representacao String de um seguro de valor:
	 * R$ VALOR
	 */
	@Override
	public String toString() {
		return "R$" + (double)(valor * 100);
	}
}
