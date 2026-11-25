package br.edu.fatecpg.appcompose

import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun calcularPrecoComDesconto_preco500_desconto15_retorna425() {
        val preco = 500.0
        val percentual = 15.0
        val resultado = calcularPrecoComDesconto(preco, percentual)
        assertEquals(425.0, resultado, 0.001)
    }
}
