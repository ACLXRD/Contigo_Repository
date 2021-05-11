document.getElementById('hablar').addEventListener("click", () => {
    var texto=document.getElementById("texto").value;
    var texto2= document.getElementById("texto2");
    console.log(texto2.innerHTML)
    decir(texto);

    /*speechSynthesis.onvoiceschanged = () => {
        const text = 'Hola, soy Jorge Baumann y mi perro se llama Rambo'
        const synth = speechSynthesis
        const voices = synth.getVoices()
        const utterThis = new SpeechSynthesisUtterance(text)
        utterThis.voice = voices.find(v => v.name === 'Jorge')
        utterThis.pitch = 1.5
        utterThis.rate = 1
        synth.speak(utterThis)
      }*/
    
});

function decir(texto) {
    speechSynthesis.speak(new SpeechSynthesisUtterance(texto));       
}







