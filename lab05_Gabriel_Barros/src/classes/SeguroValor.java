package classes;

/**
 * Representação de um Seguro de apostas baseado em um valor fixo.
 * @author Gabriel Barros 
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
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro ao criar seguro por valor: valor nao pode ser menor ou igual a zero");
		}
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
		return String.format("R$ %,.2f", (double)(valor / 100));
	}
}
