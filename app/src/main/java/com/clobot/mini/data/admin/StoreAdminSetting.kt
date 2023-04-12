package com.clobot.mini.data.admin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * TODO 저장 필요한 항목 key 추가 / 아직 임시 값 사용 중 -> 추후 값 수정 필요
 * key 값 추가 -> get() -> set() -> AdminPage 연결
 * @property context : LocalContext.current
 *
 * 타입 유형 - 키 이름 지정 필요
 *
 * 간단한 코드 설명..
 * 데이터 읽기 방법 : this.dataStore.data의 flow를 생성 (이 때 에러 핸들링 코드도 추가 가능)
 * 사용 하려면 해당 데이터를 구독하는 value 생성 하면 됨.
 * 예_ val example = getPromoteCycle.collectAsState(initial = 0) 이런 식..
 *
 */
class StoreAdminSetting(private val context: Context) {
    // singleton
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        //preferences를 접근할 때 사용하는 Preferences.Key
        val PROMOTE_CYCLE = intPreferencesKey("promote_cycle") //stringPreferencesKey
        val TIME_CYCLE = stringPreferencesKey("time_cycle")
    }

    // 데이터 읽기
    // 이동 홍보 주기 값 가져오기
    val getPromoteCycle: Flow<Int?> =
        context.dataStore.data.map { preferences -> preferences[PROMOTE_CYCLE] ?: 0 }
            .catch { exception ->
                if (exception is IOException) {
                    emit(0) //emptyPreferences()
                } else {
                    throw exception
                }
            }

    val getTimeCycle: Flow<String?> =
        context.dataStore.data.map { preferences -> preferences[TIME_CYCLE] ?: "" }

    // 데이터 쓰기
    // 이동 홍보 주기 값 저장하기
    suspend fun savePromoteCycle(cycle: Int) {
        context.dataStore.edit { preferences ->
            preferences[PROMOTE_CYCLE] = cycle
        }
    }

    suspend fun saveTimeCycle(cycle : String){
        context.dataStore.edit {
            preference->
            preference[TIME_CYCLE]= cycle
        }
    }
}