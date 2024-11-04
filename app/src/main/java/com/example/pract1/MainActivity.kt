package com.example.pract1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var selectPais: String
    private lateinit var nomEditText: EditText
    private lateinit var apellEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var generoRadioGroup: RadioGroup
    private lateinit var ListViewPaises: ListView
    private lateinit var leerCheckBox: CheckBox
    private lateinit var deportCheckBox: CheckBox
    private lateinit var musicCheckBox: CheckBox
    private lateinit var artCheckBox: CheckBox
    private lateinit var cocinaCheckBox: CheckBox
    private lateinit var viajarCheckBox: CheckBox
    private lateinit var calificSeekBar: SeekBar
    private lateinit var calificTextView: TextView
    private lateinit var subSwitch: Switch
    private lateinit var enviarButton: Button
    private lateinit var resumTextView: TextView
    private lateinit var resum: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        nomEditText = findViewById(R.id.etNom)
        apellEditText = findViewById(R.id.etApellido)
        emailEditText = findViewById(R.id.etEmail)
        generoRadioGroup = findViewById(R.id.rgGenero)
        ListViewPaises = findViewById(R.id.paisesListView)
        leerCheckBox = findViewById(R.id.cBHobbie1)
        deportCheckBox = findViewById(R.id.cBHobbie2)
        musicCheckBox = findViewById(R.id.cBHobbie3)
        artCheckBox = findViewById(R.id.cBHobbie4)
        cocinaCheckBox = findViewById(R.id.cBHobbie5)
        viajarCheckBox = findViewById(R.id.cBHobbie6)
        calificSeekBar = findViewById(R.id.opseekBar)
        calificTextView = findViewById(R.id.tvop)
        subSwitch = findViewById(R.id.switchsub)
        enviarButton = findViewById(R.id.buttonEnviar)
        resumTextView = findViewById(R.id.tvresum)


        val paises = arrayOf("Afganistán", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Arabia Saudita", "Argelia",
            "Argentina", "Armenia", "Australia", "Austria", "Azerbaiyán", "Bahamas", "Bangladés", "Barbados", "Baréin",
            "Bélgica", "Belice", "Benín", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina", "Botsuana",
            "Brasil", "Brunéi", "Bulgaria", "Burkina Faso", "Burundi", "Bután", "Cabo Verde", "Camboya", "Camerún",
            "Canadá", "Catar", "Chad", "Chile", "China", "Chipre", "Colombia", "Comoras", "Corea del Norte",
            "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia", "Cuba", "Dinamarca", "Dominica", "Ecuador",
            "Egipto", "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "España",
            "Estados Unidos", "Estonia", "Esuatini", "Etiopía", "Filipinas", "Finlandia", "Fiyi", "Francia",
            "Gabón", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Guatemala", "Guinea", "Guinea-Bisáu",
            "Guinea Ecuatorial", "Guyana", "Haití", "Honduras", "Hungría", "India", "Indonesia", "Irak", "Irán",
            "Irlanda", "Islandia", "Islas Marshall", "Islas Salomón", "Israel", "Italia", "Jamaica", "Japón",
            "Jordania", "Kazajistán", "Kenia", "Kirguistán", "Kiribati", "Kuwait", "Laos", "Lesoto", "Letonia",
            "Líbano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Madagascar", "Malasia",
            "Malaui", "Maldivas", "Malí", "Malta", "Marruecos", "Mauricio", "Mauritania", "México", "Micronesia",
            "Moldavia", "Mónaco", "Mongolia", "Montenegro", "Mozambique", "Namibia", "Nauru", "Nepal", "Nicaragua",
            "Níger", "Nigeria", "Noruega", "Nueva Zelanda", "Omán", "Países Bajos", "Pakistán", "Palaos", "Panamá",
            "Papúa Nueva Guinea", "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido", "República Centroafricana",
            "República Checa", "República del Congo", "República Democrática del Congo", "República Dominicana",
            "Ruanda", "Rumania", "Rusia", "Samoa", "San Cristóbal y Nieves", "San Marino", "San Vicente y las Granadinas",
            "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles", "Sierra Leona", "Singapur",
            "Siria", "Somalia", "Sri Lanka", "Suazilandia", "Sudáfrica", "Sudán", "Sudán del Sur", "Suecia",
            "Suiza", "Surinam", "Tailandia", "Tanzania", "Tayikistán", "Timor Oriental", "Togo", "Tonga",
            "Trinidad y Tobago", "Túnez", "Turkmenistán", "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay",
            "Uzbekistán", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yibuti", "Zambia", "Zimbabue"
        )
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, paises)
        val adapter = ArrayAdapter(this, R.layout.fila, paises)
        ListViewPaises.adapter = adapter

        selectPais = ""
        ListViewPaises.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                selectPais = paises[position]
            }

        nomEditText.requestFocus()


        calificSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Actualiza el TextView mientras se mueve la SeekBar
                calificTextView.text = "Nivel de satisfacción: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementación
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementación
            }
        })

        enviarButton.setOnClickListener {

            Enviar()
            resum = resumTextView.text.toString()
            }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("RESUM_KEY", resum)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("MyTag", "onRestoreInstanceState")

        resumTextView.setText(savedInstanceState.getString("RESUM_KEY"))
    }

    @SuppressLint("SetTextI18n")
    private fun Enviar() {
        Log.d("button", "Se ha hecho click")
        var nombre = nomEditText.text.toString()
        var apellidos = apellEditText.text.toString()
        var email = emailEditText.text.toString()

        var genero = ""
        var selectedRadioButtonId = generoRadioGroup.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {

            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            genero = selectedRadioButton.text.toString()
        }

        var hobbies = "hobbies:\n"
        if (leerCheckBox.isChecked == true)
            hobbies+="Leer\n"
        if (deportCheckBox.isChecked == true)
            hobbies+="Deporte\n"
        if (musicCheckBox.isChecked == true)
            hobbies+="Música\n"
        if (artCheckBox.isChecked == true)
            hobbies+="Arte\n"
        if (cocinaCheckBox.isChecked == true)
            hobbies+="Cocina\n"
        if (viajarCheckBox.isChecked == true)
            hobbies+="Viajar\n"

        var suscrito = ""
        if (subSwitch.isChecked) {
            suscrito = "Has decidido suscribirte a el boletín"
        }else {
            suscrito = "Has decidido no suscribirte a el boletín"
        }

        var calificacion = "Nivel de satisfacción:" + calificSeekBar.progress

        var pais = selectPais
        if (pais!=""){
            resumTextView.setText("Datos Enviados\n $nombre $apellidos\n $email\nGenero: $genero\n $hobbies\n" +
                    "País: $pais\n $suscrito\n $calificacion")
        }else{
            Toast.makeText(this, "Introduce un país", Toast.LENGTH_SHORT).show()
            resumTextView.setText("Datos Enviados")
        }

    }
}