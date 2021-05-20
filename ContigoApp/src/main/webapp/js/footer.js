$(document).ready(function () {
    const footer =  `
    <div class="box-1">
        <div class="logo">
            <a href="#"><p class="title">Contigo</p></a>
        </div>
        <div class="box-11">
            <div class="icon">    
                <a href="#"><img class="sepia" src="Iconos/facebook.png" alt=""></a>
            </div>
            <div class="icon">
                <a href="#"><img class="sepia" src="Iconos/instagram.png"></a>
            </div>
            <div class="icon">
                <a href="#"><img class="sepia" src="Iconos/linkedin.png"></a>
            </div>
            <div class="icon">
                <a href="#"><img class="sepia" src="Iconos/youtube.png"></a>
            </div>
        </div>
        <div class="row">
            <img id="lines" src="img/lines.png" alt="">
        </div>
    </div>
    <div class="box-2">
        <div class="rxw">
            <div class="coll">
                <a href="#"><p class="option">Servicios</p></a> 
                <a href="#"><p class="item">Conversatorios</p></a> 
                <a href="#"><p class="item">Historias de decisión</p></a> 
                <a href="#"><p class="item">Chat privado</p></a> 
                <a href="#"><p class="item">Citas de ayuda</p></a> 
            </div>
            <div class="coll">
                <a href="#"><p class="option">About</p></a> 
                <a href="#"><p class="item">Desarrolladores</p></a> 
                <a href="#"><p class="item">Personal calificado</p></a> 
                <a href="#"><p class="item">Contacto</p></a> 
            </div>
            <div class="coll">
                <a href="#"><p class="option">Soporte</p></a> 
                <a href="#"><p class="item">Términos & Condiciones</p></a> 
                <a href="#"><p class="item">Política de Privacidad</p></a> 
                <a href="#"><p class="item">Preguntas frecuentes</p></a> 
            </div>
        </div>
        <p class="item" id="copyright">Copyright © 2021 Contigo. Creado por El Grupo De Atrás A La Derecha</p>
    </div>

    `
    $('footer').append(footer)
})