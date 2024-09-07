package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.core.base.MapperDomain
import ru.androidschool.intensiv.data.storage.entities.MoviesEntity as InMovies
import ru.androidschool.intensiv.data.vo.Movies as OutMovies

class MoviesMapper : MapperDomain.ReadOnly<InMovies, OutMovies> {

    private val movieMapper = MovieStorageMapper()
    private val actorMapper = ActorMapper()
    private val genreMapper = GenreMapper()

    override fun readOnly(data: InMovies): OutMovies = with(data) {
        OutMovies(
            movie = movieMapper.fromLocalDataBase(movieEntity),
            actors = actorsEntities?.map { actorMapper.fromLocalDataBase(it) },
            genres = genresEntities?.map { genreMapper.fromLocalDataBase(it) },
        )
    }
}