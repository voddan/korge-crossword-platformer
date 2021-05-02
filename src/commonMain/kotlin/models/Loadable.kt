package models

interface Loadable {
    suspend fun initLoad()
}