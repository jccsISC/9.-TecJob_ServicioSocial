package com.jccsisc.tecjob_final;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionUsuario
{

    public static String carreraAlum="";

    public boolean validacionVacios(String str)
    {
        boolean estado = true;
        if (str.isEmpty()) {
            estado = false;
        }
        return estado;
    }

    public boolean validaionNombre(String Nombre)
    {
        boolean estado = true;
        if (Nombre.length() > 45) {
            estado = false;
        }
        return estado;
    }

    public boolean validacionUsuario(String Usuario)
    {
        boolean estado = true;
        if (Usuario.length() < 6 && Usuario.length() < 12)
        {
            estado = false;
        }
        return estado;
    }

    public boolean validacionTamañoPswIguales(String Psw, String conPsw)
    {
        boolean estado = true;
        if ((Psw.length() < 6 && Psw.length() < 8) || (conPsw.length() < 6 && conPsw.length() < 8))
        {
            estado = false;
        }
        return estado;
    }

    public boolean validarTamañoPsw(String password)
    {
        boolean estado = true;
        if(password.length()<8)
        {
            estado=false;
        }
        return estado;
    }

    public boolean validacionPswIguales(String Psw, String conPsw)
    {
        boolean estado = true;
        if(!Psw.equals(conPsw))
        {
            estado = false;
        }
        return estado;
    }

    public  boolean validacionCorreo(String correo)
    {
        boolean estado = true;
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(correo);
        if (!matcher.matches()) {
            estado = false;
        }
        return estado;
    }

    //Validar correo valido
    public Matcher validaCorreo(String email)
    {
        Pattern patternEmail = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        +"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherEmail = patternEmail.matcher(email);
        return  matcherEmail;
    }

    public void Limpieza(EditText editText) {
        editText.setText("");
    }

}
