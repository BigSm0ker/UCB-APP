package com.calyrsoft.ucbp1.features.profile.domain.model

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail

class ProfileValueObjectsTest {

    // -------- ProfileName --------
    @Test
    fun `ProfileName acepta nombres no vacios`() {
        val vo = ProfileName("Homero")
        assertEquals("Homero", vo.value)
    }

    @Test
    fun `ProfileName falla si esta vacio`() {
        try {
            ProfileName("")
            fail("Debió lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message!!.contains("no puede estar vacío"))
        }
    }

    // -------- ProfileEmail --------
    @Test
    fun `ProfileEmail acepta formato valido`() {
        val vo = ProfileEmail("homero.simpson@springfieldmail.com")
        assertEquals("homero.simpson@springfieldmail.com", vo.value)
    }

    @Test
    fun `ProfileEmail falla si formato es invalido`() {
        try {
            ProfileEmail("correo-invalido")
            fail("Debió lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message!!.contains("válido"))
        }
    }

    // -------- ProfilePhone --------
    @Test
    fun `ProfilePhone acepta formato internacional simple`() {
        val vo = ProfilePhone("+19395557422")
        assertEquals("+19395557422", vo.value)
    }

    @Test
    fun `ProfilePhone falla si formato es invalido`() {
        try {
            ProfilePhone("abc-123")
            fail("Debió lanzar IllegalArgumentException")
        } catch (e: IllegalArgumentException) {
            assertTrue(e.message!!.contains("no es válido"))
        }
    }

    // -------- ProfileSummary --------
    @Test
    fun `ProfileSummary acepta cualquier texto`() {
        val vo = ProfileSummary("Inspector de la planta nuclear")
        assertEquals("Inspector de la planta nuclear", vo.value)
    }
}
