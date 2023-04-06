# MiniRobot

### 프로젝트 사양 참고
* 로봇
  * 1920px * 1080px (14-inch)
    ```
    @Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
  * OS : Based on Android 9
  * Sample App : <https://github.com/OrionStarGIT/RobotSample>
  </br>
* 앱 아키텍쳐 : MVVM 아키텍쳐 (Model - View - View Model)
    * Model : data > Dummy Data (Repo 역할)
    * View : view > Composable 요소 들 (Fragment 대체)
    * View Model : util 폴더 내 데이터 클래스 (23.03.27 추후 추가 예정)
  </br>
</br>

******************
### 폴더 구조 (23.03.27 수정 필요)
![화면 캡처 2023-03-27 131624](https://user-images.githubusercontent.com/68893329/227839602-35269e2f-b7ba-4ff0-a7f4-f3dda653a2b5.jpg)
</br>
</br>

******************
### 환경 설정
* 로봇 어플리케이션의 개발은 Android 개발 환경, IDE 툴에 의존해야 하며 자세한 사양은 아래 문서를 참고.
  * https://developer.android.com/ 
* 몇 가지 주요 라이브러리 버전
  * Jetpack Compose UI version : 1.1.1
  * hilt-android version : 2.31.1
  * hilt-navigation-compose : 1.0.0
  * navigation : 2.5.3
* [Compose Api guideline](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md#naming-unit-composable-functions-as-entities) 참고
  * 파스칼 케이스 사용
  * sealed class 추천
  * Naming 규칙 일부 참고
    * @Composable Entity 이름은 대문자로 시작 - 명사
    * @Composable 값을 반환하는 Composable은 소문자로 시작
  * ViewModel 작성 시 LiveData 보다 Flow 사용 권장