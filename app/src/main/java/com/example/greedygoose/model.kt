package com.example.greedygoose

import android.app.NotificationManager
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.greedygoose.foreground.FloatingLayout

val memes: List<Int> = listOf(
    R.drawable.meme_1, R.drawable.meme_2, R.drawable.meme_3, R.drawable.meme_4,
    R.drawable.meme_5, R.drawable.meme_6, R.drawable.meme_7)

val theme_map: HashMap<String, HashMap<String, Int>> = hashMapOf(
    "ENG" to hashMapOf(
        "ANGRY_LEFT" to R.drawable.eng_angry_left,
        "ANGRY_LEFT2" to R.drawable.eng_angry_left2,
        "ANGRY_RIGHT" to R.drawable.eng_angry_right,
        "ANGRY_RIGHT2" to R.drawable.eng_angry_right2,
        "BEHIND_LEFT" to R.drawable.eng_behind_left,
        "BEHIND_LEFT2" to R.drawable.eng_behind_left2,
        "BEHIND_RIGHT" to R.drawable.eng_behind_right,
        "BEHIND_RIGHT2" to R.drawable.eng_behind_right2,
        "FLYING_LEFT" to R.drawable.eng_flying_left,
        "FLYING_RIGHT" to R.drawable.eng_flying_right,
        "SITTING_LEFT" to R.drawable.eng_sitting_left,
        "SITTING_RIGHT" to R.drawable.eng_sitting_right,
        "WALKING_LEFT" to R.drawable.eng_walking_left,
        "WALKING_LEFT2" to R.drawable.eng_walking_left2,
        "WALKING_RIGHT" to R.drawable.eng_walking_right,
        "WALKING_RIGHT2" to R.drawable.eng_walking_right2,
        "WINDOW_LEFT" to R.drawable.eng_window_left,
        "WINDOW_RIGHT" to R.drawable.eng_window_right,
        "ANGRY_LEFT_MIDDLE" to R.drawable.eng_angry_leftmiddle,
        "ANGRY_RIGHT_MIDDLE" to R.drawable.eng_angry_rightmiddle,
        "WALKING_LEFT_MIDDLE" to R.drawable.eng_walking_leftmiddle,
        "WALKING_RIGHT_MIDDLE" to R.drawable.eng_walking_rightmiddle,
        ),
    "MATH" to hashMapOf(
        "ANGRY_LEFT" to R.drawable.math_angry_left,
        "ANGRY_LEFT2" to R.drawable.math_angry_left2,
        "ANGRY_RIGHT" to R.drawable.math_angry_right,
        "ANGRY_RIGHT2" to R.drawable.math_angry_right2,
        "BEHIND_LEFT" to R.drawable.math_behind_left,
        "BEHIND_LEFT2" to R.drawable.math_behind_left2,
        "BEHIND_RIGHT" to R.drawable.math_behind_right,
        "BEHIND_RIGHT2" to R.drawable.math_behind_right2,
        "FLYING_LEFT" to R.drawable.math_flying_left,
        "FLYING_RIGHT" to R.drawable.math_flying_right,
        "SITTING_LEFT" to R.drawable.math_sitting_left,
        "SITTING_RIGHT" to R.drawable.math_sitting_right,
        "WALKING_LEFT" to R.drawable.math_walking_left,
        "WALKING_LEFT2" to R.drawable.math_walking_left2,
        "WALKING_RIGHT" to R.drawable.math_walking_right,
        "WALKING_RIGHT2" to R.drawable.math_walking_right2,
        "WINDOW_LEFT" to R.drawable.math_window_left,
        "WINDOW_RIGHT" to R.drawable.math_window_right,
        "ANGRY_LEFT_MIDDLE" to R.drawable.math_angry_leftmiddle,
        "ANGRY_RIGHT_MIDDLE" to R.drawable.math_angry_rightmiddle,
        "WALKING_LEFT_MIDDLE" to R.drawable.math_walking_leftmiddle,
        "WALKING_RIGHT_MIDDLE" to R.drawable.math_walking_rightmiddle,
    ),
    "SCI" to hashMapOf(
        "ANGRY_LEFT" to R.drawable.sci_angry_left,
        "ANGRY_LEFT2" to R.drawable.sci_angry_left2,
        "ANGRY_RIGHT" to R.drawable.sci_angry_right,
        "ANGRY_RIGHT2" to R.drawable.sci_angry_right2,
        "BEHIND_LEFT" to R.drawable.sci_behind_left,
        "BEHIND_LEFT2" to R.drawable.sci_behind_left2,
        "BEHIND_RIGHT" to R.drawable.sci_behind_right,
        "BEHIND_RIGHT2" to R.drawable.sci_behind_right2,
        "FLYING_LEFT" to R.drawable.sci_flying_left,
        "FLYING_RIGHT" to R.drawable.sci_flying_right,
        "SITTING_LEFT" to R.drawable.sci_sitting_left,
        "SITTING_RIGHT" to R.drawable.sci_sitting_right,
        "WALKING_LEFT" to R.drawable.sci_walking_left,
        "WALKING_LEFT2" to R.drawable.sci_walking_left2,
        "WALKING_RIGHT" to R.drawable.sci_walking_right,
        "WALKING_RIGHT2" to R.drawable.sci_walking_right2,
        "WINDOW_LEFT" to R.drawable.sci_window_left,
        "WINDOW_RIGHT" to R.drawable.sci_window_right,
        "ANGRY_LEFT_MIDDLE" to R.drawable.sci_angry_leftmiddle,
        "ANGRY_RIGHT_MIDDLE" to R.drawable.sci_angry_rightmiddle,
        "WALKING_LEFT_MIDDLE" to R.drawable.sci_walking_leftmiddle,
        "WALKING_RIGHT_MIDDLE" to R.drawable.sci_walking_rightmiddle,
    ),
    "NONE" to hashMapOf(
        "ANGRY_LEFT" to R.drawable.none_angry_left,
        "ANGRY_LEFT2" to R.drawable.none_angry_left2,
        "ANGRY_RIGHT" to R.drawable.none_angry_right,
        "ANGRY_RIGHT2" to R.drawable.none_angry_right2,
        "BEHIND_LEFT" to R.drawable.none_behind_left,
        "BEHIND_LEFT2" to R.drawable.none_behind_left2,
        "BEHIND_RIGHT" to R.drawable.none_behind_right,
        "BEHIND_RIGHT2" to R.drawable.none_behind_right2,
        "FLYING_LEFT" to R.drawable.none_flying_left,
        "FLYING_RIGHT" to R.drawable.none_flying_right,
        "SITTING_LEFT" to R.drawable.none_sitting_left,
        "SITTING_RIGHT" to R.drawable.none_sitting_right,
        "WALKING_LEFT" to R.drawable.none_walking_left,
        "WALKING_LEFT2" to R.drawable.none_walking_left2,
        "WALKING_RIGHT" to R.drawable.none_walking_right,
        "WALKING_RIGHT2" to R.drawable.none_walking_right2,
        "WINDOW_LEFT" to R.drawable.none_window_left,
        "WINDOW_RIGHT" to R.drawable.none_window_right,
        "ANGRY_LEFT_MIDDLE" to R.drawable.none_angry_leftmiddle,
        "ANGRY_RIGHT_MIDDLE" to R.drawable.none_angry_rightmiddle,
        "WALKING_LEFT_MIDDLE" to R.drawable.none_walking_leftmiddle,
        "WALKING_RIGHT_MIDDLE" to R.drawable.none_walking_rightmiddle,
    )
)

enum class TimerState {
    NOT_STARTED, RUNNING, PAUSED
}

class model {
    private var egg_count = MutableLiveData<Int>(100)
    private var theme = MutableLiveData<String>("NONE")
    private var action = MutableLiveData<String>("WALKING_LEFT")
    private var entertainment = MutableLiveData<Boolean>(false)
    private var floatingLayout: FloatingLayout? = null
    var is_tie_unlocked = true
    var is_goggle_unlocked = false
    var is_hard_hat_unlocked = false
    var is_tie_selected = false
    var is_goggle_selected = false
    var is_hard_hat_selected = false
    var timer_state = TimerState.NOT_STARTED
    var elapsed_time = 0L
    var set_time = 0L
    lateinit var r_notif_manager: NotificationManager

    fun increase_egg_count(amt: Int) {
        this.egg_count.value = this.egg_count.value?.plus(amt)
    }

    fun decrease_egg_count(amt: Int) {
        this.egg_count.value = this.egg_count.value?.minus(amt)
    }

    fun get_egg_count(): Int? {
        return this.egg_count.value
    }

    // Pass in a UI element that you want to be updated based on the egg_count
    // For now, only taking in a textview
    fun observe_egg(context: LifecycleOwner, ob: TextView) {
        this.egg_count.observe(context, Observer {
            ob.text = this.egg_count.value.toString()
        })
    }

    fun set_theme(theme: String) {
        this.theme.value = theme
    }

    fun set_action(action: String) {
        this.action.value = action
    }

    fun get_theme(): String? {
        return this.theme.value
    }

    fun get_action(): String? {
        return this.action.value
    }

    fun toggle_theme() {
        if (this.theme.value == "MATH") {
            this.theme.value = "ENG"
        } else {
            this.theme.value = "MATH"
        }
    }

    // Pass in a UI element that you want to be updated based on the egg_count
    // For now, only taking in a textview
    fun observe_theme(context: LifecycleOwner, ob: ImageView) {
        this.theme.observe(context, Observer {
            theme_map[this.theme.value]?.get(this.action.value)
                ?.let { it1 -> ob.setImageResource(it1) }
        })
    }

    fun toggle_walk(direction: String, type: String) {
        if (type =="ANGRY") {
            if (direction == "LEFT") {
                if (this.action.value == "ANGRY_LEFT") {
                    this.action.value = "ANGRY_LEFT2"
                } else {
                    this.action.value = "ANGRY_LEFT"
                }
            } else {
                if (this.action.value == "ANGRY_RIGHT") {
                    this.action.value = "ANGRY_RIGHT2"
                } else {
                    this.action.value = "ANGRY_RIGHT"
                }
            }
        } else {
            if (direction == "LEFT") {
                if (this.action.value == "WALKING_LEFT") {
                    this.action.value = "WALKING_LEFT2"
                } else {
                    this.action.value = "WALKING_LEFT"
                }
            } else {
                if (this.action.value == "WALKING_RIGHT") {
                    this.action.value = "WALKING_RIGHT2"
                } else {
                    this.action.value = "WALKING_RIGHT"
                }
            }
        }
    }

    fun observe_action(context: LifecycleOwner, ob: ImageView) {
        this.action.observe(context, Observer {
            theme_map[this.theme.value]?.get(this.action.value)
                ?.let { it1 -> ob.setImageResource(it1) }
        })
    }

    fun set_entertainment(b: Boolean) {
        this.entertainment.value = b
    }

    fun observe_entertainment(life: LifecycleOwner, context: Context) {
        this.entertainment.observe(life, Observer {
            if (it == true) {
                mod.floatingLayout = FloatingLayout(context, R.drawable.egg_small, life)
                mod.floatingLayout!!.setView()
            }
        })
    }

    fun set_timer_state(timer_state: TimerState) {
        this.timer_state = timer_state
    }

    fun get_timer_state(): TimerState {
        return this.timer_state
    }

    fun set_elapsed_time(elapsed_time: Long) {
        this.elapsed_time = elapsed_time
    }

    fun get_elapsed_time(): Long {
        return this.elapsed_time
    }

    fun set_set_time(set_time: Long) {
        this.set_time = set_time
    }

    fun get_set_time(): Long {
        return this.set_time
    }

    fun set_r_notif_manager(r_notif_manager: NotificationManager) {
        this.r_notif_manager = r_notif_manager
    }

    fun get_r_notif_manager(): NotificationManager {
        return this.r_notif_manager
    }
}