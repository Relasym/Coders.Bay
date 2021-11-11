function wrongFunction() {
  alert("Falsche Antwort!");
}

function correctFunction() {
  alert("Richtig!");
  window.location.replace("richtig.html");
}

function helpFunction() {
  $(".helppanel").toggleClass("open");
}