var btnAbrirPopup = document.getElementById('btn-abrir-popup'),
    overlay = document.getElementById('overlay'),
    popup = document.getElementById('popup');
   

btnAbrirPopup.addEventListener('click', function () {
    crear();
});

btnCerrarPopup = document.getElementById('btn-cerrar-popup');

/*btnCerrarPopup.addEventListener('click', function () {
    
});*/

var valor = $('.btn-abrir-popup').val();
function crear() {
    let txt = '<div class="overlay active" id="overlay">' +
        '<div class="popup active" id="popup">' +
        '<a href="#" id="btn-cerrar-popup" class="btn-cerrar-popup" onclick="eliminar()"> ' +
        '<i class="fa fa-times" aria-hidden="true"></i>' + '</a>' +
        '<h3>'+valor+'</h3>' +
        '<h4>Formulario</h4>' +
        '<form action="">' +
        '<div class="contenedor-inputs">' +
        '<input type="text" placeholder="Texto siguiente">' +
        '<div class="numeroOpc">' +
        '<p>Numero de opciones</p>' +
        '<button id="opciones" class="Aumentar">+</button>' +
        '<button class="Disminuir" id="menos">-</button>' +
        '</div>' +
        '<div id="extra"></div>' +
        '</div>' +
        '<input type="submit" class="btn-submit" value="Crear">'
        + '</form>'
        + '</div>'
        + '</div';
    $("#ventana").append(txt);
}

function eliminar(){
    $('#overlay').remove();
}