package com.example.epsswim.presentation.ui.trainer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.epsswim.data.repositories.CompetitionRepository
import com.example.epsswim.data.repositories.TrainerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(private val competitionRepository: CompetitionRepository) : ViewModel()  {
}