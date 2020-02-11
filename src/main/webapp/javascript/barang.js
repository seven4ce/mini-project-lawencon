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
// try{
request.open('GET', 'http://localhost:7000/api/barang/search?startDate=&endDate=', true);
request.onload = function () {

  // Begin accessing JSON data here
  var data = JSON.parse(this.response);
  var results = data.result;
  if (request.status >= 200 && request.status < 400) {

    const button = document.createElement('div');
    button.setAttribute('class', 'card');
  
    const buttonDownload = document.createElement('h3');
    buttonDownload.textContent = `Download File`;
  
    containerButton.appendChild(button); 
    button.appendChild(buttonDownload);
      
        button.addEventListener ("click", function() {     
  
          alert("Download File ?");
  
          window.open('http://localhost:7000/api/download/barang/search?startDate=&endDate=');
  
        });
   
      results.forEach(getDataJsonBody => {
      const card = document.createElement('div');
      card.setAttribute('class', 'card');

      const h1 = document.createElement('h1');
      h1.textContent = getDataJsonBody.namaBarang;

      const h2 = document.createElement('h2');
      h2.textContent = `Harga Satuan : ${getDataJsonBody.harga.hargaSatuan}`;

      const h3 = document.createElement('h2');
      h3.textContent = `Jenis Barang : ${getDataJsonBody.tipeBarang.jenisBarang}`;

      const h4 = document.createElement('h2');
      h4.textContent = `Supplier : ${getDataJsonBody.supplier.namaSupplier}`;

      const h5 = document.createElement('h2');
      h5.textContent = `Id Supplier : ${getDataJsonBody.supplier.idSupplier}`;

      const h6 = document.createElement('h2');
      h6.textContent = `Nomor Telp Supplier : ${getDataJsonBody.supplier.phoneNo}`;

      const h7 = document.createElement('h2');
      h7.textContent = `Alamat Supplier : ${getDataJsonBody.supplier.alamat}`;

      const h8 = document.createElement('h2');
      h8.textContent = `Stock Barang : ${getDataJsonBody.stockBarang}`;

      container.appendChild(card);
      card.appendChild(h1);
      card.appendChild(h2);
      card.appendChild(h3);
      card.appendChild(h4);
      card.appendChild(h5);
      card.appendChild(h6);
      card.appendChild(h7);
      card.appendChild(h8);
      
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

// }catch(e){
// console.log('catch', e);

// const card1 = document.createElement('div');
// card1.setAttribute('class', 'cardMain');

// const h9 = document.createElement('h4');
//  h9.textContent = `Could Not Connect To Server, Please Check Your Connection And Try Again !`;

// containerButton.appendChild(card1); 
// card1.appendChild(h9);
  
// card1.addEventListener ("click", function() {     

//       alert(" Back To Home ?");

//           card1.onclick = function(){        
//           window.location = `MainMenu.html`;
//            };

//     });
// }
