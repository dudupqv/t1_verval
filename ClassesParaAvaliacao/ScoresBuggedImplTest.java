import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoresBuggedImplTest {
	ScoresBuggedImpl scores = new ScoresBuggedImpl(); 


	@Test
	void testAdd() {
		//score menor que 0
		assertFalse(scores.add(new GameEntry("Eduardo", -1)));
		//score igual ao limite inferior
		assertTrue(scores.add(new GameEntry("Felipe", 0)));
		//score igual ao limite inferior + 1
		assertTrue(scores.add(new GameEntry("Gabriel", 1)));
		//score igual ao limite superior - 1
		assertTrue(scores.add(new GameEntry("Leonardo", (Integer.MAX_VALUE-1))));
		//score igual ao limite superior
		assertTrue(scores.add(new GameEntry("Rafael", (Integer.MAX_VALUE))));
		//score maior que o limite superior
		assertFalse(scores.add(new GameEntry("Gael", (Integer.MAX_VALUE+1))));
		
		//adicionando mais um GameEntry para alcançar o limite do vetor
		assertTrue(scores.add(new GameEntry("Fernando", 381)));
		
		//Tentando adicionar alguem com vetor full
		assertFalse(scores.add(new GameEntry("João", 400)));
		
		// removendo e verificando que a primeira posição do vetor
		// posui a pessoa com o maior score
		assertEquals("Rafael", scores.remove(0).getName());
	}

//	@Test
//	void testRemove() {
//		
//	}
//
//	@Test
//	void testGetNumElements() {
//		fail("Not yet implemented");
//	}

}
