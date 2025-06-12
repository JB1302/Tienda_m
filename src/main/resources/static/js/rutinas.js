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