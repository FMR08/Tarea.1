import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

class Cliente {
    private String nombre;
    private String rut;
    private String direccion;
    public Cliente(String Nom,String rut,String direccion) {
        nombre=Nom;
        this.rut=rut;
        this.direccion=direccion;
    }
    public String getRut() {
        return rut;
    }
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}
class DocTributario{
    private String numero;
    private String rut;
    private Date fecha;
    public DocTributario(OrdenCompra compra){
        this.rut=compra.cliente.getRut();
        numero=compra.cliente.getDireccion();
        Calendar cal = GregorianCalendar.getInstance();
        fecha=cal.getTime();
    }

    @Override
    public String toString() {
        return "direcion: "+numero+". rut: "+rut+". fecha:"+fecha;
    }
}
class Boleta extends DocTributario{
    public Boleta(OrdenCompra compra) {
        super(compra);
    }
}
class Factura extends DocTributario{
    public Factura(OrdenCompra compra) {
        super(compra);
    }
}
class Articulo {
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;

    public Articulo(float peso,float precio,String nombre, String desc){
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
        return calcPreciosinIVA()+calcIVA();
    }

    public float calcPreciosinIVA() {
        return cantidad * articulo.getPrecio();
    }

    public float calcIVA() {
        return calcPreciosinIVA() / 10;
    }

    public float calcPeso() {
        return articulo.getPeso() * cantidad;
    }
}
class OrdenCompra {
    private Date fecha;
    private String estado;
    protected Cliente cliente;
    private ArrayList<DetalleOrden> Orden ;
    public OrdenCompra(Cliente cliente) {
        this.cliente=cliente;
        Orden = new ArrayList();
        Calendar cal = GregorianCalendar.getInstance();
        fecha=cal.getTime();

    }

    public void AgregarCompra(DetalleOrden gg) {
        Orden.add(gg);
    }

    public float calcPeso() {
        float precio = 0;
        for (int i = 0; i < Orden.size(); i++) {
            precio += Orden.get(i).calcPeso();
        }
        return precio;
    }
    public float calcPrecio() {
        float precio = 0;
        for (int i=0;i<Orden.size();i++){
            precio+=Orden.get(i).calcPrecio();
        }
        return precio;
    }
    public float calcPreciosinIVA() {
        float precio = 0;
        for (int i=0;i<Orden.size();i++){
            precio+=Orden.get(i).calcPreciosinIVA();
        }
        return precio;
    }
    public float calcIVA() {

        float precio = 0;
        for (int i=0;i<Orden.size();i++){
            precio+=Orden.get(i).calcIVA();
        }
        return precio;
    }

    public String EstadoAactual() {
        Date calenrio = new Date();
        if (0>fecha.getDay()+4-calenrio.getDay()){
            return "entregado";
        }
        return "preparando pedido";
    }
}
class Pago {
    protected float monto;
    private Date fecha;
    public Pago(OrdenCompra orden){
        monto=orden.calcPrecio();
    }

    public float getMonto() {
        return monto;
    }
}
class Efectivo extends Pago {
    public Efectivo(OrdenCompra orden) {
        super(orden);
    }
    public float calcDevolucion(float pagar){
        monto=monto-pagar;
        if(0<monto) {
            return 0;
        }
        float devolver=-monto;
        monto=0;
        return devolver;
    }

}
class Transferencia extends Pago {
    private String banco;
    private String numCuenta;
    public Transferencia(OrdenCompra orden,String banco,String numCuenta) {
        super(orden);
        this.banco=banco;
        this.numCuenta=numCuenta;
    }
}
class Tarjeta extends Pago {
    private String tipo;
    private String numTransaccion;
    public Tarjeta(OrdenCompra orden,String tipo,String numTransaccion) {
        super(orden);
        this.tipo=tipo;
        this.numTransaccion=numTransaccion;
    }
}
public class Main {
    public static void main(String[] args) {
        Cliente pablo = new Cliente("Juan","1234567","conce 1412");
        Articulo fideo = new Articulo(2.4f,12f,"fideo","fideo numero 5 rapidos de hacer");
        Articulo salsa_roja = new Articulo(0.7f,5.6f,"salsa blanca o roja","salsa con trozos de verdura sin carne");
        Articulo salsa_blanca = new Articulo(0.7f,5.6f,"salsa blanca o roja","salsa con trozos de verdura sin carne");
        Articulo carne_molida = new Articulo(1.1f,9f,"carne moldida de cerdo","carne molida %7 grasa");
        DetalleOrden pedido1 = new DetalleOrden(2,fideo);
        DetalleOrden pedido2 = new DetalleOrden(4,salsa_roja);
        DetalleOrden pedido3 = new DetalleOrden(0,salsa_blanca);
        DetalleOrden pedido4 = new DetalleOrden(1,carne_molida);
        OrdenCompra compra = new OrdenCompra(pablo);
        compra.AgregarCompra(pedido1);
        compra.AgregarCompra(pedido2);
        compra.AgregarCompra(pedido3);
        compra.AgregarCompra(pedido4);
        float precio=compra.calcPrecio();
        System.out.println(precio);
        precio=compra.calcPreciosinIVA();
        System.out.println(precio);
        precio=compra.calcIVA();
        System.out.println(precio);
        float peso=compra.calcPeso();
        System.out.println(peso);
        Efectivo pago=new Efectivo(compra);
        System.out.println("monto por pagar: "+pago.monto);
        float vuelto=pago.calcDevolucion(50f);
        System.out.println("vuelto: "+vuelto);
        System.out.println("falta por pagar: "+pago.monto);
        vuelto=pago.calcDevolucion(30f);
        System.out.println("vuelto: "+vuelto);
        System.out.println("falta por pagar: "+pago.monto);
        Cliente juan = new Cliente("juan","7654321","con 1214");
        OrdenCompra compra1=new OrdenCompra(juan);
        compra1.AgregarCompra(pedido1);
        DetalleOrden pedido5 =new DetalleOrden(4,salsa_blanca);
        compra1.AgregarCompra(pedido5);
        Tarjeta visa= new Tarjeta(compra1,"visa","1234567");
        Boleta boleta= new Boleta(compra);
        System.out.println(boleta.toString());
        System.out.println(compra.EstadoAactual());
        
    }
}
