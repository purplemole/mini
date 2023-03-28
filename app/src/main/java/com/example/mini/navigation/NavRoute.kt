package com.example.mini.navigation

//네비게이션 라우트 이넘
enum class NavRoute(val routeName: String, val description: String) {
    MAIN("main", "메인"),
    //main - button1
    NEW_CUSTOMER("new-customer","신규 고객"),
    NEW_INFORMATION("new-information", "접수 방법 안내"),
    
    //main - button2
    EXISTING_CUSTOMER("existing-customer", "당일 방문 고객"),
    EXISTING_INFORMATION("existing-information", "예약 방법 안내"),

    //main - button3
    RESERVATION_CUSTOMER("reservation-customer", "예약 고객"),
    QR_RECOGNITION("qr-recognition", "예약 확인"),
    RESERVATION_CONFIRM("reservation-confirm", "접수 완료"),
    RESERVATION_FAILED("reservation-failed", "접수 실패"),
    WAITING_AREA("waiting-area", "대기 장소 안내"),
    RESERVATION_INFORMATION("reservation-information", "예약 고객 안내"),

    //main - button4
    SITE_INFORMATION("sites-information", "이용 안내"),

    //main - button4(이용 안내) - button1, button2, button3
    HOSPITAL_HOURS("hospital-hours", "진료 시간"),
    RESERVATION_METHOD("reservation-method", "예약 방법"),
    PARKING("parking", "주차 안내"),

    NAME_CALLING("name-calling", "이름 부르기"),
    NAME_QR("name-qr", "본인 확인"),
    NAME_CONFIRM("name-confirm", "본인 확인 완료"),
    NAME_FAILED("name-failed", "본인 확인 실패"),
    TREATMENT_METHOD("treatment-method", "진료 방법 안내"),
    PATIENT_INFORMATION("patient-information", "환자 안내"),
    
    // 대기 화면
    STANDBY("standby", "대기 화면"),
}