package controllers;

import java.util.ArrayList;

import classes.*;
/**
 * Controller da classe Cenario
 * @author Gabriel Barros
 *
 */
public class CenarioController {
	private Caixa caixa;
	private ArrayList<Cenario> cenarios;
	
	/**
	 * Constroi o controller, inicializando o array de cen�rios
	 * @param caixa
	 * @param taxa
	 */
	public CenarioController(int caixa, double taxa) {
		this.cenarios = new ArrayList<>();
		this.caixa = new Caixa(caixa, taxa);
	}
	
	/**
	 * Retorna a quantidade de dinheiro atual no caixa
	 * @return
	 */
	public int getValorCaixa() {
		return caixa.getDinheiro();
	}
	
	/**
	 * Cadastra um cenario a partir de sua descricao
	 * @param descricao
	 */
	public void cadastrarCenario(String descricao) {
		cenarios.add(new Cenario((cenarios.size()+1), descricao));
	}
	
	/**
	 * Cadastra um cenario com bonus a partir de sua descricao e o bonus que ele possui.
	 * @param descricao
	 */
	public void cadastrarCenario(String descricao, int bonus) {
		cenarios.add(new CenarioBonus((cenarios.size()+1), descricao, bonus));
		caixa.retirarDinheiro(bonus);
	}
	
	/**
	 * Retorna uma representacao String de um cenario especifico
	 * @param cenario
	 * @return
	 */
	public String exibirCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta de cenario: ");
		return cenarios.get(cenario - 1).toString();
	}
	
	/**
	 * Retorna uma representacao String de todos os cenarios cadastrados
	 * @return
	 */
	public String exibirCenarios() {
		String result = "";
		for (int i = 0; i < cenarios.size(); i++) {
			result += cenarios.get(i).toString();
			if(i != cenarios.size() - 1) {
				result += System.lineSeparator();
			}
		}
		return result;
	}
	
    /**
     * Cadastra uma aposta num cenario especifico. Recebe o nome do apostador, o valor da aposta e a previsao da mesma.
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     */
	public int cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		verificaCenario(cenario, "Erro no cadastro de aposta: ");
		validaPrevisao(previsao, "Erro no cadastro de aposta: ");		
		return cenarios.get(cenario - 1).cadastrarAposta(apostador, valor, previsao.equals("VAI ACONTECER"));
	}
	
    /**
     * Cadastra uma aposta assegurada por valor num cenario especifico. Recebe o nome do apostador, o valor e a previsao da aposta, o valor e o custo do seguro .
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     * @param valorSeguro
     * @param custo
     */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorSeguro, int custo) {
		if(valorSeguro <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
		SeguroValor seguro = new SeguroValor(valorSeguro);
		caixa.adicionarDinheiro(custo);
		verificaCenario(cenario, "Erro no cadastro de aposta assegurada por valor: ");
		validaPrevisao(previsao, "Erro no cadastro de aposta assegurada por valor: ");		
		return cenarios.get(cenario - 1).cadastrarAposta(apostador, valor, previsao.equals("VAI ACONTECER"), seguro);	
	}
	
	/**
     * Cadastra uma aposta assegurada por taxa num cenario especifico. Recebe o nome do apostador, o valor e a previsao da aposta, a taxa e o custo do seguro.
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     * @param taxa
     * @param custo
     */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		if(taxa <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Taxa assegurada nao pode ser menor ou igual a zero");
		}
		SeguroTaxa seguro = new SeguroTaxa(taxa);
		caixa.adicionarDinheiro(custo);
		verificaCenario(cenario, "Erro no cadastro de aposta assegurada por taxa: ");
		validaPrevisao(previsao, "Erro no cadastro de aposta assegurada por taxa: ");	
		return cenarios.get(cenario - 1).cadastrarAposta(apostador, valor, previsao.equals("VAI ACONTECER"), seguro);	
	}
	
	/**
	 * Altera o seguro de uma aposta associada a um cenario para seguro por valor
	 * @param cenario
	 * @param apostaAssegurada
	 * @param valor
	 */
	public void alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		verificaCenario(cenario - 1, "Erro no cadastro de aposta: ");
		cenarios.get(cenario - 1).alterarSeguroValor(apostaAssegurada - 1, valor);
	}
	
	/**
	 * Altera o seguro de uma aposta associada a um cenario para seguro por taxa
	 * @param cenario
	 * @param apostaAssegurada
	 * @param valor
	 */
	public void alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		verificaCenario(cenario - 1, "Erro no cadastro de aposta: ");
		cenarios.get(cenario - 1).alterarSeguroTaxa(apostaAssegurada - 1, taxa);
	}
	
	/**
	 * Retorna o numero total de apostas cadastradas num cenario especifico
	 * @param cenario
	 * @return
	 */
	public int totalDeApostas(int cenario) {
		verificaCenario(cenario, "Erro na consulta do total de apostas: ");
		return cenarios.get(cenario - 1).totalDeApostas();
	}
	
	/**
	 * Retorna o valor total apostado num cenario especifico
	 * @param cenario
	 * @return
	 */
	public int valorTotalDeApostas(int cenario) {
		verificaCenario(cenario, "Erro na consulta do valor total de apostas: ");
		return cenarios.get(cenario - 1).valorTotalDeApostas();
	}
	
	/**
	 * Exibe todas as apostas de um cenario especifico
	 * @param cenario
	 * @return
	 */
	public String exibeApostas(int cenario) {
		return cenarios.get(cenario - 1).exibeApostas();
	}
	
	/**
	 * Fecha um cenario, informando se o mesmo ocorreu ou nao
	 * @param cenario
	 * @param ocorreu
	 */
	public void fecharCenario(int cenario, boolean ocorreu) {
		verificaCenario(cenario, "Erro ao fechar aposta: ");
		if(cenarios.get(cenario - 1).getEstado() == 1 || cenarios.get(cenario - 1).getEstado() == 2 ) {
			throw new IllegalArgumentException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		caixa.retirarDinheiro(cenarios.get(cenario - 1).finalizarCenario(ocorreu, caixa.getTaxa()));
		caixa.adicionarDinheiro(cenarios.get(cenario - 1).getCaixa());
	}
	
	/**
	 * Retorna a quantidade de dinheiro que deve ser destinada ao caixa do sistema
	 * @param cenario
	 * @return
	 */
	public int getCaixaCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta do caixa do cenario: ");
		if(cenarios.get(cenario - 1).getEstado() == 0) {
			throw new IllegalArgumentException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		return cenarios.get(cenario - 1).getCaixa();
	}
	
	/**
	 * Retorna a quantidade de dinheiro que deve ser distribuida entre os vencedores da aposta
	 * @param cenario
	 * @return
	 */
	public int getTotalRateioCenario(int cenario) {
		verificaCenario(cenario, "Erro na consulta do total de rateio do cenario: ");
		if(cenarios.get(cenario - 1).getEstado() == 0) {
			throw new IllegalArgumentException("Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		return cenarios.get(cenario - 1).getRateio();	
	}
	
	private void verificaCenario(int cenario, String erro) {
		if(cenario < 1) {
			throw new IllegalArgumentException(erro + "Cenario invalido");
		}
		if(cenario > cenarios.size()) {
			throw new IllegalArgumentException(erro + "Cenario nao cadastrado");
		}
	}
	
	private void validaPrevisao(String previsao, String msg) {
		if(previsao == null || previsao.trim().equals("")) {
			throw new IllegalArgumentException(msg + "Previsao nao pode ser vazia ou nula");
		}
		if(!(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException(msg + "Previsao invalida");
		}
	}
}
