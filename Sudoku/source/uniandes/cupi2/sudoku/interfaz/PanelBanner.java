/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_sudoku
 * Autor: Equipo Cupi2 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.sudoku.interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el panel donde se muestra la imagen banner.
 * @author JuanUrrea
 */
public class PanelBanner extends JPanel
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ruta de la imagen Banner.
	 */
	public final static String PHBANNER = "data/imagenes/banner.png";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Etiqueta de la imagen.
	 */
	private JLabel lbFoto;


	// ----------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------

	/**
	 * Crea un nuevo Panel Banner.
	 * pre: La ruta PHBANNER contiene una imagen.
	 * post: Se ha añadido la foto banner al panel y se ha inicializado.
	 */
	public PanelBanner()
	{
		setLayout(new GridLayout(1,1));
		lbFoto = new JLabel();
		lbFoto.setIcon(new ImageIcon(PHBANNER));
		lbFoto.setHorizontalAlignment(JLabel.CENTER);
		add(lbFoto);
	}

}
