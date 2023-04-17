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
    private val moveLimitFrom = stringPreferencesKey("limit_from") // 이동 제한 시간
    private val moveLimitTo = stringPreferencesKey("limit_to")
    private val monFrom = stringPreferencesKey("monFrom")
    private val tueFrom = stringPreferencesKey("tueFrom")
    private val wedFrom = stringPreferencesKey("wedFrom")
    private val thuFrom = stringPreferencesKey("thuFrom")
    private val friFrom = stringPreferencesKey("friFrom")
    private val satFrom = stringPreferencesKey("satFrom")
    private val sunFrom = stringPreferencesKey("sunFrom")
    private val monTo = stringPreferencesKey("monTo")
    private val tueTo = stringPreferencesKey("tueTo")
    private val wedTo = stringPreferencesKey("wedTo")
    private val thuTo = stringPreferencesKey("thuTo")
    private val friTo = stringPreferencesKey("friTo")
    private val satTo = stringPreferencesKey("satTo")
    private val sunTo = stringPreferencesKey("sunTo")

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

    val getLimitFrom: Flow<String> =
        myDataStore.data.map { preferences -> preferences[moveLimitFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }

    val getLimitTo: Flow<String> =
        myDataStore.data.map { preferences -> preferences[moveLimitTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }

    val getMonFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[monFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getTueFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[tueFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getWedFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[wedFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getThuFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[thuFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getFriFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[friFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getSatFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[satFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getSunFrom : Flow<String> =
        myDataStore.data.map { preferences -> preferences[sunFrom] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }

    val getMonTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[monTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getTueTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[tueTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getWedTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[wedTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getThuTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[thuTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getFriTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[friTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getSatTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[satTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
                } else {
                    throw exception
                }
            }
    val getSunTo : Flow<String> =
        myDataStore.data.map { preferences -> preferences[sunTo] ?: "00:00" }
            .catch { exception ->
                if (exception is IOException) {
                    emit("00:00")
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
        limitFrom: String,
        limitTo: String,
        monOpeFrom : String,
        tueOpeFrom : String,
        wedOpeFrom : String,
        thuOpeFrom : String,
        friOpeFrom : String,
        satOpeFrom : String,
        sunOpeFrom : String,
        monOpeTo : String,
        tueOpeTo : String,
        wedOpeTo : String,
        thuOpeTo : String,
        friOpeTo : String,
        satOpeTo : String,
        sunOpeTo : String,
    ) { // 전체 저장
        myDataStore.edit { preference ->
            preference[promoteCycle] = promote
            preference[forceChargingPer] = forcePer
            preference[operatingPer] = operating
            preference[moveLimitFrom] = limitFrom
            preference[moveLimitTo] = limitTo
            preference[monFrom] = monOpeFrom
            preference[tueFrom] = tueOpeFrom
            preference[wedFrom] = wedOpeFrom
            preference[thuFrom] = thuOpeFrom
            preference[friFrom] = friOpeFrom
            preference[satFrom] = satOpeFrom
            preference[sunFrom] = sunOpeFrom
            preference[monTo] = monOpeTo
            preference[tueTo] = tueOpeTo
            preference[wedTo] = wedOpeTo
            preference[thuTo] = thuOpeTo
            preference[friTo] = friOpeTo
            preference[satTo] = satOpeTo
            preference[sunTo] = sunOpeTo
        }
    }
}