package classes;
/**
 * Representação de uma aposta
 * @author Gabriel Barros
 *
 */
public class Aposta {
	private String nomeApostador;
	private int valor;
	private boolean previsao;
	
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
	}
	
	/**
	 * Retorna uma representação String da aposta:
	 * NOME - VALOR - PREVISÃO
	 */
	@Override
	public String toString() {
		String previsao = "";
		if (this.previsao) {
			previsao = "VAI ACONTECER";
		}else {
			previsao = "N VAI ACONTECER";
		}
		return (nomeApostador + " - " + valor + " - " + previsao);
	}
	
	public int getValor() {
		return valor;
	}
	
	public boolean getPrevisao() {
		return previsao;
	}

}
