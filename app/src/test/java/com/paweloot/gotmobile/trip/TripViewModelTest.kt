package com.paweloot.gotmobile.trip

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.paweloot.gotmobile.model.entity.Point
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

class TripViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TripViewModel
    private lateinit var stateObserver: Observer<Int>

    @Captor
    private lateinit var stateCaptor: ArgumentCaptor<Int>

    private lateinit var startPoint: Point
    private lateinit var viaPoint: Point
    private lateinit var endPoint: Point

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TripViewModel()
        stateObserver = mock()
        viewModel.currentState.observeForever(stateObserver)

        startPoint = Point(1, "Palenica Białczańska", 0)
        viaPoint = Point(2, "Wodogrzmoty Mickiewicza", 0)
        endPoint = Point(3, "Polana pod Wołoszynem", 0)
    }

    @Test
    fun isCurrentStateChanging() {
        stateCaptor.run {
            verify(stateObserver, times(1)).onChanged(capture())
            assertEquals(SELECT_START_POINT, value)

            viewModel.changeStateTo(SELECT_END_POINT)
            verify(stateObserver, times(2)).onChanged(capture())
            assertEquals(SELECT_END_POINT, value)

            viewModel.changeStateTo(POINTS_SELECTED)
            verify(stateObserver, times(3)).onChanged(capture())
            assertEquals(POINTS_SELECTED, value)

            viewModel.changeStateTo(SAVE_TRIP)
            verify(stateObserver, times(4)).onChanged(capture())
            assertEquals(SAVE_TRIP, value)
        }
    }

    @Test
    fun addPathPoint() {
        val pathPoints = viewModel.pathPoints
        assertEquals(0, pathPoints.size)
        assertEquals(emptyList<Point>(), pathPoints)

        viewModel.addPathPoint(startPoint)
        assertEquals(1, pathPoints.size)
        assertEquals(listOf(startPoint), pathPoints)

        viewModel.addPathPoint(viaPoint)
        viewModel.addPathPoint(endPoint)
        assertEquals(3, pathPoints.size)
        assertEquals(listOf(startPoint, viaPoint, endPoint), pathPoints)
    }
}