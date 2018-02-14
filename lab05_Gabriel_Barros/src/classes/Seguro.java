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
	
	@Override
	public abstract String toString();
	
	public abstract int getValor(int valorAposta);
}
