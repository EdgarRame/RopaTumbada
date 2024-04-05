package java;

public class Modelo {
    
    public class ModeloEspecialidad {
        private int Id; 
        private String Nombre;
        private String Apellido;
        private String Ciudad;
        private int Existencia;
        private int Telefono;
        private int Precio;
        
        public ModeloEspecialidad() {
        }
        
        public ModeloEspecialidad(int Id, String Nombre, String Apellido, String Ciudad,
            int Existencia, int Telefono,int Precio) {
            this.Id = Id; 
            this.Nombre = nombre;
            this.Apellido = apellido;
            this.Ciudad = ciudad;
            this.Existencia = existencia;
            this.Telefono = telefono;
            this.Precio = precio;    
        }
        
        public int getId() {
            this.id= Id;
        }
        
        public void getId(int Id) {
            return.id; 
        }
        
        public String getNombre() {
            this.nombre= Nombre; 
        }
        
        public void getNombre(String Nombre) {
            return.nombre; 
        }
        
        public String setApellido(){
            this.apellido= Apellido;
        }
      
        public void getApellido(String Apellido){
            return.apellido;
        }
        
        public int setExistencia(){
            this.existencia= Existencia;
        }
        
        public void getExistencia(int existencia){
            return.existencia;
        }
        
        public int setTelefono(){
            this.telefono = Telefono;
        }
        
        public void getTelefono(int Telefono){
            return.telefono;
        }
        
        public String setCiudad(){
            this.ciudad = Ciudad;
        }
        
        public void getCiudad(String Ciudad){
            return.ciudad;
        }
        
        public int setPrecio(){
            this.precio = Precio;
        }
        
        public void getPrecio(intPrecio){
            return.precio;
        }
        
        public int setExistencia(){
            this.existencia = Existencia
        }
        
        public void Existencia(){
            return.existencia;
        }
        
        @Override
        public String toString() {
            return "Especialidad{" + 
                    "id=" + Id + 
                    ", nombre='" + Nombre + '\'' +
                    '}';
        }
        
    }
}
