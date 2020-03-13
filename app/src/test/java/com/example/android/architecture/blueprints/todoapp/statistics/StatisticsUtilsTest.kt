package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Create an active task
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )

        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    /**
     * If there is one completed task and no active tasks,
     * the activeTasks percentage should be 0f, and the completed tasks percentage should be 100f .
     */
    @Test
    fun getActiveAndCompletedStats_oneCompletedNoActive_returnsActiveZeroAndCompletedHundred() {
        val completedTask = Task("completed", "test", isCompleted = true)

        val result = getActiveAndCompletedStats(listOf(completedTask))

        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    /**
     * If there are two completed tasks and three active tasks,
     * the completed percentage should be 40f and the active percentage should be 60f.
     */
    @Test
    fun getActiveAndCompletedStats_twoCompletedThreeActive_returnsActiveSixtyAndCompletedForty() {
        // Given 3 completed tasks and 2 active tasks
        val completedTask = Task("completed", "test", isCompleted = true)
        val activeTask = Task("active", "test", isCompleted = false)
        val taskList: List<Task> = listOf(completedTask, completedTask, activeTask, activeTask, activeTask)

        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(taskList)

        // Then the result is 40-60
        assertThat(result.activeTasksPercent, `is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    /**
     * If there is an empty list (emptyList()), then both percentages should be 0f.
     */
    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        // When there are no tasks
        val result = getActiveAndCompletedStats(emptyList())

        // Both active and completed tasks are 0
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    /**
     * If there was an error loading the tasks, the list will be null, and both percentages should be 0f.
     */
    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // When there's an error loading stats
        val result = getActiveAndCompletedStats(null)

        // Both active and completed tasks are 0
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }
}