package net.fpl.phuongttph18428_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;



public class PH18428_IntroActivity extends AppCompatActivity {
TextView txtTile;
ImageView img_intro;
Animation animationText,animationImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ph18428_activity_intro);
        txtTile=findViewById(R.id.txt_Tiltintro);
        img_intro=findViewById(R.id.img_intro);
        animationText= AnimationUtils.loadAnimation(this,R.anim.ph18428_text_intro_animation);
        txtTile.setAnimation(animationText);
        animationImage=AnimationUtils.loadAnimation(this,R.anim.image_intro_animation);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              startActivity(new Intent(PH18428_IntroActivity.this, PH18428_LoginActivity.class));
              finish();
          }
      },3000);
    }
}
