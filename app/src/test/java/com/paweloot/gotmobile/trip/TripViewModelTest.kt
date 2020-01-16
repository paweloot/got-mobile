package com.paweloot.gotmobile.trip

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.paweloot.gotmobile.model.entity.MtnGroup
import com.paweloot.gotmobile.model.entity.Point
import com.paweloot.gotmobile.model.repository.PointRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

class TripViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TripViewModel
    private lateinit var pointRepository: PointRepository
    private lateinit var pointsObserver: Observer<List<Point>>
    private lateinit var stateObserver: Observer<Int>

    @Captor
    private lateinit var stateCaptor: ArgumentCaptor<Int>

    @Captor
    private lateinit var pointsCaptor: ArgumentCaptor<List<Point>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TripViewModel()
        pointRepository = mock()
        stateObserver = mock()
        pointsObserver = mock()

        viewModel.currentState.observeForever(stateObserver)

        viewModel.pointRepository = pointRepository
        viewModel.points.observeForever(pointsObserver)
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
    fun fetchPointsSuccessful() {
        val mtnGroup = MtnGroup(1, "T.01", "Tatry Wysokie")
        val expectedPoints = listOf(
            Point(1, "Polana pod Wołoszynem", 1250),
            Point(2, "Wodogrzmoty Mickiewicza", 1100)
        )

        `when`(pointRepository.fetchPoints(mtnGroup)).thenReturn(expectedPoints)
        viewModel.fetchPoints(mtnGroup)

        pointsCaptor.run {
            verify(pointsObserver, times(1)).onChanged(capture())
            assertEquals(expectedPoints, value)
        }
    }

    @Test
    fun fetchPointsFailure() {
        val mtnGroup = MtnGroup(12, "S.01", "Góry Izerskie")

        `when`(pointRepository.fetchPoints(mtnGroup)).thenReturn(null)
        viewModel.fetchPoints(mtnGroup)

        pointsCaptor.run {
            verify(pointsObserver, times(1)).onChanged(capture())
            assertNull(value)
        }
    }
}