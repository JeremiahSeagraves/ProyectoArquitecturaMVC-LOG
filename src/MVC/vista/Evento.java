package MVC.vista;


public class Evento{
    private String operacion;
    private Object object;
    
    
    public Evento(String operacion, Object object){
        this.operacion = operacion;
        this.object = object;
    }

    public String getOperacion() {
        return operacion;
    }

    public Object getObject() {
        return object;
    }
    
}
