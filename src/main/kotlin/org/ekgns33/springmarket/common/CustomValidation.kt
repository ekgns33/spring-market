package org.ekgns33.springmarket.common

object CustomValidation {
    // 간단한 이메일 정규식 예시. 필요에 따라 수정할 수 있습니다.
    private val EMAIL_REGEX =
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$".toRegex()

    fun isEmail(email: String): Boolean = EMAIL_REGEX.matches(email)
}
