package classes;

import java.util.ArrayList;

/**
 * Reopresenta��o de um cen�rio de aposta
 * @author Gabriel Barros
 *
 */
public class Cenario {
	private int num;
	private String descricao;
	private int estado;
	private ArrayList<Aposta> apostas;
	private int caixa;
	private int rateio;
	
	/**
	 * Constr�i um cenario a partir do numero que o identifica e a sua descri��o.
	 * Todo cen�rio � iniciado com o seu estado em 0, representando que ainda n�o foi finalizado
	 * O caixa e o rateio s�o inicializados com 0;
	 * @param num
	 * @param descricao
	 */
	public Cenario(int num, String descricao) {
		if(descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		this.apostas = new ArrayList<>();			
		this.num = num;
		this.descricao = descricao;
		this.estado = 0;
		this.caixa = 0;
		this.rateio = 0;
		
	}
	
	private void finalizarApostas(double taxa, boolean estado) {
		//int certas = 0;
		int erradas = 0;
		for (int i = 0; i < apostas.size(); i++) {
			/*if(apostas.get(i).getPrevisao() == estado) {
				certas += apostas.get(i).getValor();
			}else{
				erradas += apostas.get(i).getValor();
			}	*/	
			if(!apostas.get(i).getPrevisao() == estado) {
				erradas += apostas.get(i).getValor();
			}
		}
		this.caixa += (int)erradas * taxa;
		this.rateio += erradas - caixa;
	}
	
	/**
	 * Fecha um cen�rio. Recebe um estado informando se o cen�rio ocorreu ou n�o e a taxa que deve ser retirada de cada aposta perdida no sistema.
	 * Define o estado do cen�rio, a quantidade de dinheiro que deve ser encaminhada ao caixa e a quantidade de dinheiro que deve ser repartida entre os vencedores 
	 * @param estado
	 * @param taxa
	 */
	public void finalizarCenario(boolean estado, double taxa) {
		int i = 2;
		if(estado) {
			i = 1;
		}
		this.estado = i;
		finalizarApostas(taxa, estado);
	}
	
	/**
	 * Cadastra uma aposta no cen�rio, recebe o nome do apostador, o valor da aposta e a previs�o da mesma
	 * @param apostador
	 * @param valor
	 * @param previsao
	 */
	public void cadastrarAposta(String apostador, int valor, boolean previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
	}
	
	/**
	 * Retorna o total de apostas cadastradas no cen�rio
	 * @return
	 */
	public int totalDeApostas() {
		return apostas.size();
	}
	
	/**
	 * Retorna o valor total apostado no cen�rio
	 * @return
	 */
	public int valorTotalDeApostas() {
		int total = 0;
		for (int i = 0; i < apostas.size(); i++) {
			total += apostas.get(i).getValor();
		}
		return total;
	}
	
	/**
	 * Retorna uma representa��o String de todas as apostas cadastradas:
	 * 1 - NOME1 - VALOR1 - PREVIS�O1
	 * 2 - NOME2 - VALOR2 - PREVIS�O2
	 * 3 - NOME3 - VALOR3 - PREVIS�O3
	 * ...
	 * @return
	 */
	public String exibeApostas() {
		String result = "";
		for (int i = 0; i < apostas.size(); i++) {
			result += (i + 1) + " - " + apostas.get(i).toString();
			if(i != apostas.size() - 1) {
				result += System.lineSeparator();
			}
		}
		return result;
	}
	
	public int getCaixa() {
		return caixa;
	}
	
	public void setCaixa(int valor) {
		this.caixa += valor;
	}
	
	public int getRateio() {
		return rateio;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

	
	public ArrayList<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(ArrayList<Aposta> apostas) {
		this.apostas = apostas;
	}

	public void setRateio(int rateio) {
		this.rateio = rateio;
	}

	/**
	 * Retorna uma represen��o String do cen�rio:
	 * NUMERA��O - DESCRI��O - ESTADO
	 */
	@Override
	public String toString() {
		String estado = "";
		switch(this.estado) {
		case 0:
			estado = "Nao finalizado";
			break;
		case 1:
			estado = "Finalizado (ocorreu)";
			break;
		case 2:
			estado = "Finalizado (n ocorreu)";
			break;
		}
		return (num + " - " + descricao + " - " + estado);
	}
	
	public int getEstado() {
		return estado;
	}
}
