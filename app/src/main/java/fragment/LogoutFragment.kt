package fragment

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.greenpayremastered.R
import com.google.firebase.auth.FirebaseAuth


class Logout : Fragment() {

    private var logoutKnappen: Button? = null
    private var imageAnimationCircle: ImageView? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_logout, container, false)
        logoutKnappen = view.findViewById<Button>(R.id.logoutKnapp)
        imageAnimationCircle= view.findViewById<ImageView>(R.id.imageRotation)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val rotateAnimation = RotateAnimation(0F, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.duration = 500
        rotateAnimation.repeatCount = Animation.INFINITE
        imageAnimationCircle?.startAnimation(rotateAnimation)

        logoutKnappen?.setOnClickListener()
        {
            logoutKnappen!!.text = "test"
            //logoutFun()
        }
    }

    fun rotation360degrees() {

        val rotateAnimation = RotateAnimation(0F, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.interpolator = LinearInterpolator()
        rotateAnimation.duration = 500
        rotateAnimation.repeatCount = Animation.INFINITE
    }
    fun logoutFun()
    {
        mAuth?.signOut()

        /*
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    startActivity(new Intent(LoginActivityScreen.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            });
        */

        //startActivity(new Intent(LoginActivityScreen.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        //finish();
    }

}