package fr.raouf.verra

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paypal.android.sdk.payments.*
import org.json.JSONException
import org.json.JSONObject
import java.math.BigDecimal

class PaymentActivity : AppCompatActivity() {

    private lateinit var editAmount: EditText
    private lateinit var btnPayment: Button

    private val clientId = "Ab7Kd0jPZhsTj2qi0I8MJaAFgmjBY_vjTYbKYF06uwrtt3loCTTTWiTxtNlM1yhVrdSiMSsy03w0IBEQ"
    private val PAYPAL_REQUEST_CODE = 123
    companion object {
        lateinit var configuration: PayPalConfiguration
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        editAmount = findViewById(R.id.edtAmount)
        btnPayment = findViewById(R.id.btnPayment)

        configuration = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(clientId)

        btnPayment.setOnClickListener {
            getPayment()
        }
    }

    private fun getPayment() {
        val amounts = editAmount.text.toString()

        val payment = PayPalPayment(BigDecimal(amounts), "EUR", "Code with Arving", PayPalPayment.PAYMENT_INTENT_SALE)

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration)
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment)

        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PAYPAL_REQUEST_CODE) {
            val paymentConfirmation = data?.getParcelableExtra<PaymentConfirmation>(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION)
            if (paymentConfirmation != null) {
                try {
                    val paymentDetails = paymentConfirmation.toJSONObject().toString(4)
                    JSONObject(paymentDetails)
                } catch (e: JSONException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid Payment", Toast.LENGTH_SHORT).show()
        }
    }
}
