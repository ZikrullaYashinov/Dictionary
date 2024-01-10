package zikrulla.production.dictionary.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zikrulla.production.dictionary.repository.DictionariesRepository
import zikrulla.production.dictionary.repository.impl.DictionariesRepositoryImpl
import zikrulla.production.dictionary.usecase.DictionaryUseCase
import zikrulla.production.dictionary.usecase.InputDetailsUseCase
import zikrulla.production.dictionary.usecase.impl.DictionaryUseCaseImpl
import zikrulla.production.dictionary.usecase.impl.InputDetailsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getInputDetailsUseCase(inputDetailsUseCase: InputDetailsUseCaseImpl): InputDetailsUseCase

    @Binds
    fun getDictionaryUseCase(dictionaryUseCaseImpl: DictionaryUseCaseImpl): DictionaryUseCase

}