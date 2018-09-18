import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoresBuggedImplTest {
	ScoresBuggedImpl scores = new ScoresBuggedImpl();
	String testeToString = "[" + "(" + "Gabriel" + ", " + "1" + "), " +
						         "(" + "Felipe" + ", " + "0" + ")" +"]";

	@Test
	void testAddAndRemove() {
		
		//score menor que 0
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.add(new GameEntry("Eduardo", -1)));
		
		//score igual ao limite inferior
		assertTrue(scores.add(new GameEntry("Felipe", 0)));
		//score igual ao limite inferior + 1
		assertTrue(scores.add(new GameEntry("Gabriel", 1)));
		
		//teste ToString
		assertEquals(testeToString, scores.toString());
		
		//score igual ao limite superior - 1
		assertTrue(scores.add(new GameEntry("Leonardo", (Integer.MAX_VALUE-1))));
		//score igual ao limite superior
		assertTrue(scores.add(new GameEntry("Rafael", (Integer.MAX_VALUE))));
		
		//score maior que o limite superior
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.add(new GameEntry("Gael", (Integer.MAX_VALUE+1))));
		//assertFalse(scores.add(new GameEntry("Gael", (Integer.MAX_VALUE+1))));
		
		//adicionando mais um GameEntry para alcançar o limite do vetor
		assertTrue(scores.add(new GameEntry("Fernando", 381)));
		
		//Tentando adicionar alguem com vetor full
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> scores.add(new GameEntry("João", 400)));
		
		// removendo e verificando que a primeira posição do vetor
		// posui a pessoa com o maior score
		assertEquals("Rafael", scores.remove(0).getName());
		//Após a primeira remoção a primeira posição é atualizada
		//E desse jeito o primeiro lugar fica o segundo melhor score
		assertEquals("Leonardo", scores.remove(0).getName());
		assertEquals("Fernando", scores.remove(0).getName());
		assertEquals("Gabriel", scores.remove(0).getName());
		assertEquals("Felipe", scores.remove(0).getName());
		
	}

	@Test
	void testGetNumElements() {
		assertTrue(scores.getNumElements() >= 0);
	}
	
	@Test
	public final void testeRemove2(){
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(-1));
    }
	
	@Test
	public final void testeRemove3(){
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(1));
    }
	
	@Test
	public final void testeRemove4(){
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(5));
    }
	
	@Test
	public final void testeAdd(){
		Assertions.assertThrows(NullPointerException.class, () -> scores.add(null));
    }
	
	@Test
	void testGetCapacity() {
		assertEquals(5, scores.getCapacity());
	}

}
