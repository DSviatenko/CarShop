function loadContent() {

    let objects = JSON.parse(Get("http://localhost:8080/car").responseText);
    let table = document.getElementById("carList");
    // alert(objects[0].brand + objects[0].model + objects[0].price);

    for (let i = 0; i < objects.length; i++) {

        let col = document.createElement("div");
        col.setAttribute("class", "col");

        let card = document.createElement("div");
        card.setAttribute("class", "card shadow-sm");

        let img = document.createElement("img");
        img.setAttribute("src", "resources/img/" + i + ".jpg");
        img.setAttribute("class", "img-thumbnail")

        let cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");

        let cardText = document.createElement("p");
        cardText.setAttribute("class", "card-text");
        cardText.appendChild(document.createTextNode(objects[i].brand + " " + objects[i].model));

        let buttonAndPrice = document.createElement("div");
        buttonAndPrice.setAttribute("class", "d-flex justify-content-between align-items-center");

        let buttonGroup = document.createElement("div");
        buttonGroup.setAttribute("class", "btn-group");

        let buttonDetail = document.createElement("a");
        buttonDetail.setAttribute("type", "button");
        buttonDetail.setAttribute("class", "btn btn-sm btn-outline-secondary");
        buttonDetail.setAttribute("href", "http://localhost:8080/car/" + objects[i].id);
        buttonDetail.appendChild(document.createTextNode("Details"));

        let buttonPhone = document.createElement("a");
        buttonPhone.setAttribute("type", "button");
        buttonPhone.setAttribute("class", "btn btn-sm btn-outline-secondary");
        buttonPhone.appendChild(document.createTextNode("Phone"));


        let cardPrice = document.createElement("strong");
        cardPrice.setAttribute("class", "text-body-secondary");
        cardPrice.appendChild(document.createTextNode(objects[i].price + "$"));

        buttonGroup.appendChild(buttonDetail);
        buttonGroup.appendChild(buttonPhone);
        buttonAndPrice.appendChild(buttonGroup);
        buttonAndPrice.appendChild(cardPrice);
        cardBody.appendChild(cardText);
        cardBody.appendChild(buttonAndPrice);
        card.appendChild(img);
        card.appendChild(cardBody);
        col.appendChild(card);
        table.appendChild(col);
        
    }
}

function Get(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("GET", requestUrl, false);
    Httpreq.send(null);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to GET " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found GET " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function post(requestUrl, body) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("POST", requestUrl, false);
    Httpreq.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    Httpreq.send(body);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to POST " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found POST " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function Delete(requestUrl) {
    let Httpreq = new XMLHttpRequest(); // a new request
    Httpreq.open("DELETE", requestUrl, false);
    Httpreq.send(null);
    console.log(Httpreq.status);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}