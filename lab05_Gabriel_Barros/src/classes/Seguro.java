package classes;
/**
 * Representação de um seguro para apostas
 * @author Gabriel Barros
 *
 */
public abstract class Seguro {
	private String tipo;
	
	/**
	 * Constroi um seguro definindo seu tipo
	 * @param tipo
	 */
	public Seguro(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Retorna uma representacao String de um seguro
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Retorna o valor da aposta que foi assegurado
	 * @param valorAposta
	 * @return
	 */
	public abstract int getValor(int valorAposta);
}
