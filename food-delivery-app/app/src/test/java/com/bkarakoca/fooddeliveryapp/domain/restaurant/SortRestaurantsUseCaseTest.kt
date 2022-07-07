package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantSortingType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import io.mockk.MockKAnnotations
import org.junit.*

class SortRestaurantsUseCaseTest {

    lateinit var dataProvider: SortRestaurantsUseCaseDataProvider


    private lateinit var useCase: SortRestaurantsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        dataProvider = SortRestaurantsUseCaseDataProvider()
        useCase = SortRestaurantsUseCase()
    }

    @Test
    fun `when sortList called with best_match answers sorted list`() {
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
    fun `when sortList called with newest answers sorted list`() {
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
    fun `when sortList called with rating answers sorted list`() {
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
    fun `when sortList called with distance answers sorted list`() {
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
    fun `when sortList called with popularity answers sorted list`() {
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
    fun `when sortList called with average_product_price answers sorted list`() {
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
    fun `when sortList called with delivery_cost answers sorted list`() {
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
    fun `when sortList called with min_cost answers sorted list`() {
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