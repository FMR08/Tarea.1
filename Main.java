mport java.util.ArrayList;
import java.util.Date;

class Cliente {
    private String nombre;
    private String rut;
    private String direcion;
    public Cliente(String Nom,String rut,String direcion) {
        nombre=Nom;
        this.rut=rut;
    }

    public String getRut() {
        return rut;
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
    private Date fecha = new Date();
    private int año;
    private int mes ;
    private int dia ;
    public DocTributario(String numero,OrdenCompra ordenCompra){
        this.rut=ordenCompra.cliente.getRut();
        this.numero=numero;
        año=fecha.getYear();
        mes=fecha.getMonth();
        dia= fecha.getDay();
    }
}
class Boleta extends DocTributario{
    public Boleta(String num, OrdenCompra ordenCompra) {
        super(num,ordenCompra);
    }
}
class Factura extends DocTributario{
    public Factura(String num, OrdenCompra ordenCompra) {
        super(num, ordenCompra);
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
    public Cliente cliente;
    private Date fecha;
    private String estado;
    private ArrayList<DetalleOrden> Orden;
    public OrdenCompra (Cliente cliente){
        this.cliente=cliente;
        Orden = new ArrayList();
    }

    public void AgregarCompra(DetalleOrden gg) {
        Orden.add(gg);
    }
    public float calcPrecio() {
        float suma=0;
        for (int i=0;i<Orden.size();i++) {
            suma += Orden.get(i).calcPrecio();
        }
        return suma;
    }
    public float calcPreciosinIVA() {
        float suma=0;
        for (int i=0;i<Orden.size();i++) {
            suma += Orden.get(i).calcPreciosinIVA();
        }
        return suma;
    }
    public float calcIVA() {
        float suma=0;
        for (int i=0;i<Orden.size();i++) {
            suma += Orden.get(i).calcIVA();
        }
        return suma;
    }

    public float calcPeso() {
        float suma=0;
        for (int i=0;i<Orden.size();i++) {
            suma += Orden.get(i).calcPeso();
        }
        return suma;
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
        Cliente raul = new Cliente("Raul","1234567","esta");
        Articulo uvas = new  Articulo(0.9F,"uvas verdes","uvas de provincia", 3.50F);
        Articulo sandia = new Articulo(8.70F,"sandia","sandia esilo resaurante ",12.0f);
        Articulo fideos =new Articulo(2.50f,"cabello de angel","finos fideos filetiado finamente fijado del fiel fiscal",6.25f);
        DetalleOrden producto1 =new DetalleOrden(1,uvas);
        DetalleOrden producto2 =new DetalleOrden(1,sandia);
        DetalleOrden producto3 =new DetalleOrden(1,fideos);
        OrdenCompra compra =new OrdenCompra(raul);
        compra.AgregarCompra(producto1);
        compra.AgregarCompra(producto2);
        compra.AgregarCompra(producto3);
        float p=compra.calcPrecio();
        System.out.println(p);
    }
}
