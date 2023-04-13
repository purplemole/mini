package com.clobot.mini.data.admin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

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
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@ActivityRetainedScoped
class StoreAdminSetting @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        //val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    }

    // preferences를 접근할 때 사용하는 Preferences.Key
    private val promoteCycle = intPreferencesKey("promote_cycle") //stringPreferencesKey
    private val timeCycle = stringPreferencesKey("time_cycle")

    // 데이터 읽기
    // 이동 홍보 주기 값 가져오기
    val getPromoteCycle: Flow<Int> =
        context.dataStore.data.map { preferences -> preferences[promoteCycle] ?: 1 }
            .catch { exception ->
                if (exception is IOException) {
                    emit(1) //emptyPreferences()
                } else {
                    throw exception
                }
            }

    val getTimeCycle: Flow<String> =
        context.dataStore.data.map { preferences -> preferences[timeCycle] ?: "" }


    // 데이터 쓰기
    suspend fun saveTimeCycle(cycle: String) {
        context.dataStore.edit { preference ->
            preference[timeCycle] = cycle
        }
    }

    // 전체 저장
    /**
     * TODO : 이동 제한 시간, 강제 충전 시작, 운영 시작, 운영 시간
     *
     * @param promote : 이동 홍보 주기 (int)
     */
    suspend fun saveAllAdminSetting(promote: Int) {
        context.dataStore.edit { preference ->
            preference[promoteCycle] = promote
        }
    }
}