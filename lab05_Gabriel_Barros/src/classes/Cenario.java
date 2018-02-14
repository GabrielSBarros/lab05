package classes;

import java.util.ArrayList;

/**
 * Reopresentacao de um cenario de aposta
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
	 * Constroi um cenario a partir do numero que o identifica e a sua descricao.
	 * Todo cenario e iniciado com o seu estado em 0, representando que ainda nao foi finalizado
	 * O caixa e o rateio sao inicializados com 0;
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
	
	private	int finalizarApostas(double taxa, boolean estado) {
		//int certas = 0;
		int retirarCaixa = 0;
		int erradas = 0;
		for (int i = 0; i < apostas.size(); i++) {
			/*if(apostas.get(i).getPrevisao() == estado) {
				certas += apostas.get(i).getValor();
			}else{
				erradas += apostas.get(i).getValor();
			}	*/	
			if(!apostas.get(i).getPrevisao() == estado) {
				erradas += apostas.get(i).getValor();
				if(apostas.get(i).getSeguro() != null) {
					retirarCaixa += apostas.get(i).getValorSeguro();
				}
			}
		}
		this.caixa += (int)erradas * taxa;
		this.rateio += erradas - caixa;
		return retirarCaixa;
	}
	
	/**
	 * Fecha um cenario. Recebe um estado informando se o cenario ocorreu ou nao e a taxa que deve ser retirada de cada aposta perdida no sistema.
	 * Define o estado do cenario, a quantidade de dinheiro que deve ser encaminhada ao caixa e a quantidade de dinheiro que deve ser repartida entre os vencedores 
	 * @param estado
	 * @param taxa
	 */
	public int finalizarCenario(boolean estado, double taxa) {
		int i = 2;
		if(estado) {
			i = 1;
		}
		this.estado = i;
		return finalizarApostas(taxa, estado);
	}
	
	/**
	 * Cadastra uma aposta no cenario, recebe o nome do apostador, o valor da aposta e a previsao da mesma
	 * @param apostador
	 * @param valor
	 * @param previsao
	 */
	public int cadastrarAposta(String apostador, int valor, boolean previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
		return apostas.size();
	}
	
	/**
	 * Cadastra uma aposta assegurada no cenario, recebe o nome do apostador, o valor e a previsao da aposta, e o seguro
	 * @param apostador
	 * @param valor
	 * @param previsao
	 */
	public int cadastrarAposta(String apostador, int valor, boolean previsao, Seguro seguro) {
		apostas.add(new Aposta(apostador, valor, previsao, seguro));
		return apostas.size();
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
	 * Retorna uma representacao String de todas as apostas cadastradas:
	 * 1 - NOME1 - VALOR1 - PREVISAO1
	 * 2 - NOME2 - VALOR2 - PREVISAO2
	 * 3 - NOME3 - VALOR3 - PREVISAO3
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
	
	/**
	 * Altera o seguro da aposta para seguro por valor, recebe a aposta que recebera o seguro e o valor assegurado
	 * @param aposta
	 * @param valor
	 */
	public void alterarSeguroValor(int aposta, int valor) {
		verificaAposta(aposta, "Erro ao ");
		verificaApostaAssegurada(aposta, "valor");
		apostas.get(aposta).setSeguro(new SeguroValor(valor));
	}
	
	/**
	 * Altera o seguro da aposta para seguro por taxa, recebe a aposta que recebera o seguro e a taxa assegurada
	 * @param aposta
	 * @param valor
	 */
	public void alterarSeguroTaxa(int aposta, double taxa) {
		verificaAposta(aposta, "Erro ao ");
		verificaApostaAssegurada(aposta, "taxa");
		apostas.get(aposta).setSeguro(new SeguroTaxa(taxa));
	}
	
	/**
	 * Retorna uma representacao String do cenario:
	 * NUMERACAO - DESCRICAO - ESTADO
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
	
	public int getEstado() {
		return estado;
	}
	
	private void verificaAposta(int aposta, String msg) {
		if(aposta < 0) {
			throw new NullPointerException("aposta invalida"); 
		}else if(aposta > apostas.size() - 1) {
			throw new NullPointerException("aposta n cadastrada");
		}
		
	}
	
	private void verificaApostaAssegurada(int aposta, String seguro) {
		if(!apostas.get(aposta).getSeguro().getTipo().equals(seguro)) {
			throw new IllegalArgumentException("Erro ao mudar o tipo de seguro: Tipo de seguro incompativel");
		}
	}
}
