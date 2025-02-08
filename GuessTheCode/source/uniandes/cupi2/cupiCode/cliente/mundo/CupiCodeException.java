/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiCode
 * Autor: Equipo Cupi2 2015
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiCode.cliente.mundo;

/**
 * Excepción que se lanza para indicar un problema en el juego.
 */
public class CupiCodeException extends Exception
{
    /**
     * Construye una nueva excepción de este tipo con el mensaje indicado.
     * <b>post: </b> Se creó el excepción correctamente.<br>
     * @param pMensaje Mensaje que describe la causa de la excepción. pMensaje != null.
     */
    public CupiCodeException( String pMensaje )
    {
        super( pMensaje );
    }
}
