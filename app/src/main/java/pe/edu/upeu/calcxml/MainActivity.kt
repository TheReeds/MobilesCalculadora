package pe.edu.upeu.calcxml
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado: EditText
    private var valAnt = 0.0
    private var valActual = 0.0
    private var operador = ""
    private var isOperatorPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado = findViewById(R.id.txtResult)
        botones()
    }

    fun botones() {
        val buttons = arrayOf(
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnSumar, R.id.btnRestar, R.id.btnMulti, R.id.btnIgual,
            R.id.btnDivision, R.id.btnRaiz, R.id.btnPotencia, R.id.btnClear, R.id.btnPi
        )
        for (button in buttons) {
            val btn = findViewById<Button>(button)
            btn.setOnClickListener { onClickListener(btn) }
        }
    }

    fun onClickListener(view: View) {
        val buttonX = findViewById<Button>(view.id)
        when (view.id) {
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 -> {
                appendContent(buttonX.text.toString())
            }
            R.id.btnSumar, R.id.btnRestar, R.id.btnMulti, R.id.btnDivision, R.id.btnPotencia -> {
                setOperador(buttonX.text.toString())
            }
            R.id.btnRaiz -> {
                calculateSquareRoot()
            }
            R.id.btnPi -> {
                appendContent(Math.PI.toString())
            }
            R.id.btnIgual -> {
                operacion()
            }
            R.id.btnClear -> {
                clearAll()
            }
        }
    }

    fun appendContent(valor: String) {
        if (isOperatorPressed) {
            txtResultado.text.clear()
            isOperatorPressed = false
        }
        txtResultado.append(valor)
    }

    fun setOperador(oper: String) {
        if (!isOperatorPressed) {
            operador = oper
            valAnt = txtResultado.text.toString().toDouble()
            isOperatorPressed = true
        } else {
            operador = oper
        }
    }

    fun operacion() {
        valActual = txtResultado.text.toString().toDouble()
        val resultx = when (operador) {
            "x" -> valActual * valAnt
            "+" -> valActual + valAnt
            "-" -> valAnt - valActual
            "/" -> if (valActual != 0.0) valAnt / valActual else 0.0
            "^" -> valAnt.pow(valActual)
            else -> valActual
        }
        txtResultado.setText(resultx.toString())
        operador = ""
        isOperatorPressed = false
    }

    fun calculateSquareRoot() {
        val number = txtResultado.text.toString().toDouble()
        val result = sqrt(number)
        txtResultado.setText(result.toString())
    }

    fun clearAll() {
        txtResultado.text.clear()
        valAnt = 0.0
        valActual = 0.0
        operador = ""
        isOperatorPressed = false
    }
}