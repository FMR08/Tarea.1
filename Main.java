import java.util.Date;

class Cliente {
    private String nombre;
    private String rut;
    public Cliente(String Nom,String rut) {
        nombre=Nom;
        this.rut=rut;
    }
}
class Direcion {
    private String direccion;
    public Direcion(String direc) {
        direccion = direc;
    }
}
class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;

    public Articulo(float peso,String nombre, String desc,float precio){
        this.peso=peso;
        this.nombre=nombre;
        descripcion=desc;
        this.precio=precio;
    }

    public float getPrecio() {
        return precio;
    }

    public float getPeso() {
        return peso;
    }
}
class DetalleOrden {
    private int cantidad;
    private Articulo articulo;

    public DetalleOrden(int cantidad, Articulo art) {
        this.cantidad = cantidad;
        articulo = art;
    }

    public float calcPrecio() {
        return articulo.getPrecio() * cantidad;
    }

    public float calcPreciosinIVA() {
        return calcPrecio() - calcIVA();
    }

    public float calcIVA() {
        return cantidad * articulo.getPrecio() / 10;
    }

    public float calcPeso() {
        return articulo.getPeso() * cantidad;
    }
}
class Pago {
    private float monto;
    private Date fecha;
}
class Efectivo extends Pago {
    public float calcDevolucion
}
class Transferencia extends Pago {
}
class Tarjeta extends Pago {
}
public class Main {


}