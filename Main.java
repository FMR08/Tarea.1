import java.util.ArrayList;
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
class DocTributario{
    private String numero;
    private String rut;
    private Date fecha;
    public DocTributario(String num, String rut){
        this.rut=rut;
        numero=num;
    }
}
class Boleta extends DocTributario{
    public Boleta(String num, String rut) {
        super(num, rut);
    }
}
class Factura extends DocTributario{
    public Factura(String num, String rut) {
        super(num, rut);
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
        return calcPreciosinIVA() +calcIVA();
    }

    public float calcPreciosinIVA() {
        return articulo.getPrecio()* cantidad;
    }

    public float calcIVA() {
        return calcPreciosinIVA() / 10;
    }

    public float calcPeso() {
        return articulo.getPeso() * cantidad;
    }
}
class OrdenCompra{
    private Date fecha;
    private String estado;
    ArrayList<DetalleOrden> Orden= new ArrayList<DetalleOrden>();
    public OrdenCompra (String estado){
        this.estado=estado;
    }
    public void AgregarCompra(DetalleOrden gg) {
        Orden.add(gg);
    }
    public float calcPrecio() {
    return 0;
    }
    public float calcPreciosinIVA() {
        return 0;
    }
    public float calcIVA() {
        return 0;
    }
    public float calcPeso() {
        
        return 0;
    }
}
class Pago {
    private float monto;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }
}
class Efectivo extends Pago {
    public float calcDevolucion;
}
class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(String ban, String num){
        banco=ban;
        numCuenta=num;
    }
}
class Tarjeta extends Pago {
}
public class Main {
    public static void main(String[] args) {
        Pago pago=new Pago();
        Date fecha=new Date(5,5,5);
        System.out.println(pago.getFecha());

    }
}
