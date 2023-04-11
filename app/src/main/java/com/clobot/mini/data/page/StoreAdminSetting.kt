package com.clobot.mini.data.page

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAdminSetting(private val context: Context) {

    // singleton
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

        //preferences를 접근할 때 사용하는 Preferences.Key
        val PROMOTE_CYCLE = intPreferencesKey("promote_cycle") //stringPreferencesKey
    }


    // 이동 홍보 주기 값 가져오기
    val getPromoteCycle: Flow<Int?> =
        context.dataStore.data.map { preferences -> preferences[PROMOTE_CYCLE] ?: 0 }

    // 이동 홍보 주기 값 저장하기
    suspend fun savePromoteCycle(cycle: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROMOTE_CYCLE] = cycle
        }
    }
}