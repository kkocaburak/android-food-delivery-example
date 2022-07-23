package com.bkarakoca.fooddeliveryapp.scene.restaurant.listing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.domain.restaurant.GetRestaurantListUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.HandleRestaurantFavoriteUseCase
import com.bkarakoca.fooddeliveryapp.domain.restaurant.SortRestaurantsUseCase
import com.bkarakoca.fooddeliveryapp.internal.util.Event
import com.bkarakoca.fooddeliveryapp.internal.util.Failure
import com.bkarakoca.fooddeliveryapp.navigation.NavigationCommand
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class FRRestaurantListVMTest {

    @MockK
    lateinit var getRestaurantListUseCase: GetRestaurantListUseCase

    @MockK
    lateinit var handleRestaurantFavoriteUseCase: HandleRestaurantFavoriteUseCase

    @MockK
    lateinit var sortRestaurantsUseCase: SortRestaurantsUseCase

    lateinit var dataProvider: FRRestaurantListVMDataProvider

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FRRestaurantListVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)

        dataProvider = FRRestaurantListVMDataProvider()
        viewModel = spyk(FRRestaurantListVM(
            getRestaurantListUseCase,
            handleRestaurantFavoriteUseCase,
            sortRestaurantsUseCase
        ))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when fetchRestaurantList success should post RestaurantListUIModel`() = runBlocking {
        // given
        val response = dataProvider.getRestaurantList()
        coEvery { getRestaurantListUseCase.execute(mockk()) } coAnswers {
            flow { emit(response) }
        }

        // when
        viewModel.fetchRestaurantList()

        delay(1000L)

        // then
        Assert.assertTrue(viewModel.restaurantListUIModel.value != null)
    }

    @Test
    fun `when onRestaurantClicked should post navigation action`() = runBlocking {
        // when
        val restaurantUIModel = dataProvider.getRestaurantUIModel()
        viewModel.onRestaurantClicked(restaurantUIModel)

        val navigationValue = Event(
            NavigationCommand.ToDirection(FRRestaurantListDirections.toFRRestaurantDetail(
                restaurantUIModel
            ))
        )
        // then
        Assert.assertTrue(viewModel.navigation.value != navigationValue)
    }

    @Test
    fun `when onRestaurantFavoriteClicked failed should call handleFailure`() = runBlocking {
        // when
        val customFailure = Failure.CustomException("test")
        coEvery { handleRestaurantFavoriteUseCase.execute(mockk()) } coAnswers {
            flow { throw customFailure }
        }

        viewModel.onRestaurantFavoriteClicked(mockk())

        delay(100L)
        // then
        Assert.assertTrue(viewModel.popup.value != null)
    }

    @Test
    fun `when onRestaurantSortClicked with checked false should do nothing`() = runBlocking {
        // when
        coEvery { viewModel.onRestaurantSortClicked(mockk(), mockk()) } coAnswers {
            mockk()
        }

        viewModel.onRestaurantSortClicked(false, mockk())
        viewModel.filteredRestaurantListUIModel.value = null

        delay(100L)
        // then
        Assert.assertTrue(viewModel.filteredRestaurantListUIModel.value == null)
    }

    @Test
    fun `when onRestaurantSortClicked with checked true should return sorted list`() = runBlocking {
        // when
        coEvery { sortRestaurantsUseCase.execute(mockk()) } coAnswers {
            flow { dataProvider.getSortedListByDuration() }
        }

        viewModel.filteredRestaurantListUIModel.value = dataProvider.getRestaurantList()
        viewModel.restaurantListUIModel.value = dataProvider.getRestaurantList()
        viewModel.onRestaurantSortClicked(true, RestaurantSortingType.DISTANCE)

        delay(200L)
        // then
        val restaurantUIModel = viewModel.filteredRestaurantListUIModel.value?.restaurantItemList?.get(1)
        if (restaurantUIModel is RestaurantUIModel) {
            Assert.assertTrue(restaurantUIModel.id == 1L)
            Assert.assertTrue(restaurantUIModel.restaurantName == "restaurant")
            Assert.assertTrue(restaurantUIModel.restaurantStatusType == RestaurantStatusType.CLOSED)
        }
    }

    @Test
    fun `when onRestaurantSortClicked with checked true and restaurantListUIModel null call handleFailure`() = runBlocking {
        // when
        coEvery { sortRestaurantsUseCase.execute(mockk()) } coAnswers {
            flow { dataProvider.getSortedListByDuration() }
        }

        viewModel.restaurantListUIModel.value = null
        viewModel.onRestaurantSortClicked(true, mockk())

        delay(200L)
        // then
        Assert.assertTrue(viewModel.popup.value != null)
    }

}