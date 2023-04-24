package com.clobot.mini.view.navigation

//네비게이션 라우트 이넘
sealed class NavRoute(val routeName: String, val description: String, val pageWaite: Int = 0) {
    object Main : NavRoute("main", "메인", 30)

    //main - button1
    object NewCustomer : NavRoute("new-customer", "신규 고객")
    object NewInformation : NavRoute("new-information", "접수 방법", 5)

    //main - button2
    object ExistingCustomer : NavRoute("existing-customer", "당일 방문 고객")
    object ExistingInformation : NavRoute("existing-information", "예약 방법 안내", 5)

    //main - button3
    object ReservationCustomer : NavRoute("reservation-customer", "예약 고객", 30)
    object QrRecognition : NavRoute("qr-recognition", "예약 확인", 5)
    object ReservationConfirm : NavRoute("reservation-confirm", "접수 완료", 5)
    object ReservationFailed : NavRoute("reservation-failed", "접수 실패", 5)
    object WaitingArea : NavRoute("waiting-area", "대기 장소 안내", 5)
    object ReservationInformation : NavRoute("reservation-information", "예약 고객 안내", 5)

    //main - button4
    object SitesInformation : NavRoute("sites-information", "이용 안내", 10)

    //main - button4(이용 안내) - button1, button2, button3
    object HospitalHours : NavRoute("hospital-hours", "진료 시간", 10)
    object ReservationMethod : NavRoute("reservation-method", "예약 방법 안내", 10)
    object Parking : NavRoute("parking", "주차 안내", 10)
    object NameCalling : NavRoute("name-calling", "이름 부르기", 10)
    object NameQr : NavRoute("name-qr", "본인 확인", 5)
    object NameConfirm : NavRoute("name-confirm", "본인 확인 완료")
    object NameFailed : NavRoute("name-failed", "본인 확인 실패")
    object TreatmentMethod : NavRoute("treatment-method", "진료 방법 안내", 5)
    object PatientInformation : NavRoute("patient-information", "환자 안내", 20)

    // 대기 화면
    object Standby : NavRoute("standby", "대기")

    // 관리자 화면
    object Admin : NavRoute("admin", "관리자")

    object Charging : NavRoute("charging", "충전 중")

    object Developer : NavRoute("developer", "개발자 페이지", 0)

    object BootCheck : NavRoute("boot-check", "부팅 체크", 0)
}