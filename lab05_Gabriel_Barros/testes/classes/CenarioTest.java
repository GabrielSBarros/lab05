package classes;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.Cenario;

class CenarioTest {
	private Cenario cenario;
	
	@Test
	public void testDescricaoVazia() {
		try {
			cenario = new Cenario(1, "");
			fail();
		}catch(IllegalArgumentException e) {		
		}
	}
		
	@Test
	public void finalizarCenarioAconteceu() {
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		cenario.cadastrarAposta("apostador2", 10, false);
		cenario.finalizarCenario(true, 0.1);
		assertEquals(1, cenario.getEstado());
		assertEquals(9, cenario.getRateio());
		assertEquals(1, cenario.getCaixa());
	}
	
	@Test
	public void finalizarCenarioNAconteceu() {
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		cenario.cadastrarAposta("apostador2", 10, false);
		cenario.finalizarCenario(false, 0.1);
		assertEquals(2, cenario.getEstado());
		assertEquals(9, cenario.getRateio());
		assertEquals(1, cenario.getCaixa());
	}
	
	@Test
	public void testCadastrarAposta() {
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		assertEquals("1 - apostador - 10 - VAI ACONTECER",cenario.exibeApostas());
		cenario.cadastrarAposta("apostador", 10, true);
		assertEquals("1 - apostador - 10 - VAI ACONTECER" + System.lineSeparator() + 
					 "2 - apostador - 10 - VAI ACONTECER", 
					  cenario.exibeApostas());
	}
	
	@Test
	public void testToString() {
		cenario = new Cenario(1, "cenario");
		assertEquals("1 - cenario - Nao finalizado", cenario.toString());
	}
	
	@Test
	public void testFinalizarCenario() {
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		cenario.cadastrarAposta("apostador2", 10, false);
		cenario.finalizarCenario(true, 0.1);
		assertEquals(9, cenario.getRateio());
		assertEquals(1, cenario.getEstado());
		
	}

}
