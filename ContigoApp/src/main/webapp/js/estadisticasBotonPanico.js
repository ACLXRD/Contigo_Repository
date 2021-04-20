var estudiante
var estadisticas
var grados
var gradosInstitucion = []
var boton = document.getElementById("generarEstadisticas");

$(document).ready(function () {
    traerGrados();
    estudiante = getCookie("token");
});

function traerGrados() {
    $.ajax({
        method: 'GET',
        url: 'Grado',
        data: "json",
        contentType: "JSON application/json charset=utf-8",
        success: function (response) {
            if (response.tipo === "ok") {
                console.log(response);
                grados = response.Grados;
                cargarSelectGrados(grados);
            } else {
                console.log(response.mensaje);
            }
        },
        error: function (response) {
            console.log(JSON.stringify(response))
        }
    });
}

function cargarSelectGrados(grados) {
    for (var grado in grados) {
        document.getElementById("grados").innerHTML += "<option value='" + grados[grado] + "'>" + grados[grado] + "</option>";
    }

}

$("#btnGerar").on("click", function () {
    //window.location.assign("gestionCurso.html")
    consultarInformacion();
    $("#grados").on("click", function () {
        gradoseleccionado = $('#grados option:selected').val()
        console.log(gradoseleccionado)
        obj = {
            grado: gradoseleccionado
        }
        consultarEstudianteGrado(obj);
    });
});

function consultarInformacion(obj) {
    $.ajax({
        method: 'GET',
        url: 'EstadisticasBtnPanico',
        data: JSON.stringify(obj),
        dataType: "json",
        success: function (response) {
            if (response.tipo === "ok") {
                console.log(response);
                estadisticas = response.Estadisticas;
                // console.log(estadisticas)
                grafica1(estadisticas)
                filtrarClicksporDia(estadisticas)
            } else {
                console.log(response.mensaje);
            }
        },
        error: function (response) {
            console.log(JSON.stringify(response))
        }
    });
}

// Grafica mensual de la cantidad de veces que los estudiantes han dado click al botón de pánico
function grafica1(estadisticas) {
    var meses = ['EN.', 'FEB.', 'MAR', 'ABR', 'MAY', 'JUN', 'JUL', 'AGT', 'SEP', 'OCT', 'NOV', 'DIC']
    var frecuencia = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0] // frecuenciauencia por mes
    for (let index = 0; index < estadisticas.length; index++) {
        for (let i = 0; i < estadisticas[index].fechas.length; i++) {
            var fecha = estadisticas[index].fechas[i]
            for (let j = 0; j < meses.length; j++) {
                var n = frecuencia[j]
                if (fecha.charAt(6) == (j + 1).toString()) {
                    n++
                    frecuencia[j] = n
                } else if ((fecha.charAt(5) + fecha.charAt(6)) == (j + 1).toString()) {
                    n++
                    frecuencia[j] = n
                }
            }
        }
    }
    parametrosGrafica(meses, frecuencia)
    // console.log(frecuencia)
}

// Grafica semanal de la cantidad de veces que los estudiantes han dado click al botón de pánico

function grafica2(estadisticas) {
    var semanas = ['En', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
    var frecuencia = [0, 0, 0, 0] // frecuenciauencia por semana
}

function parametrosGrafica(x, y) {
    var chart = new Chart(document.getElementById('cantidadEstudiantes').getContext('2d'), {
        type: 'line',
        data: {
            labels: x,
            datasets: [{
                label: 'Interacciones mensuales con el botón de Pánico',
                borderColor: '#2d4059',
                backgroundColor: 'rgba(176, 196, 222, 0.35)',
                data: y
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Cantidad de estudiantes usando  Contigo en la Institución'
            },
            legend: {
                display: false
            }
        }
    });
}


function filtrarClicksporDia(estadisticas) {
    var clicksdiarios = 0;
    var dias = [
        'domingo',
        'lunes',
        'martes',
        'miércoles',
        'jueves',
        'viernes',
        'sábado',
    ];
    var frec = [0, 0, 0, 0, 0, 0, 0];
    for (let index = 0; index < estadisticas.length; index++) {
        for (let i = 0; i < estadisticas[index].fechas.length; i++) {
            var fecha = estadisticas[index].fechas[i];
            ms = Date.parse(estadisticas[index].fechas[i]);
            fecha2 = new Date(ms).getDay();
            const nombreDia = dias[fecha2];
            for (let j = 0; j < dias.length; j++) {
                if (fecha2 == (j).toString()) {
                    var n = frec[j];
                    n++;
                    frec[j] = n
                }
            }
        }
    }
    var tablaInscripcionAconversatorio = new Chart(document.getElementById('conversatoriosComunes').getContext('2d'), {
        type: 'bar',
        data: {
            labels: dias,
            datasets: [{
                label: 'Días',
                data: frec,
                backgroundColor: [
                    'rgb(136, 145, 200,0.6)',
                    'rgba(210, 180, 140, 0.6)',
                    'rgba(188, 143, 143, 0.6)',
                    'rgba(153, 102, 255, 0.6)'
                ],
                borderColor: [
                    'rgb(136, 145, 200)',
                    'rgba(210, 180, 140, 1)',
                    'rgba(188, 143, 143, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            title: {
                display: true,
                text: 'Número de estudiantes por grado que han asistido a conversatorios'
            },
            layout: {
                padding: {
                    left: 0,
                    right: 25,
                    top: 0,
                    bottom: 0
                }
            }
        }
    });
}

function consultarEstudianteGrado(obj) {
    $.ajax({
        method: 'POST',
        url: 'EstudiantePorGradoServlet',
        data: JSON.stringify(obj),
        dataType: "json",
        success: function (response) {
            if (response.tipo === "ok") {
                console.log(response);
                estudiantesGrado = response.estudiantes;
                graficaBotonPanicoClicksGrado(estudiantesGrado);
            } else {
                console.log(response.mensaje);
            }
        },
    });
}

function crearArregloFrecuenciaGradosInstitucion(grados) {
    gradosInstitucion
    for (let i = 0; i < grados[index]; i++) {
        gradosInstitucion[i]=0;
    }
}

function graficaBotonPanicoClicksGrado(estudiantesGrado) {
    var clicksGrado = 0;
    crearArregloFrecuenciaGradosInstitucion(grados);
    for (let index = 0; index < estadisticas.length; index++) {
        for (let i = 0; i < estadisticas[index].fechas.length; i++) {
            if (estadisticas[index].estudiante[i] == estudiantesGrado[index].documento) {
                clicksGrado = frec[j];
                clicksGrado++;
                frec[j] = clicksGrado
            }

        }
    }
}


