package br.util;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static int calcularIdade(Date dataNascimento) {
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.setTime(dataNascimento);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);

        if (hoje.get(Calendar.DAY_OF_YEAR) < dataNasc.get(Calendar.DAY_OF_YEAR)) {
            idade--;
        }

        return idade;
    }
}
