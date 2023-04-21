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
    // preferences를 접근할 때 사용하는 Preferences.Key
    private val promoteCycle = intPreferencesKey("promote_cycle") // 이동 홍보 주기
    private val forceChargingPer = intPreferencesKey("force_charging") // 강제 충전 시작 퍼센트
    private val operatingPer = intPreferencesKey("robot_operating") // 로봇 운영 시작
    private val movementRestrict = stringPreferencesKey("movement_restrict") //00:00~00:00
    private val monFromTo = stringPreferencesKey("mon_operating")
    private val tueFromTo = stringPreferencesKey("tue_operating")
    private val wedFromTo = stringPreferencesKey("wed_operating")
    private val thuFromTo = stringPreferencesKey("thu_operating")
    private val friFromTo = stringPreferencesKey("fri_operating")
    private val satFromTo = stringPreferencesKey("sat_operating")
    private val sunFromTo = stringPreferencesKey("sun_operating")

    private val myDataStore = context.dataStore

    // 데이터 읽기
    // 이동 홍보 주기 값 가져오기
    val getPromoteCycle: Flow<Int> =
        myDataStore.data.map { preferences -> preferences[promoteCycle] ?: 1 }
            .catch { exception ->
                if (exception is IOException) {
                    emit(1) //emptyPreferences()
                } else {
                    throw exception
                }
            }

    // 강제 충전 시작 퍼센트 값 가져 오기
    val getForceCharging: Flow<Int> =
        myDataStore.data.map { preferences -> preferences[forceChargingPer] ?: 10 }
            .catch { exception ->
                if (exception is IOException) {
                    emit(10)
                } else {
                    throw exception
                }
            }

    val getOperatingPer: Flow<Int> =
        myDataStore.data.map { preferences -> preferences[operatingPer] ?: 40 }
            .catch { exception ->
                if (exception is IOException) {
                    emit(40)
                } else {
                    throw exception
                }
            }

    val getMoveRestrict: Flow<String> =
        myDataStore.data.map { preferences -> preferences[movementRestrict] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }

    val getMonFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[monFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getTueFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[tueFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getWedFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[wedFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getThuFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[thuFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getFriFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[friFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getSatFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[satFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }
    val getSunFromTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[sunFromTo] ?: "00:00~00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00~00:00")
                } else {
                    throw exception
                }
            }


    // 데이터 쓰기
    /**
     * TODO : 이동 제한 시간, 강제 충전 시작, 운영 시작, 운영 시간
     *
     * @param promote : 이동 홍보 주기 (int)
     * @param forcePer : 강제 충전 시작 퍼센트 (int)
     */
    suspend fun saveAllAdminSetting(
        promote: Int,
        forcePer: Int,
        operating: Int,
        restrictT : String,
        monT : String,
        tueT : String,
        wedT : String,
        thuT : String,
        friT : String,
        satT : String,
        sunT : String,
    ) { // 전체 저장
        myDataStore.edit { preference ->
            preference[promoteCycle] = promote
            preference[forceChargingPer] = forcePer
            preference[operatingPer] = operating
            preference[movementRestrict] = restrictT
            preference[monFromTo] = monT
            preference[tueFromTo] = tueT
            preference[wedFromTo] = wedT
            preference[thuFromTo] = thuT
            preference[friFromTo] = friT
            preference[satFromTo] = satT
            preference[sunFromTo] = sunT
        }
    }

    suspend fun forTestCode(promote: Int,
                            forcePer: Int){
        myDataStore.edit { preference ->
            preference[promoteCycle] = promote
            preference[forceChargingPer] = forcePer
        }
    }
}