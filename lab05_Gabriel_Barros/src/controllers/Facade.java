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
	 * Cadastra um cen�rio a partir de sua descri��o
	 * @param descricao
	 */
    public void cadastrarCenario(String descricao) {
    	cenarioController.cadastrarCenario(descricao);
    }
    
    /**
	 * Cadastra um cen�rio com bonus a partir de sua descri��o e o bonus que ele possui
	 * @param descricao
	 */
    public void cadastrarCenario(String descricao, int bonus) {
    	cenarioController.cadastrarCenario(descricao, bonus);
    }
    
    /**
	 * Retorna uma representa��o String de um cen�rio espec�fico
	 * @param cenario
	 * @return
	 */
    public String exibirCenario(int cenario) {
    	return cenarioController.exibirCenario(cenario);
    }
    /**
	 * Retorna uma representa��o String de todos os cen�rios cadastrados
	 * @return
	 */
    public String exibirCenarios() {
    	return cenarioController.exibirCenarios();
    }
    
    /**
     * Cadastra uma aposta num cen�rio espec�fico. Recebe o nome do apostador, o valor da aposta e a previs�o da mesma.
     * @param cenario
     * @param apostador
     * @param valor
     * @param previsao
     */
    public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
    	cenarioController.cadastrarAposta(cenario, apostador, valor, previsao);
    }
    
	/**
	 * Retorna o valor total apostado num cen�rio espec�fico
	 * @param cenario
	 * @return
	 */
    public int valorTotalDeApostas(int cenario) {
    	return cenarioController.valorTotalDeApostas(cenario);
    }
    
    /**
	 * Retorna o n�mero total de apostas cadastradas num cen�rio espec�fico
	 * @param cenario
	 * @return
	 */
    public int totalDeApostas(int cenario) {
    	return cenarioController.totalDeApostas(cenario);
    }
    
    /**
	 * Exibe todas as apostas de um cen�rio espec�fico
	 * @param cenario
	 * @return
	 */
    public String exibeApostas(int cenario) {
    	return cenarioController.exibeApostas(cenario);
    }
    
    /**
	 * Fecha um cen�rio, informando se o mesmo ocorreu ou n�o
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
