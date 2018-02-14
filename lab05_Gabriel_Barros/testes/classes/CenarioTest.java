package classes;

import static org.junit.Assert.*;
import org.junit.Test;

import classes.Cenario;

public class CenarioTest {
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
		SeguroValor seguroValor = new SeguroValor(100);
		SeguroTaxa seguroTaxa = new SeguroTaxa(0.3);
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		cenario.cadastrarAposta("apostador2", 10, false);
		cenario.cadastrarAposta("apostador3", 10, true, seguroValor);
		cenario.cadastrarAposta("apostador4", 10, true, seguroTaxa);
		cenario.finalizarCenario(true, 0.1);
		assertEquals(1, cenario.getEstado());
		assertEquals(9, cenario.getRateio());
		assertEquals(1, cenario.getCaixa());
	}
	
	@Test
	public void finalizarCenarioNAconteceu() {
		SeguroValor seguroValor = new SeguroValor(100);
		SeguroTaxa seguroTaxa = new SeguroTaxa(0.3);
		cenario = new Cenario(1, "cenario");
		cenario.cadastrarAposta("apostador", 10, true);
		cenario.cadastrarAposta("apostador2", 10, false);
		cenario.cadastrarAposta("apostador3", 10, true, seguroValor);
		cenario.cadastrarAposta("apostador4", 10, true, seguroTaxa);
		cenario.finalizarCenario(false, 0.1);
		assertEquals(2, cenario.getEstado());
		assertEquals(27, cenario.getRateio());
		assertEquals(3, cenario.getCaixa());
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
	public void testCadastrarApostaAssegurada() {
		cenario = new Cenario(1, "cenario");
		SeguroValor seguroValor = new SeguroValor(100);
		SeguroTaxa seguroTaxa = new SeguroTaxa(0.3);
		cenario.cadastrarAposta("apostador", 10, true, seguroValor);
		assertEquals("1 - apostador - 10 - VAI ACONTECER - ASSEGURADA(valor) - R$ 1,00",cenario.exibeApostas());
		cenario.cadastrarAposta("apostador", 10, true, seguroTaxa);
		assertEquals("1 - apostador - 10 - VAI ACONTECER - ASSEGURADA(valor) - R$ 1,00" + System.lineSeparator() + 
					 "2 - apostador - 10 - VAI ACONTECER - ASSEGURADA(taxa) - 30.0%", 
					  cenario.exibeApostas());
	}
	
	@Test
	public void testAlterarSeguroValorSeguroPorValor() {
		cenario = new Cenario(1, "cenario");
		SeguroValor seguro = new SeguroValor(50);
		cenario.cadastrarAposta("apostador", 2, true, seguro);
		try {
		cenario.alterarSeguroValor( 0, 100);
		fail();
		}catch(IllegalArgumentException e) {
			
		}
	}	
	
	@Test
	public void testAlterarSeguroTaxaSeguroPorTaxa() {
		cenario = new Cenario(1, "cenario");
		SeguroTaxa seguro = new SeguroTaxa(0.3);
		cenario.cadastrarAposta("apostador", 2, true, seguro);
		try {
		cenario.alterarSeguroTaxa( 0, 0.2);
		fail();
		}catch(IllegalArgumentException e) {
			
		}
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
