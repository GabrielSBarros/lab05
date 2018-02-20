package controllers;

import easyaccept.EasyAccept;

public class Facade {
	private CenarioController cenarioController;
	
	public Facade() {
		this.cenarioController = new CenarioController(0, 0);
	}
	
	/**
	 * Inicializa o caixa do sistema com a quantidade de dinheiro inicial e a taxa que deve ser retirada de cada aposta perdida no sistema.
	 * @param caixa
	 * @param taxa
	 */
    public void inicializa(int caixa, double taxa) {
    	cenarioController = new CenarioController(caixa, taxa);
    }
    
    /**
	 * Retorna a quantidade de dinheiro atual no caixa
	 * @return
	 */
    public int getCaixa() {
    	return cenarioController.getValorCaixa();
    }
    
    /**
	 * Cadastra um cenario a partir de sua descricao
	 * @param descricao
	 */
    public void cadastrarCenario(String descricao) {
    	cenarioController.cadastrarCenario(descricao);
    }
    
    /**
	 * Cadastra um cenario com bonus a partir de sua descricao e o bonus que ele possui
	 * @param descricao
	 */
    public void cadastrarCenario(String descricao, int bonus) {
    	cenarioController.cadastrarCenario(descricao, bonus);
    }
    
    /**
	 * Retorna uma representacao String de um cenario especifico
	 * @param cenario
	 * @return
	 */
    public String exibirCenario(int cenario) {
    	return cenarioController.exibirCenario(cenario);
    }
    /**
	 * Retorna uma representa��o String de todos os cenarios cadastrados
	 * @return
	 */
    public String exibirCenarios() {
    	return cenarioController.exibirCenarios();
    }
    
    /**
     * Cadastra uma aposta num cenario especifico. Recebe o nome do apostador, o valor da aposta e a previsao da mesma.
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     */
    public int cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
    	return cenarioController.cadastrarAposta(cenario, apostador, valor, previsao);
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
    	return cenarioController.cadastrarApostaSeguraValor(cenario, apostador, valor, previsao, valorSeguro, custo);
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
    	return cenarioController.cadastrarApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
    }
    
	/**
	 * Retorna o valor total apostado num cenario especifico
	 * @param cenario
	 * @return
	 */
    public int valorTotalDeApostas(int cenario) {
    	return cenarioController.valorTotalDeApostas(cenario);
    }
    
    /**
	 * Retorna o n�mero total de apostas cadastradas num cenario especifico
	 * @param cenario
	 * @return
	 */
    public int totalDeApostas(int cenario) {
    	return cenarioController.totalDeApostas(cenario);
    }
    
    /**
	 * Exibe todas as apostas de um cenario especifico
	 * @param cenario
	 * @return
	 */
    public String exibeApostas(int cenario) {
    	return cenarioController.exibeApostas(cenario);
    }
    
    /**
	 * Fecha um cenario, informando se o mesmo ocorreu ou nao
	 * @param cenario
	 * @param ocorreu
	 */
    public void fecharAposta(int cenario, boolean ocorreu) {
    	cenarioController.fecharCenario(cenario, ocorreu);
    }
    
    /**
	 * Retorna a quantidade de dinheiro que deve ser destinada ao caixa do sistema
	 * @param cenario
	 * @return
	 */
    public int getCaixaCenario(int cenario) {
    	return cenarioController.getCaixaCenario(cenario);
    }
    
    /**
	 * Retorna a quantidade de dinheiro que deve ser distribuida entre os vencedores da aposta
	 * @param cenario
	 * @return
	 */
    public int getTotalRateioCenario(int cenario) {
    	return cenarioController.getTotalRateioCenario(cenario);
    }
    
    /**
     * Altera o tipo de seguro de uma aposta contida em um cenario para seguro por valor
     * @param cenario
     * @param apostaAssegurada
     * @param valor
     */
    public void alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
    	cenarioController.alterarSeguroValor(cenario, apostaAssegurada, valor);
    }
    
    /**
     * Altera o tipo de seguro de uma aposta contida em um cenario para seguro por taxa
     * @param cenario
     * @param apostaAssegurada
     * @param valor
     */
   	public void alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
   		cenarioController.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
   	}
    
   	public void alterarOrdem(String ordem) {
   		cenarioController.alterarOrdem(ordem);
   	}
   	
   	public String exibirCenarioOrdenado(int cenario) {
   		return cenarioController.exibirCenarioOrdenado(cenario);
   	}
   	
    public static void main(String[] args) {
		args = new String[] {"controllers.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt", "acceptance_test/us6_test.txt", "acceptance_test/us7_test.txt"};
		EasyAccept.main(args);
	}
}
