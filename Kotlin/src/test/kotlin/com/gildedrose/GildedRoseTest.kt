package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {
    @Test fun aNormalItemBeforeSellByDecreasesByOne() {
        val item = testGildedRose("a normal item", 1, 1)
        assertQuality(0, item)
        assertSellIn(0, item)
    }

    @Test fun aNormalItemAfterSellByDecreasesByTwo() {
        val item = testGildedRose("a normal item", 0, 2)
        assertQuality(0, item)
        assertSellIn(-1, item)
    }

    @Test
    fun aNormalItemQualityCanNeverGoBelowZero() {
        val item = testGildedRose("a normal item", 0, 0)
        assertQuality(0, item)
        assertSellIn(-1, item)
    }

    @Test fun agedBrieGetsBetterWithAge() {
        val item = testGildedRose("Aged Brie", 1, 1)
        assertQuality(2, item)
        assertSellIn(0, item)
    }

    @Test fun agedBrieCannotGainMoreThan50Quality() {
        val item = testGildedRose("Aged Brie", 1, 50)
        assertQuality(50, item)
        assertSellIn(0, item)
    }

    @Test fun sulfurusSellInNeverGoesDown() {
        val item = testGildedRose("Sulfuras, Hand of Ragnaros", 1, 50)
        assertSellIn(1, item)
    }

    private fun assertSellIn(expectedSellIn: Int, item: Item) {
        assertEquals("sell-in", expectedSellIn, item.sellIn)
    }

    @Test fun suflurusValueNeverChanges() {
        val item = testGildedRose("Sulfuras, Hand of Ragnaros", 1, 50)
        assertQuality(50, item)
    }

    @Test fun baskstagePassesIncreaseByOneBefore10Days() {
        val item = testGildedRose("Backstage passes to a TAFKAL80ETC concert", 11, 1)
        assertQuality(2, item)
        assertEquals("sellin", 10, item.sellIn)
    }

    @Test fun backstagePassesIncreaseByTwoBefore5days() {
        val item = testGildedRose("Backstage passes to a TAFKAL80ETC concert", 6, 1)
        assertQuality(3, item)
    }

    @Test
    fun backstagePassesIncreaseByTwoBeforeConcert() {
        val item = testGildedRose("Backstage passes to a TAFKAL80ETC concert", 5, 1)
        assertQuality(4, item)
    }

    @Test fun backstagePassesAreWorthlessAfterTheConcert() {
        val item = testGildedRose("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        assertQuality(0, item)
    }

    @Test fun conjuredItemsDegradeTwiceAsFastAsNormal() {
        val item = testGildedRose("Conjured Breakfast Cereal", 1, 2)
        assertQuality(0, item)
    }

    private fun assertQuality(expectedQuality: Int, item: Item) {
        assertEquals("quality", expectedQuality, item.quality)
    }

    private fun testGildedRose(itemName: String, sellIn: Int, quality: Int): Item {
        val items = arrayOf(Item(itemName, sellIn, quality))
        val app = GildedRose(items)
        app.updateQuality()
        val item = app.items[0]
        return item
    }
}


