package com.sunsosay.wongnaiassignment.presentation.di

import com.sunsosay.wongnaiassignment.presentation.bitcoin.BitcoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel {
        BitcoinViewModel(
            get()
        )
    }
}