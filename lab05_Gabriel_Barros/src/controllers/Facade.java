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
	 * Cadastra um cenário a partir de sua descrição
	 * @param descricao
	 */
    public void cadastrarCenario(String descricao) {
    	cenarioController.cadastrarCenario(descricao);
    }
    
    /**
	 * Retorna uma representação String de um cenário específico
	 * @param cenario
	 * @return
	 */
    public String exibirCenario(int cenario) {
    	return cenarioController.exibirCenario(cenario);
    }
    /**
	 * Retorna uma representação String de todos os cenários cadastrados
	 * @return
	 */
    public String exibirCenarios() {
    	return cenarioController.exibirCenarios();
    }
    
    /**
     * Cadastra uma aposta num cenário específico. Recebe o nome do apostador, o valor da aposta e a previsão da mesma.
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     */
    public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
    	cenarioController.cadastrarAposta(cenario, apostador, valor, previsao);
    }
    
	/**
	 * Retorna o valor total apostado num cenário específico
	 * @param cenario
	 * @return
	 */
    public int valorTotalDeApostas(int cenario) {
    	return cenarioController.valorTotalDeApostas(cenario);
    }
    
    /**
	 * Retorna o número total de apostas cadastradas num cenário específico
	 * @param cenario
	 * @return
	 */
    public int totalDeApostas(int cenario) {
    	return cenarioController.totalDeApostas(cenario);
    }
    
    /**
	 * Exibe todas as apostas de um cenário específico
	 * @param cenario
	 * @return
	 */
    public String exibeApostas(int cenario) {
    	return cenarioController.exibeApostas(cenario);
    }
    
    /**
	 * Fecha um cenário, informando se o mesmo ocorreu ou não
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
    
    public static void main(String[] args) {
		args = new String[] {"controllers.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt"};
		EasyAccept.main(args);
	}
}
