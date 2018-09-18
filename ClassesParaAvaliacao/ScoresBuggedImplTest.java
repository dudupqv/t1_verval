import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoresBuggedImplTest {
	
	ScoresBuggedImpl scores;
	String testeToString;
	
	// Construtor de testes
	@BeforeEach
	void setup() {
		scores = new ScoresBuggedImpl();
		testeToString = "[" + "(" + "Gabriel" + ", " + "10" + "), " +
					         "(" + "Eduardo" + ", " + "5" + ")" +"]";
		scores.add(new GameEntry("Eduardo", 5));
		scores.add(new GameEntry("Gabriel", 10));
	}
	
	// Teste realizado em cima dos getters Capacity e NumElements
	// Ambos seguem a regra das tabelas mostradas no relatório
	@Test
	void gettersTest() {
		assertEquals(2, scores.getNumElements());
		assertEquals(5, scores.getCapacity());
		assertTrue(scores.getNumElements() >= 0);
		assertTrue(scores.getCapacity() == 5);
	}
	
	// Teste em cima do método toString(), para garantir que retorne o formato da String proposto
	@Test
	void toStringTest() {
		//teste do método ToString
		assertEquals(testeToString, scores.toString());
	}

	// A seguir, uma série de testes realizados para garantir que o método add funciona
	// O método a seguir realiza testes com os limites (apresentados na tabela de casos de teste)
	@Test
	void addTest() {
		//Score menor que 0 (limite inferior)
		Assertions.assertThrows(IllegalArgumentException.class, () -> scores.add(new GameEntry("Eduardo", -1)));
		//Score maior que o limite (limite superior)
		Assertions.assertThrows(IllegalArgumentException.class, () -> scores.add(new GameEntry("Gael", (Integer.MAX_VALUE+1))));
		//Score igual ao limite inferior
		assertTrue(scores.add(new GameEntry("Felipe", 0)));
		//Score igual ao limite inferior + 1
		assertTrue(scores.add(new GameEntry("Gabriel", 1)));
		//Score igual ao limite superior - 1
		assertTrue(scores.add(new GameEntry("Leonardo", (Integer.MAX_VALUE-1))));
		//Score igual ao limite superior
		assertTrue(scores.add(new GameEntry("Rafael", (Integer.MAX_VALUE))));
	}
	
	// A seguir, são testes que garantem ele insere Scores no vetor quando são maiores e não insere quando são menores
	@Test
	void fullTest() {
		// Lota o vetor
		assertTrue(scores.add(new GameEntry("Ijuí", 15)));
		assertTrue(scores.add(new GameEntry("Maurício", 20)));
		assertTrue(scores.add(new GameEntry("Gesiel", 25)));
		
		// Insere novo score no vetor, sucedendo pois ele é maior que o menor atual (que é Eduardo,5)
		assertTrue(scores.add(new GameEntry("Casarão", 40)));
		
		// Falha ao inserir novo Score por ele ser menor que o menor score no vetor
		assertFalse(scores.add(new GameEntry("Fracassado", 1)));
	}
	
	// A seguir, estão os testes de remoção do vetor
	// O primeiro teste é realizado como segue a tabela de casos teste
	@Test
	void removeTest() {
		// Lota o vetor
		scores.add(new GameEntry("Ijuí", 15));
		scores.add(new GameEntry("Maurício", 20));
		scores.add(new GameEntry("Gesiel", 25));
		
		// Posição inferior ao limite inferior
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(-1));
		// Posição superior ao limite superior
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(5));
		// Posição final do limite superior
		assertNotNull(scores.remove(4));
		// Posição final do limite inferior
		assertNotNull(scores.remove(0));
		
		// O próximo teste apenas garante que ele atire uma exceção, caso na posição desejada não esteja nenhum score
		// Ou seja, um null
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> scores.remove(4));
	}
	
	
	// Este teste final garante que ele mantenha a ordem do vetor. Ele deve remover em ordem de menor para maior:
	// Casarão, Gesiel, Maurício, Ijuí e Gabriel. Se o teste suceder, significa que o vetor está ordenado de acordo.
	@Test
	void testOrder() {
		// Lota o vetor
		scores.add(new GameEntry("Ijuí", 15));
		scores.add(new GameEntry("Maurício", 20));
		scores.add(new GameEntry("Gesiel", 25));
		// Substitui
		scores.add(new GameEntry("Casarão", 40));
		
		assertTrue(scores.remove(4).getName().equals("Gabriel"));
		assertTrue(scores.remove(3).getName().equals("Ijuí"));
		assertTrue(scores.remove(2).getName().equals("Maurício"));
		assertTrue(scores.remove(1).getName().equals("Gesiel"));
		assertTrue(scores.remove(0).getName().equals("Casarão"));
	}

}
