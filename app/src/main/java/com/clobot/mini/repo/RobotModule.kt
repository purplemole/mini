package com.clobot.mini.repo

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RobotModule {
    @Binds
    abstract fun bindAiniRepository(robotRepository: AiniRobotRepository): RobotRepository
}

//@Module
//@InstallIn(ViewModelComponent::class)
//class RobotModule {
//    @Provides
//    @Singleton
//    fun bindAiniRepository(robotRepository: AiniRobotRepository): RobotRepository {
//        return RobotRepository.Builder()
//            .
//    }
//}