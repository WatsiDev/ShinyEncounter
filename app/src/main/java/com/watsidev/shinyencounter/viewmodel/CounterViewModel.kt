package com.watsidev.shinyencounter.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel: ViewModel()  {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    private val _plus = MutableStateFlow(1)
    val plus = _plus.asStateFlow()

    private val _expanded = MutableStateFlow(false)
    val expanded = _expanded.asStateFlow()

    fun increment() {
        if (_plus.value != 5)  _count.value++  else _count.value += 5
    }

    fun decrement() {
        if (_plus.value != 5 && _count.value > 0) {
            _count.value--
        }  else if (_count.value > 0) {
            _count.value -= 5
        } else if (_count.value < 0) {
            _count.value = 0
        }
    }

    fun setPlus(value: Int) {
        _plus.value = value
    }

    fun setExpanded(value: Boolean) {
        _expanded.value = value
    }

    fun reset(){
        _count.value = 0
    }
}