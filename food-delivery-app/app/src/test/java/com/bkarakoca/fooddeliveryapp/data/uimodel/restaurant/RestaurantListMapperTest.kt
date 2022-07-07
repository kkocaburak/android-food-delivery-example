package com.bkarakoca.fooddeliveryapp.data.uimodel.restaurant

import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProvider
import com.bkarakoca.fooddeliveryapp.internal.util.ResourceProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.*

class RestaurantListMapperTest {

    private lateinit var dataProvider: RestaurantListMapperDataProvider

    private lateinit var resourceProvider: ResourceProvider
    lateinit var restaurantListMapper: RestaurantListMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataProvider = RestaurantListMapperDataProvider()
        resourceProvider = ResourceProviderImpl(mockk())
        restaurantListMapper = spyk(RestaurantListMapper(resourceProvider))
    }

    @Test
    fun `when mapResponseToUIModel should return RestaurantUIModel list`() {
        every { resourceProvider.getString(any()) } answers {
            "min"
        }
        val responseModel = dataProvider.getResponseModel()
        val uiModel = restaurantListMapper.mapResponseToUIModel(responseModel)
        val actualUIModel = dataProvider.getUIModel()

        Assert.assertTrue(uiModel[0].restaurantName == actualUIModel[0].restaurantName)
        Assert.assertTrue(uiModel[0].bestMatchText == actualUIModel[0].bestMatchText)
        Assert.assertTrue(uiModel[0].newestText == actualUIModel[0].newestText)
        Assert.assertTrue(uiModel[0].restaurantRatingText == actualUIModel[0].restaurantRatingText)
    }
}