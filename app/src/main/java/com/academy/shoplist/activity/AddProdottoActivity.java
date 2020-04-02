package com.academy.shoplist.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.academy.shoplist.bean.ImmagineProdotto;
import com.academy.shoplist.data.ShoplistDatabaseManager;
import com.academy.shoplist.intentConstant.Constant;
import com.academy.shoplist.util.ControlloInput;
import com.academy.shoplist.util.DbBitMapUtility;
import com.academy.shoplist.util.Uuid;
import com.jherome.linx.shoplist.R;
import com.academy.shoplist.bean.Prodotto;

import java.util.HashMap;
import java.util.Map;

public class AddProdottoActivity extends AppCompatActivity {
    Map<TextView,String> controlValue = new HashMap<TextView,String>();

    EditText nameView;
    EditText descriptionView;
    ImageView image;
    static int PReqCode =1;
    static int REQUESTCODE = 1;
    Uri pickedUri;
    Bitmap bitmap;
    byte byt[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prodotto);


        Boolean esitoControllo;

        nameView =  (EditText) findViewById(R.id.texteditName);
        descriptionView =  (EditText) findViewById(R.id.texteditdescrizione);


        image = findViewById(R.id.image_add);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 24) {
                    checkAndRequestForPermission();
                }
                else {
                    openGallery();
                }
            }

            private void openGallery() {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, REQUESTCODE);
            }

            private void checkAndRequestForPermission() {
                if (ContextCompat.checkSelfPermission(AddProdottoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(AddProdottoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText( AddProdottoActivity.this, "ACCETTA MERDACCIA!!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ActivityCompat.requestPermissions(AddProdottoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                               PReqCode );
                    }
                }
                else openGallery();
            }
        });

        Button conferma_button = (Button) findViewById(R.id.conferma);

        conferma_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ControlloInput.validaInput(nameView,descriptionView).containsKey(false))
                {

                    Toast.makeText(AddProdottoActivity.this,ControlloInput.validaInput(nameView,descriptionView).get(false),Toast.LENGTH_LONG).show();

                }else if(ControlloInput.validaInput(nameView,descriptionView).containsKey(true)) {
                    Uuid uuidProdotto = new Uuid();
                    String idProdotto = uuidProdotto.generaUUID();
                    Uuid uuidImmagine = new Uuid();
                    String idImmagine = uuidImmagine.generaUUID();

                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pickedUri);
                         byt = DbBitMapUtility.getBytes(bitmap);

                    } catch (Exception exception){

                    }
                    ShoplistDatabaseManager.getInstance(AddProdottoActivity.this).addImmagineProdotto(new ImmagineProdotto(idImmagine, byt));
                    ShoplistDatabaseManager.getInstance(AddProdottoActivity.this).addProdotto(new Prodotto(idProdotto,idImmagine,nameView.getText().toString(), descriptionView.getText().toString()));


                    Intent intent = new Intent();
                    setResult(Constant.REQUESTCODE, intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null) {
            pickedUri = data.getData();
            image.setImageURI(pickedUri);
        }
    }

}
