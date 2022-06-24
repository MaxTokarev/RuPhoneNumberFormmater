class PhoneNumberFormatter {
    fun format(initialNumber: String): String {
        if (!initialNumber.verifyInitialFormatNumber()) return ERROR_MESSAGE

        val phoneNumber = initialNumber.getPhoneNumberWithoutSupportChars()

        val result = StringBuilder().apply {
            append("8 ")
            append("(${phoneNumber.substring(1..3)}) ")
            append("${phoneNumber.substring(4..6)}-")
            append("${phoneNumber.substring(7..8)}-")
            append(phoneNumber.substring(9..10))
        }

        return result.toString()
    }

    private fun String.verifyInitialFormatNumber(): Boolean =
        isLengthValid() && isFirstDigitValid() && isCodeOperatorValid() && isHyphenLocationValid()

    private fun String.isLengthValid(): Boolean =
        getPhoneNumberWithoutSupportChars().length == PHONE_NUMBER_LENGTH

    private fun String.isFirstDigitValid(): Boolean = isFirstDigitSeven() || first() == '8'
    private fun String.isFirstDigitSeven(): Boolean = isFirstDigitSevenWithPlus() || first() == '7'
    private fun String.isFirstDigitSevenWithPlus(): Boolean = first() == '+' && get(1) == '7'

    private fun String.isCodeOperatorValid(): Boolean {
        val neededNumber = removeSpaces().run {
            if (isFirstDigitSevenWithPlus()) removeRange(0..1) else removeRange(0..0)
        }
        return neededNumber.substring(0..2).all { it.isDigit() } ||
            neededNumber.first() == '(' &&
            neededNumber.substring(1..3).all { it.isDigit() } &&
            neededNumber[4] == ')' && neededNumber[5].isDigit()
    }

    private fun String.isHyphenLocationValid(): Boolean = last() != '-' &&
        filterIndexed { index, char -> char == '-' && getOrNull(index + 1) == '-' }.isEmpty()

    private fun String.getPhoneNumberWithoutSupportChars(): String = removeSpaces()
        .replace("(", "")
        .replace("+", "")
        .replace(")", "")
        .replace("-", "")

    private fun String.removeSpaces(): String = replace(" ", "")

    private companion object {
        const val ERROR_MESSAGE = "error"
        const val PHONE_NUMBER_LENGTH = 11
    }
}