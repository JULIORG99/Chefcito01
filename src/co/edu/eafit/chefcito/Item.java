package co.edu.eafit.chefcito;

public class Item {

	protected String idreceta;
	protected String nombre;
	protected String descripcion;
	protected String preparacion;
	protected String dificultad;
	protected String tiempo;
	protected String imagen;
	protected String ingrediente;
	protected String cantidad;
	protected long id;

	public Item(String idreceta, String nombre, String descripcion,
			String preparacion, String dificultad, String tiempo,
			String ingrediente, String cantidad, String imagen) {
		super();

		this.idreceta = idreceta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.tiempo = tiempo;

		this.ingrediente = ingrediente;
		this.cantidad = cantidad;

		this.imagen = imagen;

	}

	public Item(String idreceta, String nombre, String descripcion,
			String preparacion, String dificultad, String tiempo,
			String ingrediente, String cantidad, String imagen, long id) {
		super();
		this.idreceta = idreceta;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.tiempo = tiempo;
		this.ingrediente = ingrediente;
		this.cantidad = cantidad;
		this.imagen = imagen;
		this.id = id;
	}

	public String getIdreceta() {
		return idreceta;
	}

	public void setIdreceta(String idreceta) {
		this.idreceta = idreceta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
}