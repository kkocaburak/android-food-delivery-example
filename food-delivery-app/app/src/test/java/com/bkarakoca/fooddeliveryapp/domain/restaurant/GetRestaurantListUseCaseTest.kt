package com.bkarakoca.fooddeliveryapp.domain.restaurant

import com.bkarakoca.fooddeliveryapp.data.repository.restaurant.RestaurantRepositoryImpl
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.RestaurantStatusType
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantHeaderUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantSectionEmptyUIModel
import com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant.listitem.RestaurantUIModel
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.*

class GetRestaurantListUseCaseTest {

    lateinit var dataProvider: GetRestaurantListUseCaseDataProvider

    @MockK(relaxed = true)
    lateinit var repository: RestaurantRepositoryImpl

    @MockK(relaxed = true)
    lateinit var resourceProvider: ResourceProvider

    private lateinit var useCase: GetRestaurantListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        dataProvider = GetRestaurantListUseCaseDataProvider()

        useCase = GetRestaurantListUseCase(repository, resourceProvider)
    }

    @Test
    fun `when sortRestaurantListByStatus answers sorted list`() {
        // given
        every { useCase.sortRestaurantListByStatus(mockk()) } answers {
            dataProvider.getSortedRestaurantList()
        }

        // when
        val sortedList = useCase.sortRestaurantListByStatus(
            dataProvider.getRestaurantList()
        )

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
    fun `when getFavoriteRestaurants with favorites answers favoritesList`() {
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
    fun `when getFavoriteRestaurants with no favorites answers favoritesList with empty item`() {
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
    fun `when getOpenRestaurants called answers openRestaurants`() {
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
    fun `when getOpenRestaurants called with no open restaurants answers openRestaurants with section`() {
            // when
            val openList = useCase.getOpenRestaurants(
                dataProvider.getClosedRestaurantList()
            )

            // then
            Assert.assertTrue(openList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(openList[1] is RestaurantSectionEmptyUIModel)
        }

    @Test
    fun `when getOpenRestaurants favorite restaurants answers openRestaurants with section`() {
            // when
            val openList = useCase.getOpenRestaurants(
                dataProvider.getOpenFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(openList[0] is RestaurantHeaderUIModel)
            Assert.assertTrue(openList[1] is RestaurantSectionEmptyUIModel)
        }

    @Test
    fun `when getClosingRestaurants answers getClosingRestaurants with section`() {
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
    fun `when getClosingRestaurants favorite restaurants answers empty list`() {
            // when
            val closingList = useCase.getClosingRestaurants(
                dataProvider.getClosingFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(closingList.isEmpty())
        }

    @Test
    fun `when getClosedRestaurants answers getClosedRestaurants with section`() {
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
    fun `when getClosedRestaurants favorite restaurants answers empty list`() {
            // when
            val closedList = useCase.getClosedRestaurants(
                dataProvider.getClosedFavoriteRestaurantList()
            )

            // then
            Assert.assertTrue(closedList.isEmpty())
        }

}