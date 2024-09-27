package ru.androidschool.intensiv.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.androidschool.intensiv.data.mappers.ActorMapperDto
import ru.androidschool.intensiv.data.mappers.GenreMapperDto
import ru.androidschool.intensiv.mock.mockActor
import ru.androidschool.intensiv.mock.mockCast
import ru.androidschool.intensiv.mock.mockGenreDto
import ru.androidschool.intensiv.mock.mockGenreVo

class GenreMapperDtoTest {
    val mapper = GenreMapperDto()

    @Test
    fun `test genre mapper dto success`() {
        assertEquals(mockGenreVo, mapper.toViewObject(mockGenreDto))
    }

    @Test
    fun `test genres mapper dto success`() {
        assertEquals(listOf(mockGenreVo), mapper.toViewObject(listOf( mockGenreDto)))
    }
}