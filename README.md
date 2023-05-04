# MiniRobot

### 프로젝트 사양 참고
* 로봇
  * 1920px * 1080px (14-inch)
    ```
    @Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 1000, heightDp = 410)
  * OS : Based on Android 9
  * Sample App : <https://github.com/OrionStarGIT/RobotSample>
  * 로봇 해상도 : 560 dpi (px / dp / sp 변환 시 주의)
  </br>
* 앱 아키텍쳐 : MVVM 아키텍쳐 (Model - View - View Model)
    * Model : data > Dummy Data (Repo 역할)
    * View : view > Composable 요소 들 (Fragment 대체)
    * View Model : util 폴더 내 데이터 클래스 (23.03.27 추후 추가 예정)
  </br>
</br>

******************
### 소스 코드 참고 (Last Edit : 23.04.28)
* 

******************
### 폴더 구조 (Last Edit : 23.04.28)
![image](https://user-images.githubusercontent.com/68893329/235062696-1242c60f-68c7-4b68-bd6f-51b79475901d.png)
</br>
* data : sealed class / enum class / object 등 각종 더미 데이터, 상태 등
  * admin : 관리자 페이지 관련 데이터 / 설정 값
  * network : 네트워크 상태 정의
  * page : 페이지 별 dummy data (페이지 별 버튼 정보)
  * robot : 로봇 이동 / 제어 등에 필요한 데이터 사전 정의
* model : 각종 ViewModel (main / navigation / robot view-model)
* repo : 로봇 제어를 위한 repository
* util : 각종 class ..
* view : View에 해당하는 항목들 전부 포함
  * components : custom compose item
  * page : top page, menu, 이동 페이지 포함
  * layout : 화면 layout
  * navigation : NavGraph / NavRoute / RouteAction (NavGraph는 composable / NavRoute는 sealed class / RouteAction은 동작을 정의한 class)
* MainActivity : Activity는 MainActivity 하나만 사용.

******************
### View 참고 사항 (Last Edit : 23.04.28)
![image](https://user-images.githubusercontent.com/68893329/235065228-0efc0f07-b866-4ff4-b7b6-2dbe7cadd4fd.png)
- 페이지 별로 Composable 하나씩 정의
- gnb 화면 (상단 바) 같이 동일하게 나타나는 item들은 common 항목에서 관리
- gnb 바 제외 디자인은 이미지 전체 교환(drawable)

******************
### 디자인 변경 시 주의
  * gnb 따로 / 본문 화면 따로
  * view\hospital\nameMenu 에 있는 composable : 화면에 유동적으로 표시되어야 하는 정보가 포함되어 있음 
  => 전체 이미지로 작업하지 않고 나눠서 작업했기 때문에 수정할 때도 주의 (text 따로 추가 / 배경 image는 동일한 리소스 사용)
  * reservation-confirm / name-calling : 배경 이미지 없고 아이콘 - text - button 형태라서 분리해서 작업
  * reservation-failed / name-failed : 동일한 배경 이미지를 사용하고 있는 페이지라 resource 변경되면 확인 필요
  * main / sites-information 등 버튼이 포함된 화면은 click event를 넣어주기 위해 통 이미지를 사용하지 않고 버튼 resource 분리해서 추가함.

* view\common\ui 참고 사항
  * myiconpack : svg 파일들 (jetpack compose image vector 생성)
  * theme폴더의 Type.kt/Color.kt 에 병원 별 figma 파일의 공통 Inspect 값 저장
  * 로봇 해상도가 560dpi 라 px -> dp 변환 시 주의 => 미리 변환 한 값 dimen.xml 파일에 정리해 뒀으니 참고
    * 48px 값 변환해서 가져오기 => ```dimensionResource(R.dimin.id값)```
    * id값은 padding_픽셀 값으로 지정 해뒀음.

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
* local file 경로
  * /mnt/sdcard/mini/*
    * tts : 음성 tts 파일