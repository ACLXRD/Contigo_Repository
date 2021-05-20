
var texto2= document.getElementById("texto2");
document.getElementById('hablar').addEventListener("click", () => {
    /*var texto=document.getElementById("texto").value;
    var texto2= document.getElementById("texto2");
    console.log(texto2.innerHTML)
    decir(texto);*/
    
    speechSynthesis.onvoiceschanged = () => {
        var text = texto2; 
        const synth = speechSynthesis
        const voices = synth.getVoices()
        console.log(text)
        const utterThis = new SpeechSynthesisUtterance(text)
        utterThis.voice = voices.find(v => v.name === 'Jorge')
        utterThis.pitch = 0
        utterThis.rate = 1
        synth.speak(utterThis)
      }
});

function decir(texto) {
    speechSynthesis.speak(new SpeechSynthesisUtterance(texto));       
}







