package MVC.controlador;

import MVC.vista.Evento;

public abstract class Controlador {

    private Evento evt;

    public void setEvento(Evento evt) {
        this.evt = evt;
    }
    
    public Evento getEvento(){
        return this.evt;
    }
}
