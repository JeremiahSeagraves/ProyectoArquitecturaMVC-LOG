package controlador;

import vista.Evento;

public abstract class Controlador {

    private Evento evt;

    public void setEvento(Evento evt) {
        this.evt = evt;
    }
    
    public Evento getEvt(){
        return this.evt;
    }
}
