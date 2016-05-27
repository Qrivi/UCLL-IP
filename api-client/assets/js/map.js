window.onload = init;

var cityName;
var topBar;

function init() {
    topBar = document.getElementById("top");
    cityName = document.getElementById("txt_cityName");
    adjustWidthOfCityName();
    cityName.onblur = adjustWidthOfCityName;

    loadMap();
}
function calcCityNameWidth() {
    var tmp = document.createElement("span");
    tmp.className = "input-element tmp-element";
    tmp.innerHTML = cityName.value.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
    topBar.appendChild(tmp);
    var theWidth = tmp.getBoundingClientRect().width;
    topBar.removeChild(tmp);
    return theWidth;
}

function adjustWidthOfCityName() {
    cityName.style.width = calcCityNameWidth() + 3 + "px";
    console.log(calcCityNameWidth() + "px");
}

function loadMap() {
    var myLatLng = {lat: 50.879123, lng: 4.701022};

    var map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: myLatLng
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: "Hello World!"
    });
}