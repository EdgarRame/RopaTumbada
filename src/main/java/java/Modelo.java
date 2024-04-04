package java;

public class Modelo {
    
    public class ModeloEspecialidad {
        private int id; 
        private String nombre; 
        
        public ModeloEspecialidad() {
        }
        
        public ModeloEspecialidad(int id, String nombre) {
            this.id = id; 
            this.nombre = nombre; 
        }
        
        public int getId() {
            return Id; 
        }
        
        public void setId(int Id) {
            this.Id = Id; 
        }
        
        public String getNombre() {
            return Nombre; 
        }
        
        public void setNombre(String Nombre) {
            this.Nombre = Nombre; 
        }
        
        public String getApellido(){
            this.Apellido=Apellido;
        }
        
        public void setApellido(String Apellido){
            return.Apellido;
        }
        
        public int setExistencia(){
            this.Existencia = Existencia;
        }
        
        public void getExistencia(int existencia){
            return.existencia;
        }
        
        public int setTelefono(){
            this.Telefono = Telefono;
        }
        
        public void getTelefono(int Telefono){
            return.Telefono;
        }
        
        public String setCiudad(){
            this.Ciudad = Ciudad;
        }
        
        public void getCiudad(String Ciudad){
            return.Ciudad;
        }
        
        public int setPrecio(){
            this.Precio = Precio;
        }
        
        public void getPrecio(intPrecio){
            return.Precio;
        }
        
        public int setExistencia(){
            this.Existencia = Existencia
        }
        
        public void Existencia(){
            return.Existencia;
        }
        
        @Override
        public String toString() {
            return "Especialidad{" + 
                    "id=" + id + 
                    ", nombre='" + nombre + '\'' +
                    '}';
        }
        
    }
}
