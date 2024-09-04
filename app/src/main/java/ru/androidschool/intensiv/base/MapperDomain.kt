package ru.androidschool.intensiv.base

sealed interface MapperDomain {
    interface Base<In, Out> {
        fun toLocalDataBase(data: Out): In
        fun toLocalDataBase(list: Collection<Out>): List<In> {
            val result = ArrayList<In>()
            list.mapTo(result) { toLocalDataBase(it) }
            return result
        }

        fun fromLocalDataBase(data: In): Out
        fun fromLocalDataBase(list: Collection<In>): List<Out> {
            val result = ArrayList<Out>()
            list.mapTo(result) { fromLocalDataBase(it) }
            return result
        }
    }

    interface ReadOnly<In, Out> {
        fun readOnly(data: In): Out
    }

    interface ViewObjectMapper<VO, DTO> {
        fun toViewObject(dto: DTO): VO
        fun toViewObject(list: Collection<DTO>): List<VO> {
            val result = ArrayList<VO>()
            list.mapTo(result) { toViewObject(it) }
            return result
        }
    }
}