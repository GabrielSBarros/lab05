package classes;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.Caixa;

class CaixaTest {
	private Caixa caixa;

	@Test
	public void testConstrutorDiheiroMenorQueZero() {
		try{
			caixa = new Caixa(-1, 1);
			fail();
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testConstrutorTaxaMenorQueZero() {
		try{
			caixa = new Caixa(1, -1);
			fail();
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testAdicionarDinheiro() {
		caixa = new Caixa(1000, 0.5);
		caixa.adicionarDinheiro(100);
		assertEquals(1100, caixa.getDinheiro());
	}
	
	@Test
	public void testAdicionarDinheiroNegativo() {
		caixa = new Caixa(1000, 0.5);
		try {
		caixa.adicionarDinheiro(-1);
		fail();
		}catch(IllegalArgumentException e){
			
		}
	}

}
