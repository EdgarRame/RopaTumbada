package java;
   
    public class Modelo {
        private int Id; 
        private String Nombre;
        private String Apellido;
        private String Ciudad;
        private int Existencia;
        private int Telefono;
        private int Precio;
        
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
            return Apellido;
        }
      
        public void setApellido(String Apellido){
            this.Apellido = Apellido;
        }
        
        public int getExistencia(){
            return Existencia;
        }
        
        public void setExistencia(int Existencia){
            this.Existencia = Existencia;
        }
        
        public int getTelefono(){
            return Telefono;
        }
        
        public void setTelefono(int Telefono){
            this.Telefono = Telefono;
        }
        
        public String getCiudad(){
            return Ciudad;
        }
        
        public void setCiudad(String Ciudad){
            this.Ciudad = Ciudad;
        }
        
        public int getPrecio(){
            return Precio;
        }
        
        public void setPrecio(int Precio){
            this.Precio= Precio;
        }
 
        @Override
        public String toString() {
            return "Especialidad{" + 
                    "id=" + Id + 
                    ", nombre='" + Nombre + '\'' +
                    '}';
    }    
}