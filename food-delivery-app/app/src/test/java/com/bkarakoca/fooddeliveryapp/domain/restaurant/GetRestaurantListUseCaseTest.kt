package com.bkarakoca.fooddeliveryapp.domain.restaurant

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepositoryImpl
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantHeaderUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSectionEmptyUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class GetRestaurantListUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    lateinit var dataProvider: GetRestaurantListUseCaseDataProvider

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var repository: RestaurantRepositoryImpl

    @MockK(relaxed = true)
    lateinit var resourceProvider: ResourceProvider


    private lateinit var useCase: GetRestaurantListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)

        dataProvider = GetRestaurantListUseCaseDataProvider()

        useCase = GetRestaurantListUseCase(repository, resourceProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when sortRestaurantListByStatus answers sorted list`() = runBlocking {
        // given
        every { useCase.sortRestaurantListByStatus(mockk()) } answers {
            dataProvider.getSortedRestaurantList()
        }

        // when
        val sortedList = useCase.sortRestaurantListByStatus(
            dataProvider.getRestaurantList()
        )

        delay(100L)

        val actualSortedList = dataProvider.getRestaurantList().sortedBy {
            it.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualSortedList[0].id)
        Assert.assertTrue(sortedList[0].restaurantName == actualSortedList[0].restaurantName)
        Assert.assertTrue(sortedList[1].id == actualSortedList[1].id)
        Assert.assertTrue(sortedList[1].restaurantName == actualSortedList[1].restaurantName)
    }

    @Test
    fun `when getFavoriteRestaurants with favorites answers favoritesList`() = runBlocking {
        // when
        val favoriteList = useCase.getFavoriteRestaurants(
            dataProvider.getSortedRestaurantList()
        )

        // then
        Assert.assertTrue(favoriteList[0] is RestaurantHeaderUIModel)
        Assert.assertTrue(favoriteList[1] is RestaurantUIModel)
        Assert.assertTrue((favoriteList[1] as RestaurantUIModel).isRestaurantFavorite)
        Assert.assertTrue((favoriteList[1] as RestaurantUIModel).restaurantName == "restaurant2")
    }

    @Test
    fun `when getFavoriteRestaurants with no favorites answers favoritesList with empty item`() =
        runBlocking {
            // when
            val favoriteList = useCase.getFavoriteRestaurants(
                dataProvider.getNotFavoriteSortedRestaurantList()
            )

            // then
            Assert.assertTrue(favoriteList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(favoriteList[1] is RestaurantSectionEmptyUIModel)
            Assert.assertTrue(favoriteList.size == 2)
        }

    @Test
    fun `when getOpenRestaurants called answers openRestaurants`() = runBlocking {
        // when
        val favoriteList = useCase.getOpenRestaurants(
            dataProvider.getNotFavoriteSortedRestaurantList()
        )

        // then
        Assert.assertTrue(favoriteList.size == 2)
        Assert.assertTrue(favoriteList[0] is RestaurantHeaderUIModel)
        Assert.assertTrue(favoriteList[1] is RestaurantUIModel)
        Assert.assertTrue(
            (favoriteList[1] as RestaurantUIModel).restaurantStatusType
                    == RestaurantStatusType.OPEN
        )
    }

    @Test
    fun `when getOpenRestaurants called with no open restaurants answers openRestaurants with section`() =
        runBlocking {
            // when
            val openList = useCase.getOpenRestaurants(
                dataProvider.getClosedRestaurantList()
            )

            // then
            Assert.assertTrue(openList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(openList[1] is RestaurantSectionEmptyUIModel)
        }

    @Test
    fun `when getOpenRestaurants favorite restaurants answers openRestaurants with section`() =
        runBlocking {
            // when
            val openList = useCase.getOpenRestaurants(
                dataProvider.getOpenFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(openList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(openList[1] is RestaurantSectionEmptyUIModel)
        }

    @Test
    fun `when getClosingRestaurants answers getClosingRestaurants with section`() =
        runBlocking {
            // when
            val closingList = useCase.getClosingRestaurants(
                dataProvider.getClosingRestaurantList()
            )

            // then
            Assert.assertTrue(closingList.size == 3)
            Assert.assertTrue(closingList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(closingList[1] is RestaurantUIModel)
            Assert.assertTrue(
                (closingList[1] as RestaurantUIModel).restaurantStatusType
                        == RestaurantStatusType.ORDER_AHEAD
            )
        }

    @Test
    fun `when getClosingRestaurants favorite restaurants answers empty list`() =
        runBlocking {
            // when
            val closingList = useCase.getClosingRestaurants(
                dataProvider.getClosingFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(closingList.isEmpty())
        }

    @Test
    fun `when getClosedRestaurants answers getClosedRestaurants with section`() =
        runBlocking {
            // when
            val closedList = useCase.getClosedRestaurants(
                dataProvider.getClosedRestaurantList()
            )

            // then
            Assert.assertTrue(closedList.size == 3)
            Assert.assertTrue(closedList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(closedList[1] is RestaurantUIModel)
            Assert.assertTrue(
                (closedList[1] as RestaurantUIModel).restaurantStatusType
                        == RestaurantStatusType.CLOSED
            )
        }

    @Test
    fun `when getClosedRestaurants favorite restaurants answers empty list`() =
        runBlocking {
            // when
            val closedList = useCase.getClosedRestaurants(
                dataProvider.getClosedFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(closedList.isEmpty())
        }

}