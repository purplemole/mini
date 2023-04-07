package com.clobot.mini.repo

import com.clobot.mini.repo.robot.AiniRobotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RobotModule {
    // When someone asks for repo, create a impl and return it.
    // If use other repo, change the impl.
    @Binds
    abstract fun bindAiniRepository(impl: AiniRobotRepository): RobotRepository
}