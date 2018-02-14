package classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeguroValorTest {
	private SeguroValor seguro;
	
	@Test
	public void testToString() {
		seguro = new SeguroValor(100);
		assertEquals("R$ 1,00", seguro.toString());
	}
	
	@Test
	public void testValorMenorQueUm() {
		try {
		seguro = new SeguroValor(0);
		fail();
		}catch(IllegalArgumentException e) {
			
		}
	}

}
