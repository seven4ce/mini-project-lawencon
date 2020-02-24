const app = document.getElementById('root');

const logo = document.createElement('img');
logo.src = 'img/logo.png';
logo.onclick = function(){        
  window.location = `img/logo.png`;
};

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(logo);
app.appendChild(container);

var request = new XMLHttpRequest();
// try{
request.open('GET', 'http://localhost:7000/api/barang/search?startDate=&endDate=', true);
request.onload = function () {
  var data = JSON.parse(this.response);
  var results = data.result;
  var size = results.length;
  var headerTable = [
	  { "Nama Barang": "", "Harga Satuan":0, "Jenis Barang":"", "Supplier":"", "Id Supplier":0, "Nomor Telp Supplier":"", "Alamat Supplier":"" }
	];

  if (request.status >= 200 && request.status < 400) {

    var table = document.createElement("table");
    table.style.width = '90%';
    table.setAttribute('border', '1');
    table.setAttribute('cellspacing', '0');
    table.setAttribute('cellpadding', '5');


    var col = []; // define an empty array
			for (var i = 0; i < 7; i++) {
				for (var key in headerTable[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
    	}

    
    // CREATE TABLE HEAD .
			var tHead = document.createElement("thead");	
				
			
			// CREATE ROW FOR TABLE HEAD .
      var hRow = document.createElement("tr");

      for (var i = 0; i < 7; i++) {
        var th = document.createElement("th");
        th.innerHTML = col[i];
        hRow.appendChild(th);
    }
    tHead.appendChild(hRow);
    table.appendChild(tHead);


      
      tHead.appendChild(hRow);
      table.appendChild(tHead);
      container.appendChild(table);




  }
}
request.send();