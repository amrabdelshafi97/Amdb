package amr_ayoub.movieez

interface BasePresenter<V> {
    fun attach(view: V)
}