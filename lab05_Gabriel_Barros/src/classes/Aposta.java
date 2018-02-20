package classes;
/**
 * Representacao de uma aposta
 * @author Gabriel Barros
 *
 */
public class Aposta {
	private String nomeApostador;
	private int valor;
	private boolean previsao;
	private Seguro seguro;
	
	/**
	 * Constroi uma aposta assegurada a partir do nome do apostador, o valor da aposta e a previsao do cenario em que foi apostado e o seguro.
	 * @param nomeApostador
	 * @param valor
	 * @param previsao
	 * @param seguro
	 */
	public Aposta(String nomeApostador, int valor, boolean previsao, Seguro seguro) {
		if(nomeApostador == null || nomeApostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por " + seguro.getTipo() + ": Apostador nao pode ser vazio ou nulo");
		}
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por "+ seguro.getTipo() +": Valor nao pode ser menor ou igual a zero");
		}
		this.nomeApostador = nomeApostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = seguro;
	}
	
	/**
	 * Contrói uma aposta a partir do nome do apostador, o valor da aposta e a previsão do cenário em que foi apostado
	 * @param nomeApostador
	 * @param valor
	 * @param previsao
	 */
	public Aposta(String nomeApostador, int valor, boolean previsao) {
		if(nomeApostador == null || nomeApostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
		if(valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
		this.nomeApostador = nomeApostador;
		this.valor = valor;
		this.previsao = previsao;
		this.seguro = null;
	}
	
	/**
	 * Retorna uma representação String da aposta:
	 * NOME - VALOR - PREVISÃO
	 * Se a aposta for assegurada:
	 * NOME - VALOR - PREVISÃO - ASSEGURADA(TIPO_DO_SEGURO) - VALOR ASSEGURADO
	 */
	@Override
	public String toString() {
		String s = nomeApostador + " - " + valor + " - ";
		if (this.previsao) {
			s += "VAI ACONTECER";
		}else {
			s += "N VAI ACONTECER";
		}
		if (seguro != null) {
			s += " - ASSEGURADA(" + seguro.getTipo() + ") - " + seguro.toString();
		}
		return s;
	}
	
	public int getValor() {
		return valor;
	}
	
	public boolean getPrevisao() {
		return previsao;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}
	
	/**
	 * Retorna o valor da aposta que esta assegurado
	 * @return
	 */
	public int getValorSeguro() {
		if(this.seguro == null) {
			return 0;
		}
		return seguro.getValor(valor);
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}
