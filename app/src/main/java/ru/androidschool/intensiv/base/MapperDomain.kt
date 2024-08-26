package ru.androidschool.intensiv.base

sealed interface MapperDomain {
    interface Base<In, Out> {
        fun toLocalDataBase(data: Out): In
        fun fromLocalDataBase(data: In): Out
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