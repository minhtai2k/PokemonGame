package com.example.pokemongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Drawable.ConstantState storedState = null;
    ImageView storedView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            View.OnClickListener imageClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = (ImageView) v;

                Drawable.ConstantState storedImgId;

                if(storedView == null){
                    storedView = imageView;
                    ((ImageView) v).getDrawable().setAlpha(127);

                }else{
                    Drawable.ConstantState imgId = imageView.getDrawable().getConstantState();
                    storedImgId = storedView.getDrawable().getConstantState();
                    if((storedImgId == imgId) && (storedView.getId() != imageView.getId())){

                        imageView.setVisibility(View.GONE);
                        storedView.setVisibility(View.GONE);

                        boolean allGone = true;
                        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
                        for (int i = 0; i< constraintLayout.getChildCount(); i++){
                            View subView = constraintLayout.getChildAt(i);

                            if(subView instanceof ImageView){
                                ImageView subImage = (ImageView) subView;
                                if(subView.getVisibility() != View.GONE){
                                    allGone = false;
                                }
                            }
                        }
                        if(allGone)
                        {
                            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ((ImageView) storedView).getDrawable().setAlpha(255);
                    storedView = null;
                }

            }
        };


        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        for (int i = 0; i< constraintLayout.getChildCount(); i++){
            View subView = constraintLayout.getChildAt(i);
            if(subView instanceof ImageView){
                ImageView imageView = (ImageView) subView;
                imageView.setOnClickListener(imageClick);
            }
        }
    }
}