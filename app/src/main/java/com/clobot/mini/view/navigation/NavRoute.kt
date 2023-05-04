package com.clobot.mini.view.navigation

//네비게이션 라우트 이넘
sealed class NavRoute(val routeName: String, val pageWaite: Int = 0) {
    object Main : NavRoute("main", 30)

    //main - button1
    object NewCustomer : NavRoute("new-customer")
    object NewInformation : NavRoute("new-information", 5)

    //main - button2
    object ExistingCustomer : NavRoute("existing-customer")
    object ExistingInformation : NavRoute("existing-information", 5)

    //main - button3
    object ReservationCustomer : NavRoute("reservation-customer", 30)
    object QrRecognition : NavRoute("qr-recognition", 5)
    object ReservationConfirm : NavRoute("reservation-confirm", 5)
    object ReservationFailed : NavRoute("reservation-failed", 5)
    object WaitingArea : NavRoute("waiting-area", 5)
    object ReservationInformation : NavRoute("reservation-information", 5)

    //main - button4
    object SitesInformation : NavRoute("sites-information", 10)

    //main - button4(이용 안내) - button1, button2, button3
    object HospitalHours : NavRoute("hospital-hours", 10)
    object ReservationMethod : NavRoute("reservation-method", 10)
    object Parking : NavRoute("parking", 10)
    object NameCalling : NavRoute("name-calling", 10)
    object NameQr : NavRoute("name-qr", 5)
    object NameConfirm : NavRoute("name-confirm")
    object NameFailed : NavRoute("name-failed")
    object TreatmentMethod : NavRoute("treatment-method", 5)
    object PatientInformation : NavRoute("patient-information", 20)

    // 대기 화면
    object Standby : NavRoute("standby")

    // 이동 관련 화면
    object MovePosition1 : NavRoute("move-position_1")
    object MovePosition2 : NavRoute("move-position_2")
    object MovePosition3 : NavRoute("move-position_3")
    object MoveName : NavRoute("move-name")
    object MovePromote : NavRoute("move-promote")


    // 관리자 화면
    object Admin : NavRoute("admin")
    object Developer : NavRoute("developer", 0)

    object BootCheck : NavRoute("boot-check", 0)

    // 충전 관련
    object Charging : NavRoute("charging")
    object MoveCharge : NavRoute("move-charge")
}