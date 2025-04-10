package alcala.jose.practica10

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    object Global{
        var preferencias_compartidas="sharedpreferences"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun verificar_sesion_abierta(){
        var sesion_abierta:SharedPreferences=this.getSharedPreferences(Global.preferencias_compartidas,
            Context.MODE_PRIVATE)
        var correo=sesion_abierta.getString("Correo",null)
        var proveedor=sesion_abierta.getString("Proveedor",null)
        if(correo!=null && proveedor!=null){
            var intent= Intent(applicationContext,Bienvenida::class.java)
            intent.putExtra("Correo",correo)
            intent.putExtra("Proveedor",proveedor)
            startActivity(intent)
        }
    }

    fun guardar_sesion(correo:String,proveedor:String){
        var guardar_sesion: SharedPreferences.Editor=this.getSharedPreferences(Global.preferencias_compartidas,Context.MODE_PRIVATE).edit()
        guardar_sesion.putString("Correo",correo)
        guardar_sesion.putString("Proveedor",proveedor)
        guardar_sesion.apply()
        guardar_sesion.commit()
    }
    @Composable
    fun loginGoogle(){
        val context= LocalContext.current
        val clientId = context.getString(R.string.web_client)
        val coroutineScope: CoroutineScope = rememberCoroutineScope()
        val credentialManager= CredentailManager().createCredentialManager(context)

        val signInWithGoogleOption: GetSignInWithGoogleOption =
            GetSignInWithGoogleOption.Builder(getString(R.string.web_client))
                .setNonce("nonce")
                .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(signInWithGoogleOption)
            .build()

        coroutineScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context
                )
                handleSignIn(result)
            }catch (e: GetCredentialException){
                Toast.makeText(
                    aplicationContext,
                    "Error al obtener la credencial "+e,
                    Toast.LENGTH_LONG

                ).show()
            }

        }
    }
}
/*class CredentialManager(){
    fun createCredentialManager(context: Context):CredentialManager{
        return CredentialManager.create(context)
    }
}*/