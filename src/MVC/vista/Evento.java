package vista;


public class Evento{
    private String clase;
    private String operacion;
    private Object object;
    
    
    public Evento(String clase, String operacion, Object object){
        this.clase = clase;
        this.operacion = operacion;
        this.object = object;
    }

    public String getClase() {
        return clase;
    }

    public String getOperacion() {
        return operacion;
    }

    public Object getObject() {
        return object;
    }
    
}
