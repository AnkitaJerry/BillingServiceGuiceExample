Presentación
==========================

Este es un ejemplo de la utilización de [Google guice](https://code.google.com/p/google-guice/) para la implementación de injección de dependencias en JAVA. Se trabajo sobre el ejemplo del Getting starting de Google guice.


---

Como correr el proyecto
-----------------------


Video
-----

Para ver el video puede ingresar a:

[http://dmuneras.webfactional.com/lectures/videos/BillingService.swf]("http://dmuneras.webfactional.com/lectures/videos/BillingService.swf)

Si el sitio no abre desde aquí copie y pegue la URL en el navegador.

Instrucciones 
-------------

El proyecto simplemente debe ser importado a eclipse y se debe correr el método main que esta ubicado en la clase: BillingPresentation, luego de correrlo se desplegara el siguiente menú en la consola de eclipse:


```java
Seleccione una metodo de pago            
 1 => Pago pay pal                        
 2 => Google                              
 3 => PagoUlatam                          
 x => Salir                               
 Si conoce otro procesador de tarjetas de 
 credito soportado puede ingresar su      
 su nombre   
 Con que método de pago quiere pagar: 
```


Los procesadores de tarjetas de crédito soportados hacen referencia a los procesadores que se pueden implementar y ser cargados en tiempo de ejecución; en nuestro proyecto utilizamos un XML de registro de procesadores llamado **config.xml**, la estructura del XML es la siguiente:

```java
<?xml version="1.0"?>
<wallet location ="[directorio]">
	<creditcardprocessor>
		<name>[nombreProcessador]</name>
	</creditcardprocessor>
</wallet>
```

**[directorio]:** directorio donde se encuentran las clases con las implementaciones de nuevos procesadores de tarjetas de crédito.

**[nombreProcesador]:*** El nombre del procesador es el nombre del archivo .java donde esta la implementación del procesador a cargar.


Un ejemplo de procesador es el siguiente:

```java
import billing.ChargeResult;
import billing.UnreachableException;
import creditCard.CreditCard;
import creditCard.CreditCardProcessor;


public class Default implements CreditCardProcessor {

@Override
    public ChargeResult charge(CreditCard creditCard, int amount)
    throws UnreachableException {
    return new ChargeResult(true, "Aprobado credito");
}
   

@Override
    public String toString() {
    return "Pago otros proveedores";
}
}
```

Si quiere utilizar uno de esos procesadores o implementar uno nuevo, debe registrarlo en el XML que esta ubicado en el src del proyecto y ubicar el implementacionProcesador.java en la carpeta que registró en el **config.xml**.

