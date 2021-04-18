$(document).ready(function () {
    usuario = parseInt(getCookie("tipoUsuario"));
    token = parseInt(getCookie("token"));
    documento = parseInt(getCookie("documento"));
    console.log(documento);
    console.log(usuario);
    console.log(token);
});


const imageUploader = document.getElementById('img-uploader');
const CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/miguel26697/image/upload';
const CLOUDINARY_UPLOAD_PRESET = 'wmruximj';
var img


imageUploader.addEventListener('change', (e) => {
    console.log(e)
    e.preventDefault();
    const file = e.target.files[0];
    const formData = new FormData();
    formData.append('file', file);
    formData.append('upload_preset', CLOUDINARY_UPLOAD_PRESET);

    $.ajax({
        url: CLOUDINARY_URL,
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function () {
        },
        success: function (result, textStatus, request) {
            informacion = result
            img = informacion.url;
            if (result != "error") {
                console.log(result);
            } else {
                console.log("error");
            }
        },
        complete: function (result) {
        },
        error: function (result) {
        }
    });
});

function crearHistoria() {

    nombre = $("#Nombre").val();
    descripcion = $("#Descripcion").val();
  
  
    informacion = {
        PERSONAL_PERSONA_documento: documento,
        titulo: titulo,
        descripcion: descripcion,
        urlImagen: img,
    };


    console.log(informacion);
    $.ajax({
        url: "Historia",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(informacion),
        contentType: "JSON application/json charset=utf-8",
        beforeSend: function () {
        },
        success: function (result, textStatus, request) {
            console.log(result);
            if (result != "error") {
                console.log(result);
            } else {
                console.log("error");
            }
        },
        complete: function (result) {
        },
        error: function (result) {
        }
    });

}
