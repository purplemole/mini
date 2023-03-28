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

******************

### 폴더 구조 (23.03.27 수정 필요)
![화면 캡처 2023-03-27 131624](https://user-images.githubusercontent.com/68893329/227839602-35269e2f-b7ba-4ff0-a7f4-f3dda653a2b5.jpg)
