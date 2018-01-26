package com.developer.nathan.jumper.engine;

import com.developer.nathan.jumper.elements.Canos;
import com.developer.nathan.jumper.elements.Passaro;

/**
 * Created by natha on 25/12/2017.
 */

class VerificadorDeColisao {
    private final Canos canos;
    private final Passaro passaro;

    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao() {
        return canos.temColisao(passaro);
    }
}
