function addCart(formulario){
    //Posicion 0 del formulario
    var idProducto = formulario.elements[0].value;
    //Posicion 1 del formulario
    var existencias = formulario.elements[1].value;
    
    if(existencias > 0){
        var ruta = "/carrito/agregar/"+ idProducto;
        $("#resultBlock").load(ruta);
    }else{
        alert("No hay existencias....");
    }
    
    

}

function muestraImagen(input) {
    //Si hay un archivo
    if (input.files && input.files[0]) {
        //Lector de archivos para leerlos
        var lector = new FileReader();
        //cuando se cargue el archivo
        lector.onload = function (e) {
            //Poner la informacion leida como el SRC de la etiqueta
            $('#blah').attr('src', e.target.result)
                    .height(200);
        };

        lector.readAsDataURL(input.files[0]);
    }
}