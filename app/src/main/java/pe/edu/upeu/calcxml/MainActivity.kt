package pe.edu.upeu.calcxml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado:EditText
    private var valAnt=0.0
    private var valActual=0.0
    private var operador=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado=findViewById(R.id.txtResult)
    }

    fun botones() {
        var buttons = arrayOf(
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnSumar, R.id.btnRestar, R.id.btnMulti, R.id.btnIgual
        )
        for (button in buttons){
            var btn = findViewById<Button>(button)
            btn.setOnClickListener { onClickListener(btn) }
        }
    }
    fun onClickListener(view:View){
        when(view.id){
            R.id.btn1, R.id.btn2, R.id.btn3 ,R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9->{
                var buttonX=findViewById<Button>(view.id)
                appendContent(buttonX.text.toString())
            }
        }
    }
    fun appendContent(valor:String){
        txtResultado.append(valor)
    }
    fun setOperador(oper:String){
        operador=oper
        valAnt=txtResultado.text.toString().toDouble()
        txtResultado.text.clear()
    }
    fun operacion(){
        valActual=txtResultado.text.toString().toDouble()
        var resultx=when(operador){
            "x"->valActual*valAnt
            "+"->valActual+valAnt
            "-"->valActual-valAnt
            else->valActual
        }
        txtResultado.setText(resultx.toString())
        operador=""
    }

}