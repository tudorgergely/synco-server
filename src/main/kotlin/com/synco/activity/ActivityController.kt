package com.synco.activity

import com.synco.domain.Activity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.time.Duration
import java.util.*

/**
 * @author Tudor Gergely, Catalysts GmbH
 */
@RequestMapping("/timeline")
@Controller
class ActivityController(val activityRepository: ActivityRepository) {
    @GetMapping("")
    @CrossOrigin
    @ResponseBody
    fun getTimeline(): TimelineDTO {
        val activities = activityRepository.findFirst50ByOrderByDateDesc()
        val today = Date()
        val todayItems = ArrayList<Activity>()
        val yesterdayItems = ArrayList<Activity>()
        val lastWeekItems = ArrayList<Activity>()
        val lastMonthItems = ArrayList<Activity>()
        val lastYearItems = ArrayList<Activity>()


        for (activity in activities) {
            if (Duration.between(activity.date.toInstant(), today.toInstant()).toDays() == 0L) {
                todayItems.add(activity)
            } else if (Duration.between(activity.date.toInstant(), today.toInstant()).toDays() == 1L) {
                yesterdayItems.add(activity)
            } else if (Duration.between(activity.date.toInstant(), today.toInstant()).toDays() <= 7L) {
                lastWeekItems.add(activity)
            } else if (Duration.between(activity.date.toInstant(), today.toInstant()).toDays() <= 30L) {
                lastMonthItems.add(activity)
            } else if (Duration.between(activity.date.toInstant(), today.toInstant()).toDays() <= 365L) {
                lastYearItems.add(activity)
            } else {
                break;
            }
        }

        val todayItem = TimelineItemDTO("Today", todayItems)
        val yesterdayItem = TimelineItemDTO("Yesterday", yesterdayItems)
        val lastWeekItem = TimelineItemDTO("Last week", lastWeekItems)
        val lastMonthItem = TimelineItemDTO("Last month", lastMonthItems)
        val lastYearItem = TimelineItemDTO("Last year", lastYearItems)

        val timelineItems = arrayListOf(todayItem, yesterdayItem, lastWeekItem, lastMonthItem, lastYearItem)
        val timeline = TimelineDTO(timelineItems)

        return timeline
    }

    @GetMapping("/recentSearches")
    @ResponseBody
    @CrossOrigin
    fun getRecentSearches(): List<Activity> {
        return activityRepository.findFirst5ByTypeOrderByDateDesc("search")
    }
}