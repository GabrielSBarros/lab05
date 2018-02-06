package classes;

public class ApostaAssseguradaValor extends Aposta {
	private int valorAssegurado;
	
	public ApostaAssseguradaValor(String nomeApostador, int valor, boolean previsao, int valorAssegurado, int custo) {
		super(nomeApostador, valor, previsao);
		this.valorAssegurado = valorAssegurado;
		
	}
	
	@Override
	public int getValor() {
		return super.getValor() - this.valorAssegurado;
	}
}
