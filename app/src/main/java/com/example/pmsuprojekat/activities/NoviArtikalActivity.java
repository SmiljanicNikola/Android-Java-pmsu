package com.example.pmsuprojekat.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.pmsuprojekat.MainActivity;
import com.example.pmsuprojekat.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import model.Artikal;
import model.Prodavac;

public class NoviArtikalActivity extends AppCompatActivity {

    EditText txtNaziv, txtOpis, txtCena, txtPutanja;
    TextView textViewDodaj, textViewPregledaj;
    DBHelper DB;
    ImageView imageViewSlika;

    final int REQEUST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artikl_forma);

        txtNaziv = findViewById(R.id.txtNaziv);
        txtOpis = findViewById(R.id.txtOpis);
        txtCena = findViewById(R.id.txtCena);
        txtPutanja = findViewById(R.id.txtPutanja);
        imageViewSlika = findViewById(R.id.imageView);

        imageViewSlika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions(
                    NoviArtikalActivity.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQEUST_CODE_GALLERY
                );
            }
    });


        textViewDodaj = findViewById(R.id.textViewDodaj);
        textViewPregledaj = findViewById(R.id.textViewPregledaj);
        /*Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);*/
        Intent intent = getIntent();
       // String username = intent.getStringExtra("user");
        //String prodavacId = String.valueOf(intent.getIntExtra("idProdavca",0));
        Integer prodavacid = intent.getIntExtra("id",0);

        textViewDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naziv = txtNaziv.getText().toString();
                String opis = txtOpis.getText().toString();
                Double cena = Double.valueOf(txtCena.getText().toString());
                String putanja = txtPutanja.getText().toString();
                Integer prodavac_id = prodavacid;
                byte[] image = imageViewToByte(imageViewSlika);


                if (naziv.equals("") || opis.equals("") || cena.equals("") || putanja.equals("")) {
                    Toast.makeText(NoviArtikalActivity.this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper DB = new DBHelper(NoviArtikalActivity.this);
                    Artikal artikal = new Artikal(naziv,opis,cena,putanja,prodavac_id,image);
                    DB.insertArtikal(artikal);
                    Toast.makeText(NoviArtikalActivity.this, "Uspesno ste dodali artikal", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        textViewPregledaj.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(NoviArtikalActivity.this, ArtikalActivity.class);
            startActivity(intent);
                /*Intent intent = new Intent(NoviArtikalActivity.this, ArtikalActivity.class);
                Intent intent1 = getIntent();
                String username = intent1.getStringExtra("user");
                //Korisnik korisnik = DB.findKorisnik(username);
                Prodavac prodavac = DB.findProdavac(username);
                int idProdavca = prodavac.getId();
                intent.putExtra("idProdavca", idProdavca);
                intent.putExtra("user", username);
                startActivity(intent);*/
            }
        });


    }

    public static byte[] imageViewToByte(ImageView imageViewSlika) {
        Bitmap bitmap = ((BitmapDrawable)imageViewSlika.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQEUST_CODE_GALLERY) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQEUST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQEUST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)//Image will be square
                    .start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //Set image choosed from gallery to image view
                imageViewSlika.setImageURI(resultUri);
            }
            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}