package com.kashish.product.pcos.activity

import android.app.Activity
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.kashish.product.pcos.R
import com.kashish.product.pcos.entity.Input
import com.kashish.product.pcos.rest.RestClient
import com.kashish.product.pcos.rest.RestInterface
import kotlinx.android.synthetic.main.question_one.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response


class QuestionOne : AppCompatActivity() {
    var ageString: String = "13"
    lateinit var input: Input
    var counter = 1
    var isOverWeight = -1
    var isWeightGain = -1
    var isPeriods = -1
    var isConceiving = -1
    var isChin_hair = -1.0F
    var isCheeks_hair = -1.0F
    var isUpper_lips_hair = -1.0F
    var isBetween_breasts_hair = -1.0F
    var isArms_hair = -1.0F
    var is_inner_thigh_hair = -1.0F
    var is_acne_or_skin_tag = -1
    var is_hair_thinning = -1
    var is_dark_patches = -1
    var is_tiredness = -1
    var is_mood_swings = -1
    var is_exercise = -1.0F
    var is_eat_outside = -1.0F
    var is_canned_food = -1
    var is_city = -1
    internal lateinit var restInterface: RestInterface
    internal lateinit var restClient: RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_one)

        input = Input()
        restClient= RestClient()
        restInterface=restClient.apiService

        age.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setCancelable(false)
            val picker = NumberPicker(this)
            picker.minValue = 13
            picker.maxValue = 45
            val parent = FrameLayout(this)
            parent.addView(
                picker, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            )
            builder.setView(parent)
            picker.setOnValueChangedListener { _, _, newVal ->
                ageString = (newVal.toString())
            }
            picker.value = ageString.toInt()
            picker.wrapSelectorWheel = false
            picker.isFocusable = false
            builder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
                age.setText(ageString)
                dialogInterface.dismiss()
            }
            builder.setNegativeButton("Cancel") { dialogInterface, which ->
                dialogInterface.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        over_weight_yes.setOnClickListener {
            buttonSelection(over_weight_yes, over_weight_no)
            isOverWeight = 1
        }
        over_weight_no.setOnClickListener {
            buttonSelection(over_weight_no, over_weight_yes)
            isOverWeight = 0
        }

        weight_gain_yes.setOnClickListener {
            buttonSelection(weight_gain_yes, weight_gain_no)
            isWeightGain = 1
        }
        weight_gain_no.setOnClickListener {
            buttonSelection(weight_gain_no, weight_gain_yes)
            isWeightGain = 0
        }

        periods_yes.setOnClickListener {
            buttonSelection(periods_yes, periods_no)
            isPeriods = 1
        }
        periods_no.setOnClickListener {
            buttonSelection(periods_no, periods_yes)
            isPeriods = 0
        }

        conceiving_yes.setOnClickListener {
            buttonSelection(conceiving_yes, conceiving_no)
            isConceiving = 1
        }
        conceiving_no.setOnClickListener {
            buttonSelection(conceiving_no, conceiving_yes)
            isConceiving = 0
        }

        acne_yes.setOnClickListener {
            buttonSelection(acne_yes, acne_no)
            is_acne_or_skin_tag = 1
        }
        acne_no.setOnClickListener {
            buttonSelection(acne_no, acne_yes)
            is_acne_or_skin_tag = 0
        }

        thinning_yes.setOnClickListener {
            buttonSelection(thinning_yes, thinning_no)
            is_hair_thinning = 1
        }
        thinning_no.setOnClickListener {
            buttonSelection(thinning_no, thinning_yes)
            is_hair_thinning = 0
        }

        patches_yes.setOnClickListener {
            buttonSelection(patches_yes, patches_no)
            is_dark_patches = 1
        }
        patches_no.setOnClickListener {
            buttonSelection(patches_no, patches_yes)
            is_dark_patches = 0
        }

        tiredness_yes.setOnClickListener {
            buttonSelection(tiredness_yes, tiredness_no)
            is_tiredness = 1
        }
        tiredness_no.setOnClickListener {
            buttonSelection(tiredness_no, tiredness_yes)
            is_tiredness = 0
        }

        mood_swings_yes.setOnClickListener {
            buttonSelection(mood_swings_yes, mood_swings_no)
            is_mood_swings = 1
        }
        mood_swings_no.setOnClickListener {
            buttonSelection(mood_swings_no, mood_swings_yes)
            is_mood_swings = 0
        }

        canned_yes.setOnClickListener {
            buttonSelection(canned_yes, canned_no)
            is_canned_food = 1
        }
        canned_no.setOnClickListener {
            buttonSelection(canned_no, canned_yes)
            is_canned_food = 0
        }

        city_yes.setOnClickListener {
            buttonSelection(city_yes, city_no)
            is_city = 1
        }
        city_no.setOnClickListener {
            buttonSelection(city_no, city_yes)
            is_city = 0
        }

        chin1.setOnClickListener {
            buttonSelection5(chin1, chin2, chin3, chin4, chin5)
            isChin_hair = 1.0F
        }
        chin2.setOnClickListener {
            buttonSelection5(chin2, chin1, chin3, chin4, chin5)
            isChin_hair = 2.0F
        }
        chin3.setOnClickListener {
            buttonSelection5(chin3, chin2, chin1, chin4, chin5)
            isChin_hair = 3.0F
        }
        chin4.setOnClickListener {
            buttonSelection5(chin4, chin2, chin3, chin1, chin5)
            isChin_hair = 4.0F
        }
        chin5.setOnClickListener {
            buttonSelection5(chin5, chin2, chin3, chin4, chin1)
            isChin_hair = 5.0F
        }

        cheek1.setOnClickListener {
            buttonSelection5(cheek1, cheek2, cheek3, cheek4, cheek5)
            isCheeks_hair = 1.0F
        }
        cheek2.setOnClickListener {
            buttonSelection5(cheek2, cheek1, cheek3, cheek4, cheek5)
            isCheeks_hair = 2.0F
        }
        cheek3.setOnClickListener {
            buttonSelection5(cheek3, cheek2, cheek1, cheek4, cheek5)
            isCheeks_hair = 3.0F
        }
        cheek4.setOnClickListener {
            buttonSelection5(cheek4, cheek2, cheek3, cheek1, cheek5)
            isCheeks_hair = 4.0F
        }
        cheek5.setOnClickListener {
            buttonSelection5(cheek5, cheek2, cheek3, cheek4, cheek1)
            isCheeks_hair = 5.0F
        }

        upper1.setOnClickListener {
            buttonSelection5(upper1, upper2, upper3, upper4, upper5)
            isUpper_lips_hair = 1.0F
        }
        upper2.setOnClickListener {
            buttonSelection5(upper2, upper1, upper3, upper4, upper5)
            isUpper_lips_hair = 2.0F
        }
        upper3.setOnClickListener {
            buttonSelection5(upper3, upper2, upper1, upper4, upper5)
            isUpper_lips_hair = 3.0F
        }
        upper4.setOnClickListener {
            buttonSelection5(upper4, upper2, upper3, upper1, upper5)
            isUpper_lips_hair = 4.0F
        }
        upper5.setOnClickListener {
            buttonSelection5(upper5, upper2, upper3, upper4, upper1)
            isUpper_lips_hair = 5.0F
        }

        breast1.setOnClickListener {
            buttonSelection5(breast1, breast2, breast3, breast4, breast5)
            isBetween_breasts_hair = 1.0F
        }
        breast2.setOnClickListener {
            buttonSelection5(breast2, breast1, breast3, breast4, breast5)
            isBetween_breasts_hair = 2.0F
        }
        breast3.setOnClickListener {
            buttonSelection5(breast3, breast2, breast1, breast4, breast5)
            isBetween_breasts_hair = 3.0F
        }
        breast4.setOnClickListener {
            buttonSelection5(breast4, breast2, breast3, breast1, breast5)
            isBetween_breasts_hair = 4.0F
        }
        breast5.setOnClickListener {
            buttonSelection5(breast5, breast2, breast3, breast4, breast1)
            isBetween_breasts_hair = 5.0F
        }

        arm1.setOnClickListener {
            buttonSelection5(arm1, arm2, arm3, arm4, arm5)
            isArms_hair = 1.0F
        }
        arm2.setOnClickListener {
            buttonSelection5(arm2, arm1, arm3, arm4, arm5)
            isArms_hair = 2.0F
        }
        arm3.setOnClickListener {
            buttonSelection5(arm3, arm2, arm1, arm4, arm5)
            isArms_hair = 3.0F
        }
        arm4.setOnClickListener {
            buttonSelection5(arm4, arm2, arm3, arm1, arm5)
            isArms_hair = 4.0F
        }
        arm5.setOnClickListener {
            buttonSelection5(arm5, arm2, arm3, arm4, arm1)
            isArms_hair = 5.0F
        }

        thigh1.setOnClickListener {
            buttonSelection5(thigh1, thigh2, thigh3, thigh4, thigh5)
            is_inner_thigh_hair = 1.0F
        }
        thigh2.setOnClickListener {
            buttonSelection5(thigh2, thigh1, thigh3, thigh4, thigh5)
            is_inner_thigh_hair = 2.0F
        }
        thigh3.setOnClickListener {
            buttonSelection5(thigh3, thigh2, thigh1, thigh4, thigh5)
            is_inner_thigh_hair = 3.0F
        }
        thigh4.setOnClickListener {
            buttonSelection5(thigh4, thigh2, thigh3, thigh1, thigh5)
            is_inner_thigh_hair = 4.0F
        }
        thigh5.setOnClickListener {
            buttonSelection5(thigh5, thigh2, thigh3, thigh4, thigh1)
            is_inner_thigh_hair = 5.0F
        }

        exercise1.setOnClickListener {
            buttonSelection7(exercise1, exercise2, exercise3, exercise4, exercise5, exercise6, exercise7)
            is_exercise = 1.0F
        }
        exercise2.setOnClickListener {
            buttonSelection7(exercise2, exercise1, exercise3, exercise4, exercise5, exercise6, exercise7)
            is_exercise = 2.0F
        }
        exercise3.setOnClickListener {
            buttonSelection7(exercise3, exercise2, exercise1, exercise4, exercise5, exercise6, exercise7)
            is_exercise = 3.0F
        }
        exercise4.setOnClickListener {
            buttonSelection7(exercise4, exercise2, exercise3, exercise1, exercise5, exercise6, exercise7)
            is_exercise = 4.0F
        }
        exercise5.setOnClickListener {
            buttonSelection7(exercise5, exercise2, exercise3, exercise4, exercise1, exercise6, exercise7)
            is_exercise = 5.0F
        }
        exercise6.setOnClickListener {
            buttonSelection7(exercise6, exercise2, exercise3, exercise4, exercise5, exercise1, exercise7)
            is_exercise = 6.0F
        }
        exercise7.setOnClickListener {
            buttonSelection7(exercise7, exercise2, exercise3, exercise4, exercise5, exercise6, exercise1)
            is_exercise = 7.0F
        }

        eat1.setOnClickListener {
            buttonSelection12(eat1, eat2, eat3, eat4, eat5, eat6, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 1.0F
        }
        eat2.setOnClickListener {
            buttonSelection12(eat2, eat1, eat3, eat4, eat5, eat6, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 2.0F
        }
        eat3.setOnClickListener {
            buttonSelection12(eat3, eat2, eat1, eat4, eat5, eat6, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 3.0F
        }
        eat4.setOnClickListener {
            buttonSelection12(eat4, eat2, eat3, eat1, eat5, eat6, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 4.0F
        }
        eat5.setOnClickListener {
            buttonSelection12(eat5, eat2, eat3, eat4, eat1, eat6, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 5.0F
        }
        eat6.setOnClickListener {
            buttonSelection12(eat6, eat2, eat3, eat4, eat5, eat1, eat7, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 6.0F
        }
        eat7.setOnClickListener {
            buttonSelection12(eat7, eat2, eat3, eat4, eat5, eat6, eat1, eat8, eat9, eat10, eat11, eat12)
            is_eat_outside = 7.0F
        }
        eat8.setOnClickListener {
            buttonSelection12(eat8, eat2, eat3, eat4, eat5, eat6, eat7, eat1, eat9, eat10, eat11, eat12)
            is_eat_outside = 8.0F
        }
        eat9.setOnClickListener {
            buttonSelection12(eat9, eat2, eat3, eat4, eat5, eat6, eat7, eat8, eat1, eat10, eat11, eat12)
            is_eat_outside = 9.0F
        }
        eat10.setOnClickListener {
            buttonSelection12(eat10, eat2, eat3, eat4, eat5, eat6, eat7, eat8, eat9, eat1, eat11, eat12)
            is_eat_outside = 10.0F
        }
        eat11.setOnClickListener {
            buttonSelection12(eat11, eat2, eat3, eat4, eat5, eat6, eat7, eat8, eat9, eat10, eat1, eat12)
            is_eat_outside = 11.0F
        }
        eat12.setOnClickListener {
            buttonSelection12(eat12, eat2, eat3, eat4, eat5, eat6, eat7, eat8, eat9, eat10, eat11, eat1)
            is_eat_outside = 12.0F
        }

        next_button.setOnClickListener {
            when (counter) {
                1 -> {
                    when {
                        name.text.isEmpty() -> blinkView(ll1)
                        age.text.isEmpty() -> blinkView(ll1)
                        else -> {
                            input.name = name.text.toString()
                            input.age = ageString.toFloat()
                            back2.visibility = View.VISIBLE
                            enableDisableViewGroup(ll1, false)
                            hideKeyboard(this@QuestionOne)
                            cv_extra.visibility = View.GONE
                            nextCard(cv_extra, cv1, cv2, cv3)
                            changeBackground(ll1, ll2)
                            ll2.requestFocus()
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                ll2.focusable = View.FOCUSABLE
                            }
                            ll2.isFocusableInTouchMode = true
                            cv2.requestFocus()
                            over_weight_yes.requestFocus()
                            counter += 1
                        }
                    }
                }
                2 -> {
                    when {
                        isOverWeight == -1 -> cardIncomplete(ll2, cv_extra, cv1)
                        isWeightGain == -1 -> cardIncomplete(ll2, cv_extra, cv1)
                        else -> {
                            input.over_weight = isOverWeight
                            input.weight_gain = isWeightGain
                            back2.visibility = View.INVISIBLE
                            back3.visibility = View.VISIBLE
                            enableDisableViewGroup(ll2, false)
                            nextCard(cv1, cv2, cv3, cv4)
                            changeBackground(ll2, ll3)
                            counter += 1
                        }
                    }
                }
                3 -> {
                    when {
                        isPeriods == -1 -> cardIncomplete(ll3, cv1, cv2)
                        isConceiving == -1 -> cardIncomplete(ll3, cv1, cv2)
                        else -> {
                            input.periods = isPeriods
                            input.conceiving = isConceiving
                            back3.visibility = View.INVISIBLE
                            back4.visibility = View.VISIBLE
                            enableDisableViewGroup(ll3, false)
                            cv4.visibility = View.VISIBLE
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            changeBackground(ll3, ll4)
                            counter += 1
                        }
                    }
                }
                4 -> {
                    when {

                        isChin_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        isCheeks_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        isUpper_lips_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        isBetween_breasts_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        isArms_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        is_inner_thigh_hair == -1.0F -> {
                            scrollView.smoothScrollTo(0, cv3.bottom)
                            blinkView(ll4)
                        }
                        else -> {
                            input.chin_hair = isChin_hair
                            input.cheeks_hair = isCheeks_hair
                            input.upper_lips_hair = isUpper_lips_hair
                            input.between_breasts_hair = isBetween_breasts_hair
                            input.arms_hair = isArms_hair
                            input.inner_thigh_hair = is_inner_thigh_hair
                            back4.visibility = View.INVISIBLE
                            back5.visibility = View.VISIBLE
                            enableDisableViewGroup(ll4, false)
                            nextCard(cv4, cv4, cv5, cv6)
                            changeBackground(ll4, ll5)
                            counter += 1
                        }
                    }
                }
                5 -> {
                    when {
                        is_acne_or_skin_tag == -1 -> cardIncomplete(ll5, cv4, cv4)
                        is_hair_thinning == -1 -> cardIncomplete(ll5, cv4, cv4)
                        is_dark_patches == -1 -> cardIncomplete(ll5, cv4, cv4)
                        else -> {
                            input.acne_or_skin_tag = is_acne_or_skin_tag
                            input.hair_thinning = is_hair_thinning
                            input.dark_patches = is_dark_patches
                            back5.visibility = View.INVISIBLE
                            back6.visibility = View.VISIBLE
                            enableDisableViewGroup(ll5, false)
                            nextCard(cv4, cv5, cv6, cv7)
                            changeBackground(ll5, ll6)
                            counter += 1
                        }
                    }
                }
                6 -> {
                    when {
                        is_tiredness == -1 -> cardIncomplete(ll6, cv4, cv5)
                        is_mood_swings == -1 -> cardIncomplete(ll6, cv4, cv5)
                        else -> {
                            input.tiredness = is_tiredness
                            input.mood_swings = is_mood_swings
                            back6.visibility = View.INVISIBLE
                            back7.visibility = View.VISIBLE
                            enableDisableViewGroup(ll6, false)
                            nextCard(cv5, cv6, cv7, cv8)
                            changeBackground(ll6, ll7)
                            counter += 1
                        }
                    }
                }
                7 -> {
                    when {
                        is_exercise == -1.0F -> cardIncomplete(ll7, cv5, cv6)
                        is_eat_outside == -1.0F -> cardIncomplete(ll7, cv5, cv6)
                        else -> {
                            input.exercise = is_exercise
                            input.eat_outside = is_eat_outside
                            back7.visibility = View.INVISIBLE
                            back8.visibility = View.VISIBLE
                            enableDisableViewGroup(ll7, false)
                            cv8.visibility = View.VISIBLE
                            cv7.elevation = resources.getDimension(R.dimen.card_elevation_1)
                            cv8.elevation = resources.getDimension(R.dimen.card_elevation_10)
                            scrollView.smoothScrollTo(0, cv8.bottom)
                            changeBackground(ll7, ll8)
                            counter += 1
                            next_button.text = "Check result"
                        }
                    }
                }
                8 -> {
                    when {
                        is_canned_food == -1 -> blinkView(ll8)
                        is_city == -1 -> blinkView(ll8)
                        else -> {
                            input.canned_food = is_canned_food
                            input.city = is_city
                            input.diagnose = 1
                            calculateResult(input)
                            back8.visibility = View.INVISIBLE
                            enableDisableViewGroup(ll8, false)
                            cv8.elevation = resources.getDimension(R.dimen.card_elevation_1)
                            ll8.background = resources.getDrawable(R.drawable.borderless_rounded)
                            Toast.makeText(this, "Calculating Result", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        back2.setOnClickListener {
            enableDisableViewGroup(ll1, true)
            cv_extra.visibility = View.INVISIBLE
            cv2.visibility = View.GONE
            cv3.visibility = View.GONE
            changeBackground(ll2, ll1)
            focusPreviousView(cv_extra, cv1)
            counter -= 1
        }
        back3.setOnClickListener {
            enableDisableViewGroup(ll2, true)
            previousCard(cv1, cv2, cv3, cv4)
            changeBackground(ll3, ll2)
            back2.visibility = View.VISIBLE
            counter -= 1
        }
        back4.setOnClickListener {
            enableDisableViewGroup(ll3, true)
            previousCard(cv2, cv3, cv4, cv5)
            changeBackground(ll4, ll3)
            focusPreviousView(cv1, cv2)
            back3.visibility = View.VISIBLE
            counter -= 1
        }
        back5.setOnClickListener {
            enableDisableViewGroup(ll4, true)
            previousCard(cv3, cv4, cv5, cv6)
            changeBackground(ll5, ll4)
            cv5.visibility = View.GONE
            back4.visibility = View.VISIBLE
            scrollView.smoothScrollTo(scrollView.bottom, cv4.bottom)
            counter -= 1
        }
        back6.setOnClickListener {
            enableDisableViewGroup(ll5, true)
            previousCard(cv4, cv5, cv6, cv7)
            changeBackground(ll6, ll5)
            back5.visibility = View.VISIBLE
            counter -= 1
        }
        back7.setOnClickListener {
            enableDisableViewGroup(ll6, true)
            previousCard(cv5, cv6, cv7, cv8)
            changeBackground(ll7, ll6)
            back6.visibility = View.VISIBLE
            counter -= 1
        }
        back8.setOnClickListener {
            changeBackground(ll8, ll7)
            enableDisableViewGroup(ll7, true)
            cv8.visibility = View.INVISIBLE
            scrollView.smoothScrollTo(scrollView.bottom, cv8.bottom)
            back7.visibility = View.VISIBLE
            counter -= 1
        }
    }

    private fun calculateResult(input: Input) {
        progress_bar.visibility=View.VISIBLE
        restInterface.getResult(input, object : Callback<String> {
            override fun success(t: String?, response: Response?) {
                progress_bar.visibility=View.GONE
                Toast.makeText(this@QuestionOne,t!!,Toast.LENGTH_LONG).show()
            }
            override fun failure(error: RetrofitError?) {
                progress_bar.visibility=View.GONE
                Toast.makeText(this@QuestionOne,"Failed",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun changeBackground(llOld: LinearLayout, llNew: LinearLayout) {
        llOld.background = resources.getDrawable(R.drawable.borderless_rounded)
        llNew.background = resources.getDrawable(R.drawable.border_rounded)
    }

    private fun nextCard(cvPrevious: CardView, cvOld: CardView, cvNew: CardView, cvNext: CardView) {
        cvNew.visibility = View.VISIBLE
        cvNext.visibility = View.INVISIBLE
        cvNew.elevation = resources.getDimension(R.dimen.card_elevation_10)
        cvOld.elevation = resources.getDimension(R.dimen.card_elevation_1)

//        cvNew.parent.requestChildFocus(cvNew, cvNew)
        focusOnView(cvPrevious, cvOld)
//        scrollToView(scrollView,cvNew)
    }

    private fun previousCard(
        cvMorePrevious: CardView,
        cvPrevious: CardView,
        cvOld: CardView,
        cvNew: CardView
    ) {
        cvOld.visibility = View.INVISIBLE
        cvNew.visibility = View.GONE
        cvOld.elevation = resources.getDimension(R.dimen.card_elevation_10)
        focusPreviousView(cvMorePrevious, cvPrevious)
    }

    private fun buttonSelection(button: Button, otherButton: Button) {
        button.background = resources.getDrawable(R.color.colorPrimary)
        button.setTextColor(resources.getColor(R.color.blue))

        otherButton.background = resources.getDrawable(R.color.blue)
        otherButton.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    private fun buttonSelection5(button1: Button, button2: Button, button3: Button, button4: Button, button5: Button) {
        button1.background = resources.getDrawable(R.color.colorPrimary)
        button1.setTextColor(resources.getColor(R.color.blue))

        button2.background = resources.getDrawable(R.color.blue)
        button2.setTextColor(resources.getColor(R.color.colorPrimary))

        button3.background = resources.getDrawable(R.color.blue)
        button3.setTextColor(resources.getColor(R.color.colorPrimary))

        button4.background = resources.getDrawable(R.color.blue)
        button4.setTextColor(resources.getColor(R.color.colorPrimary))

        button5.background = resources.getDrawable(R.color.blue)
        button5.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    private fun buttonSelection7(
        button1: Button,
        button2: Button,
        button3: Button,
        button4: Button,
        button5: Button,
        button6: Button,
        button7: Button
    ) {
        button1.background = resources.getDrawable(R.color.colorPrimary)
        button1.setTextColor(resources.getColor(R.color.blue))

        button2.background = resources.getDrawable(R.color.blue)
        button2.setTextColor(resources.getColor(R.color.colorPrimary))

        button3.background = resources.getDrawable(R.color.blue)
        button3.setTextColor(resources.getColor(R.color.colorPrimary))

        button4.background = resources.getDrawable(R.color.blue)
        button4.setTextColor(resources.getColor(R.color.colorPrimary))

        button5.background = resources.getDrawable(R.color.blue)
        button5.setTextColor(resources.getColor(R.color.colorPrimary))

        button6.background = resources.getDrawable(R.color.blue)
        button6.setTextColor(resources.getColor(R.color.colorPrimary))

        button7.background = resources.getDrawable(R.color.blue)
        button7.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    private fun buttonSelection12(
        button1: Button,
        button2: Button,
        button3: Button,
        button4: Button,
        button5: Button,
        button6: Button,
        button7: Button,
        button8: Button,
        button9: Button,
        button10: Button,
        button11: Button,
        button12: Button
    ) {
        button1.background = resources.getDrawable(R.color.colorPrimary)
        button1.setTextColor(resources.getColor(R.color.blue))

        button2.background = resources.getDrawable(R.color.blue)
        button2.setTextColor(resources.getColor(R.color.colorPrimary))

        button3.background = resources.getDrawable(R.color.blue)
        button3.setTextColor(resources.getColor(R.color.colorPrimary))

        button4.background = resources.getDrawable(R.color.blue)
        button4.setTextColor(resources.getColor(R.color.colorPrimary))

        button5.background = resources.getDrawable(R.color.blue)
        button5.setTextColor(resources.getColor(R.color.colorPrimary))

        button6.background = resources.getDrawable(R.color.blue)
        button6.setTextColor(resources.getColor(R.color.colorPrimary))

        button7.background = resources.getDrawable(R.color.blue)
        button7.setTextColor(resources.getColor(R.color.colorPrimary))

        button8.background = resources.getDrawable(R.color.blue)
        button8.setTextColor(resources.getColor(R.color.colorPrimary))

        button9.background = resources.getDrawable(R.color.blue)
        button9.setTextColor(resources.getColor(R.color.colorPrimary))

        button10.background = resources.getDrawable(R.color.blue)
        button10.setTextColor(resources.getColor(R.color.colorPrimary))

        button11.background = resources.getDrawable(R.color.blue)
        button11.setTextColor(resources.getColor(R.color.colorPrimary))

        button12.background = resources.getDrawable(R.color.blue)
        button12.setTextColor(resources.getColor(R.color.colorPrimary))
    }

    private fun cardIncomplete(layout: LinearLayout, cv1: CardView, cv2: CardView) {
        focusOnView(cv1, cv2)
        scrollView.post {
            blinkView(layout)
        }
    }


    private fun focusOnView(child: View, child3: View) {
        scrollView.post {
            scrollView.smoothScrollTo(0, (child.bottom + child3.bottom) / 2)
        }
    }

    private fun focusPreviousView(child: View, child3: View) {
        scrollView.post {
            scrollView.smoothScrollTo(scrollView.bottom, (child.bottom + child3.bottom) / 2)
        }
    }

    private fun blinkView(layout: LinearLayout) {
        val animation = AlphaAnimation(1f, 0f) //to change visibility from visible to invisible
        animation.duration = 50 //1 second duration for each animation cycle
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = 4 //repeating indefinitely
        animation.repeatMode = Animation.REVERSE //animation will start from end point once ended.
        layout.startAnimation(animation) //to start animation
    }

    fun enableDisableViewGroup(viewGroup: ViewGroup, enabled: Boolean) {
        val childCount = viewGroup.childCount
        for (i in 0 until childCount) {
            val view = viewGroup.getChildAt(i)
            view.isEnabled = enabled
            if (view is ViewGroup) {
                enableDisableViewGroup(view, enabled)
            }
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}