$(document).ready(function(){
    let footer=`<div id="footer">
<div id="superior">
    <ul>
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Contacto</a></li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Ayuda</a></li>
    </ul>
    <hr>
</div>
<table id="table-footer">
    <tr>
        <td id="Columna-footer">
            <div id="PrimeraColumna">
                <div>
                    <span id="contigo2"><a href="#">Contigo</a></span>
                </div>
                <div id="iconos">
                    <a href="#"><img class="facebook" src="Iconos/facebook.png"></a>
                    <a href="#"><img class="instagram" src="Iconos/instagram.png"></a>
                    <a href="#"><img class="linkedin" src="Iconos/linkedin.png"></a>
                    <a href="#"><img class="youtube" src="Iconos/youtube.png"></a>
                </div>
            </div>
        </td>
        <td id="Columna-footer">
            <div id="SegundaColumna">
                <div>
                    <span id="bold">Empresa</span>
                </div>
                <div>
                    <span><a href="#">Terminos & Condiciones</a></span>
                </div>
                <div>
                    <span><a href="#">Política de Privacidad</a></span>
                </div>
                <div>
                    <span><a href="#">FAQS</a></span>
                </div>
            </div>
        </td>
        <td id="Columna-footer">
            <div id="TercerColumna">
                <div>
                    <span id="bold">About</span>
                </div>
                <div>
                    <span><a href="#">Equipo</a></span>
                </div>
                <div>
                    <span><a href="#">Blog</a></span>
                </div>
                <div>
                    <span><a href="#">Contacto</a></span>
                </div>
            </div>
        </td>
    </tr>
</table>

<div id="Inferior">
    <div>
        <span>Copyright © 2021 Contigo. Creado por El Grupo De Atrás A La Derecha</span>
    </div>
</div>
</div>`;

let menuSuperior=`<div id="menu">
<ul>
    <li><a id="contigo-1" href="#">Contigo</a></li>
    <div id="up">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Contacto</a></li>
        <li><a href="#">About Us</a></li>
        <li><a href="#">Ayuda</a></li>
    </div>
</ul>
</div>`;

    $("body").prepend(menuSuperior);
    $("body").append(footer)
});