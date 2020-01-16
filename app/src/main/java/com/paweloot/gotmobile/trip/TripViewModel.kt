package com.paweloot.gotmobile.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.gotmobile.model.entity.*
import com.paweloot.gotmobile.model.repository.PointRepository
import com.paweloot.gotmobile.model.repository.SummaryPathRepository
import com.paweloot.gotmobile.model.repository.TripRepository
import java.util.*

const val SELECT_START_POINT = 0
const val SELECT_END_POINT = 1
const val SELECT_VIA_POINT = 2
const val POINTS_SELECTED = 3
const val SAVE_TRIP = 4

private const val TAG = "TripViewModel"

/**
 * ViewModel odpowiedzialny za dane obserwowane przez fragment TripFragment oraz za ich
 * aktualizację poprzez komunikację z odpowiednimi repozytoriami.
 */
class TripViewModel : ViewModel() {

    private val pointRepository = PointRepository()
    private val summaryPathRepository = SummaryPathRepository()
    private val tripRepository = TripRepository()

    private var _points = pointRepository.points
    private val _selectedPoint = MutableLiveData<Point>()
    private val _pathPoints: MutableList<Point> = mutableListOf()

    /**
     * Lista punktów do wyboru aktualnie wyświetlanych użytkownikowi.
     */
    val points: LiveData<List<Point>> = _points

    /**
     * Lista punktów tworzących trasę.
     */
    val pathPoints: List<Point> = _pathPoints

    /**
     * Lista odcinków w stworzonej trasie wyświetlanych w podsumowaniu.
     */
    val summaryPaths: LiveData<List<SummaryPath>> = summaryPathRepository.summaryPaths

    /**
     * Aktualnie wybrany przez użytkownika punkt.
     */
    val selectedPoint: LiveData<Point> = _selectedPoint

    /**
     *  Aktualny stan fragmentu TripFragment, będący jedną z wartości:
     *  SELECT_START_POINT, SELECT_END_POINT, SELECT_VIA_POINT, POINTS_SELECTED, SAVE_TRIP.
     */
    var currentState = MutableLiveData<Int>(SELECT_START_POINT)

    /**
     * Zmienia aktualny stan na podany.
     *
     * @param newState nowy stan
     */
    fun changeStateTo(newState: Int) {
        currentState.value = newState
    }

    /**
     * Pobiera listę punktów dla podanej grupy górskiej.
     *
     * @param mtnGroup grupa górska dla której mają zostać pobrane punkty.
     */
    fun fetchPoints(mtnGroup: MtnGroup) {
        pointRepository.fetchPoints(mtnGroup)
    }

    /**
     * Ustawia aktualnie wybrany przez użytkownika punkt.
     *
     * @param point wybrany punkt
     */
    fun setSelectedPoint(point: Point) {
        _selectedPoint.value = point
    }

    /**
     * Dodaje wybrany punkt jako kolejny punkt w tworzonej trasie.
     *
     * @param point wybrany punkt
     */
    fun addPathPoint(point: Point) {
        _pathPoints.add(point)
    }

    /**
     * Filtruje listę punktów aktualnie wyświetlanych użytkownikowi
     * na podstawie ostatnio wybranego punktu.
     */
    fun filterPoints() {
        pointRepository.filterPoints(selectedPoint.value!!)
    }

    /**
     * Pobiera listę odcinków zawierających się w stworzonej trasie.
     */
    fun fetchSummaryPaths() {
        summaryPathRepository.fetchSummaryPaths(_pathPoints)
    }

    /**
     * Zapisuje stworzoną trasę w bazie danych poprzez repozytorium, które komunikuje się
     * z odpowiednim endpoint'em w REST API.
     *
     * @param loggedTourist zalogowany turysta
     * @param selectedDate wybrana data planowanej trasy
     * @param callback callback wywoływany po zakończonej komunikacji z bazą,
     *                  posiada informację o powodzeniu operacji
     */
    fun saveTrip(loggedTourist: Tourist, selectedDate: Date, callback: (success: Boolean) -> Unit) {

        val gotPoints = summaryPaths.value!!.sumBy { path -> path.points }
        val pathPointsIds = _pathPoints.map { point -> point.id }

        val postTripBody = PostTripBody(
            loggedTourist.user.id,
            selectedDate,
            gotPoints,
            pathPointsIds
        )

        tripRepository.saveTrip(postTripBody) { success ->
            callback(success)
        }
    }
}
