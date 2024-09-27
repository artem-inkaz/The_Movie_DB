package ru.androidschool.intensiv.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.mock.mockActor
import ru.androidschool.intensiv.mock.mockCast

class ActorMapperDtoTest {
    val mapper = ActorMapperDto()

    @Test
    fun `test actor mapper dto success`() {
        assertEquals(mockActor, mapper.toViewObject(mockCast))
    }

    @Test
    fun `test actors mapper dto success`() {
        assertEquals(listOf(mockActor), mapper.toViewObject(listOf( mockCast)))
    }
}