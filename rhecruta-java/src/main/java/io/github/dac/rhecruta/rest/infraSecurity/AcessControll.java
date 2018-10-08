package io.github.dac.rhecruta.rest.infraSecurity;

import io.github.dac.rhecruta.rest.infraSecurity.model.NivelAcesso;

public class AcessControll {

    public static NivelAcesso buscarNivelPermissao(String email) {

        if (email.contains("@adm.br"))
            return NivelAcesso.NIVEL_1;
        else
            return NivelAcesso.NIVEL_2;

    }
}
