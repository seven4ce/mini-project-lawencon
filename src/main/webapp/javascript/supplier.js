const app = document.getElementById('root');

const logo = document.createElement('img');
logo.src = 'img/logo.png';
logo.onclick = function(){        
  window.location = `img/logo.png`;
};

const containerButton = document.createElement('div');
containerButton.setAttribute('class', 'container');

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(logo);
app.appendChild(containerButton);
app.appendChild(container);


var request = new XMLHttpRequest();
request.open('GET', 'http://localhost:7000/api/supplier/all', true);
request.onload = function () {

  // Begin accessing JSON data here
  var data = JSON.parse(this.response);
  var results = data.result;
  if (request.status >= 200 && request.status < 400) {

    
      results.forEach(getDataJsonBody => {
      const card = document.createElement('div');
      card.setAttribute('class', 'card');

      const h1 = document.createElement('h1');
      h1.textContent = `Id Supplier : ${getDataJsonBody.idSupplier}`;

      const h2 = document.createElement('h2');
      h2.textContent = `Nama Supplier : ${getDataJsonBody.namaSupplier}`;

      const h3 = document.createElement('h2');
      h3.textContent = `No Supplier : ${getDataJsonBody.phoneNo}`;

      const h4 = document.createElement('h2');
      h4.textContent = `Alamat : ${getDataJsonBody.alamat}`;

      container.appendChild(card);
      card.appendChild(h1);
      card.appendChild(h2);
      card.appendChild(h3);
      card.appendChild(h4);
      
    });

    
  } else {
    const card1 = document.createElement('div');
    card1.setAttribute('class', 'cardMain');

    const h9 = document.createElement('h4');
     h9.textContent = `Could Not Connect To Server, Please Check Your Connection And Try Again !`;
  
    containerButton.appendChild(card1); 
    card1.appendChild(h9);
      
    card1.addEventListener ("click", function() {     
  
          alert(" Back To Home ?");
  
              card1.onclick = function(){        
              window.location = `MainMenu.html`;
               };
  
        });
  }
}

request.send();

