package ram.ewe.englishwithexperts.Repositories;

import javax.swing.*;

public class valorPorExtenso {
    private static final String[] UNIDADES = { "", "Um", "Dois", "Três", "Quatro", "Cinco", "Seis", "Sete", "Oito", "Nove" };
    private static final String[] DEZ_A_DEZENOVE = { "Dez", "Onze", "Doze", "Treze", "Quatorze", "Quinze", "Dezesseis", "Dezessete", "Dezoito", "Dezenove" };
    private static final String[] DEZENAS = { "", "", "Vinte", "Trinta", "Quarenta", "Cinquenta", "Sessenta", "Setenta", "Oitenta", "Noventa" };
    private static final String[] CENTENAS = { "", "Cento", "Duzentos", "Trezentos", "Quatrocentos", "Quinhentos", "Seiscentos", "Setecentos", "Oitocentos", "Novecentos" };
    private static final String[] MILHARES = {"", "Mil"};

    public static String setValor(int num)
    {
        if(num == 0) { return "Zero Metical"; }
        if(num == 1) { return "Um Metical"; }
        if(num == 1000) { return "Mil Meticais"; }
        if(num == 100000) { return "Cem Mil Meticais"; }
        if(num == 1000000) { return "Um Milhão Meticais"; }
        if(num > 1000000) { JOptionPane.showMessageDialog(null, "Atingiu o limite. Por favor, Contacte o administrador!"); }

        StringBuilder extenso = new StringBuilder();
        // Milhares
        if(num > 1000)
        {
            int mil = num / 1000;
            if(mil == 1) { extenso.append(MILHARES[1]); }
            else
            {
                if(mil == 100) { extenso.append(MILHARES[1]).append(" e ").append(setAux(mil)); }
                else { extenso.append(setAux(mil)).append(" Mil"); }
            }
            num %= 1000;
            if(num > 0) { extenso.append(" e "); }
        }

        // Centenas
        if(num >= 100)
        {
            int centena = num / 100;
            if(centena == 1 && num % 100 == 0)
            {
                if(num == 100) { extenso.append("Cem"); }
                else { extenso.append(CENTENAS[2]); }
            }
            else { extenso.append(CENTENAS[centena]); }
            num %= 100;

            if(num > 0) { extenso.append(" e "); }
        }

        // Dezenas e Unidades
        if(num >= 10 && num < 20) { extenso.append(DEZ_A_DEZENOVE[num - 10]); }
        else
        {
            if(num >= 20)
            {
                int dezena = num / 10;
                extenso.append(DEZENAS[dezena]);
                num %= 10;
                if(num > 0) { extenso.append(" e "); }
            }
            if(num > 0) { extenso.append(UNIDADES[num]); }
        }

        extenso.append(" Meticais");
        return extenso.toString();
    }

    public static String setAux(int num)
    {
        StringBuilder extenso = new StringBuilder();
        // Milhares
        if(num > 1000)
        {
            int mil = num / 1000;
            if(mil == 1) { extenso.append(MILHARES[1]); }
            else
            {
                if(mil == 100) { extenso.append(MILHARES[1]).append(" e ").append(setAux(mil)); }
                else { extenso.append(setAux(mil)).append(" Mil"); }
            }
            num %= 1000;
            if(num > 0) { extenso.append(" e "); }
        }

        // Centenas
        if(num >= 100)
        {
            int centena = num / 100;
            if(centena == 1 && num % 100 == 0)
            {
                if(num == 100) { extenso.append("Cem"); }
                else { extenso.append(CENTENAS[2]); }
            }
            else { extenso.append(CENTENAS[centena]); }
            num %= 100;

            if(num > 0) { extenso.append(" e "); }
        }

        // Dezenas e Unidades
        if(num >= 10 && num < 20) { extenso.append(DEZ_A_DEZENOVE[num - 10]); }
        else
        {
            if(num >= 20)
            {
                int dezena = num / 10;
                extenso.append(DEZENAS[dezena]);
                num %= 10;
                if(num > 0) { extenso.append(" e "); }
            }
            if(num > 0) { extenso.append(UNIDADES[num]); }
        }

        return extenso.toString();
    }
}
