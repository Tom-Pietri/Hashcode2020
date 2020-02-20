package com.monochromecivilians.hashcode2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


internal class TestKtTest {
    @Test
    fun `run Input A`() {
        val input = File("./src/main/resources/a_example.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "A")).isGreaterThan(0)
    }

    @Test
    fun `run Input B`() {
        val input = File("./src/main/resources/b_read_on.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "B")).isGreaterThan(0)
    }

    @Test
    fun `run Input C`() {
        val input = File("./src/main/resources/c_incunabula.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "C")).isGreaterThan(0)
    }


    @Test
    fun `run Input D`() {
        val input = File("./src/main/resources/d_tough_choices.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "D")).isGreaterThan(0)
    }


    @Test
    fun `run Input E`() {
        val input = File("./src/main/resources/e_so_many_books.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "E")).isGreaterThan(0)
    }

    @Test
    fun `run Input F`() {
        val input = File("./src/main/resources/f_libraries_of_the_world.txt").readLines(Charsets.UTF_8)
        assertThat(runInput(input, "F")).isGreaterThan(0)
    }
}

