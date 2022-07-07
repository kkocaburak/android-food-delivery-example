package com.bkarakoca.fooddeliveryapp.domain.restaurant

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class SortRestaurantsUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    lateinit var dataProvider: SortRestaurantsUseCaseDataProvider

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: SortRestaurantsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)

        dataProvider = SortRestaurantsUseCaseDataProvider()
        useCase = SortRestaurantsUseCase()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when sortList called with best_match answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.BEST_MATCH
        )

        actualList = actualList.sortedByDescending { restaurant ->
            restaurant.bestMatchValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with newest answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.NEWEST
        )

        actualList = actualList.sortedByDescending { restaurant ->
            restaurant.newestValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with rating answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.RATING
        )

        actualList = actualList.sortedByDescending { restaurant ->
            restaurant.restaurantRatingValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with distance answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.DISTANCE
        )

        actualList = actualList.sortedBy { restaurant ->
            restaurant.restaurantDeliveryDurationValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with popularity answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.POPULARITY
        )

        actualList = actualList.sortedByDescending { restaurant ->
            restaurant.restaurantPopularityValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with average_product_price answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.AVERAGE_PRODUCT_PRICE
        )

        actualList = actualList.sortedBy { restaurant ->
            restaurant.averageProductPriceValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with delivery_cost answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.DELIVERY_COST
        )

        actualList = actualList.sortedBy { restaurant ->
            restaurant.deliveryCostValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }

    @Test
    fun `when sortList called with min_cost answers sorted list`() = runBlocking {
        // given
        var actualList = dataProvider.getRestaurantList()
        val sortedList = useCase.sortList(
            actualList,
            RestaurantSortingType.MIN_COST
        )

        actualList = actualList.sortedBy { restaurant ->
            restaurant.minimumCostValue
        }.sortedBy { restaurant ->
            restaurant.restaurantStatusType == RestaurantStatusType.CLOSED
        }

        // then
        Assert.assertTrue(sortedList[0].id == actualList[0].id)
        Assert.assertTrue(sortedList[1].id == actualList[1].id)
        Assert.assertTrue(sortedList[2].id == actualList[2].id)
    }
}