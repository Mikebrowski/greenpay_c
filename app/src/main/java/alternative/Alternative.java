package alternative;

import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class Alternative {



        //ALTERNATIVE USING TOAST and everything in one BOOLEAN EITHER IT IS ALL correct or nothing is
                private boolean validateEmailAdresse(EditText txtEmailAddress, EditText txtPassword) {
                    String emailString = txtEmailAddress.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
                    String passwordCheck = txtPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";

                    if (!emailString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailString).matches() && !passwordCheck.isEmpty()) {
                        //Toast.makeText(RegisterActivityScreen.this, "Brukeren laget " + txtEmailAddress.getText().toString(), Toast.LENGTH_LONG).show();
                        return true;
                    } else {
                        ///Toast.makeText(RegisterActivityScreen.this, "Ugyldig epost eller passord", Toast.LENGTH_LONG).show();
                        return false;
                    }//end else

                } //end validateEmailAdresse

            }//end of create user


