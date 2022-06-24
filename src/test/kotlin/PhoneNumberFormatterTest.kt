import junit.framework.TestCase.assertEquals
import org.junit.Test

class PhoneNumberFormatterTest {

    private val phoneNumberFormatter = PhoneNumberFormatter()

    @Test
    fun testSimpleFormatting() {
        assertEquals("8 (565) 678-55-88", phoneNumberFormatter.format("85656785588"))
        assertEquals("8 (888) 888-88-88", phoneNumberFormatter.format("8(888)888-88-88"))
        assertEquals(
            "8 (888) 888-88-88",
            phoneNumberFormatter.format("8     (  8  8  8  )     8   8   8 - 8   8 - 8  8")
        )
        assertEquals("8 (888) 888-88-88", phoneNumberFormatter.format("8(888)8-8-8-8-8-8-8"))
    }

    @Test
    fun testSimpleFormattingError() {
        assertEquals("error", phoneNumberFormatter.format("+7 (99b9) 555asdfb-66-77"))

        assertEquals("error", phoneNumberFormatter.format("+85656785588"))
        assertEquals("error", phoneNumberFormatter.format("+756567855881"))

        assertEquals("error", phoneNumberFormatter.format("8 (989 000-55-66"))
        assertEquals("error", phoneNumberFormatter.format("8 (9995556677"))

        assertEquals("error", phoneNumberFormatter.format("8(888)-8888888"))

        assertEquals("error", phoneNumberFormatter.format("8 -(999) - 555-66-77"))
        assertEquals("error", phoneNumberFormatter.format("8-(999)555-66-77"))
        assertEquals("error", phoneNumberFormatter.format("8(999)-555 6677"))
        assertEquals("error", phoneNumberFormatter.format("8 (99-9) 555-66-77"))
        assertEquals("error", phoneNumberFormatter.format("8 (999) 5-55--66-77"))
        assertEquals("error", phoneNumberFormatter.format("8 (999) 5-55-66-77-"))
        assertEquals("error", phoneNumberFormatter.format("8 (999) 555-----66-77"))

        assertEquals("error", phoneNumberFormatter.format("8 )989) 000-55-66"))
        assertEquals("error", phoneNumberFormatter.format("8 )989) 000-55-66"))
        assertEquals("error", phoneNumberFormatter.format("8 )989) 000-55-66"))
    }

    @Test
    fun testSimpleFormattingWithSeven() {
        assertEquals("8 (956) 576-55-66", phoneNumberFormatter.format("+79565765566"))
        assertEquals("8 (956) 576-55-66", phoneNumberFormatter.format("79565765566"))
        assertEquals("8 (999) 555-66-77", phoneNumberFormatter.format("+7 (999) 555-66-77"))
    }

    @Test
    fun testAlreadyFormatted() {
        assertEquals("8 (999) 555-66-77", phoneNumberFormatter.format("8 (999) 555-66-77"))
        assertEquals("8 (888) 888-88-88", phoneNumberFormatter.format("8(888)888-88-88"))
    }
}