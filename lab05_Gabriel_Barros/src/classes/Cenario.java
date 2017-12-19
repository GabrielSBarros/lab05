package classes;

import java.util.ArrayList;

/**
 * Reopresentação de um cenário de aposta
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
	 * Constrói um cenario a partir do numero que o identifica e a sua descrição.
	 * Todo cenário é iniciado com o seu estado em 0, representando que ainda não foi finalizado
	 * O caixa e o rateio são inicializados com 0;
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
		caixa += (int)erradas * taxa;
		rateio += erradas - caixa;
	}
	

	/**
	 * Fecha um cenário. Recebe um estado informando se o cenário ocorreu ou não e a taxa que deve ser retirada de cada aposta perdida no sistema.
	 * Define o estado do cenário, a quantidade de dinheiro que deve ser encaminhada ao caixa e a quantidade de dinheiro que deve ser repartida entre os vencedores 
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
	 * Cadastra uma aposta no cenário, recebe o nome do apostador, o valor da aposta e a previsão da mesma
	 * @param apostador
	 * @param valor
	 * @param previsao
	 */
	public void cadastrarAposta(String apostador, int valor, boolean previsao) {
		apostas.add(new Aposta(apostador, valor, previsao));
	}
	
	/**
	 * Retorna o total de apostas cadastradas no cenário
	 * @return
	 */
	public int totalDeApostas() {
		return apostas.size();
	}
	
	/**
	 * Retorna o valor total apostado no cenário
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
	 * Retorna uma representação String de todas as apostas cadastradas:
	 * 1 - NOME1 - VALOR1 - PREVISÃO1
	 * 2 - NOME2 - VALOR2 - PREVISÃO2
	 * 3 - NOME3 - VALOR3 - PREVISÃO3
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
	
	public int getRateio() {
		return rateio;
	}
	
	/**
	 * Retorna uma represenção String do cenário:
	 * NUMERAÇÃO - DESCRIÇÃO - ESTADO
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
