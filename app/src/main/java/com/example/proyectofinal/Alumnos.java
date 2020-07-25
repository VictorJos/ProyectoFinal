package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Alumnos extends AppCompatActivity {
    EditText ed0,ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    ImageView img;
    Button btnfoto;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);
        img = findViewById(R.id.imageView);
        ed0= findViewById(R.id.txtCodigo1);
        ed1= findViewById(R.id.txtNombre);
        ed2= findViewById(R.id.txtApellido);
        ed3= findViewById(R.id.txtCedula);
        //ed4= findViewById(R.id.txtCorreo);
        //ed5= findViewById(R.id.txtTelefono);
        //ed6= findViewById(R.id.txtNombrer);
        //ed7= findViewById(R.id.txtDireccion);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(imageBitmap);
        }
    }
    public void llenar(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void RegistrarAlumno(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase db= admin.getWritableDatabase();
        String codigo= ed0.getText().toString();
        String nombre= ed1.getText().toString();
        String apellido= ed2.getText().toString();
        String telefono= ed3.getText().toString();
        //String correo=ed4.getText().toString();
        //String telefono=ed5.getText().toString();
        //String nombrer=ed6.getText().toString();
        //String direccion=ed7.getText().toString();
        if(!codigo.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("telefono",telefono);
            //registro.put("correo",correo);
            //registro.put("telefono",telefono);
            //registro.put("nombrer",nombrer);
            //registro.put("direccion",direccion);
            db.insert("alumnos", null, registro);
            db.close();
            ed0.setText("");
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            //ed4.setText("");
            //ed5.setText("");
            //ed6.setText("");
            //ed7.setText("");
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debe Lllenar Todos Los Campos", Toast.LENGTH_SHORT).show();
        }

    }
    public void  BuscarAlumnos(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase db= admin.getWritableDatabase();
        String codigo= ed0.getText().toString();
        if(!codigo.isEmpty() ){
            Cursor fila=db.rawQuery
                    ("select nombre, apellido, telefono from alumnos where codigo="+ codigo, null);
            if(fila.moveToFirst()){

                ed1.setText(fila.getString(0));
                ed2.setText(fila.getString(1));
                ed3.setText(fila.getString(2));


                ;
                db.close();

            }
            else{
                Toast.makeText(this, "No exixte el Alumno", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Introdusca la cedula correcta", Toast.LENGTH_SHORT).show();
        }

    }
    public void Menu(View v){
        Intent intent= new Intent(Alumnos.this, Menu.class);
        Toast.makeText(getApplicationContext(),"Bienvenido al Menu",Toast.LENGTH_LONG).show();
        startActivity(intent);

    }
    public void ModificarAlumnos(View v){

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper
                (this,"administracion",null,1);
        SQLiteDatabase db= admin.getWritableDatabase();
        String codigo= ed0.getText().toString();
        String nombre= ed1.getText().toString();
        String apellido= ed2.getText().toString();
        String telefono= ed3.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !telefono.isEmpty())
        {
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("telefono",telefono);


            int cantidad = db.update("alumnos",registro,"codigo="+codigo,null);
            db.close();

            if(cantidad==1)
            {
                Toast.makeText(this,"Registro Modificado Exitosamente", Toast.LENGTH_SHORT).show();
                ed0.setText("");
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                //ed4.setText("");
                //ed5.setText("");
                //ed6.setText("");
                //ed7.setText("");

            }else{
                Toast.makeText(this,"Registro no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
    public void EliminarAlumnos(View v){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper
                (this,"administracion",null,1);
        SQLiteDatabase db= admin.getWritableDatabase();
        String codigo=ed0.getText().toString();
        if(!codigo.isEmpty())
        {
            int cantidad = db.delete("alumnos", "codigo="+codigo,null);
            db.close();
            ed0.setText("");
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            //ed4.setText("");
            //ed5.setText("");
            //ed6.setText("");
            //ed7.setText("");

            if(cantidad==1)
            {
                Toast.makeText(this,"Alumno eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"El Alumno no existe", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this,"Debe introducir la cedula del alumno", Toast.LENGTH_SHORT).show();
        }
    }
}
